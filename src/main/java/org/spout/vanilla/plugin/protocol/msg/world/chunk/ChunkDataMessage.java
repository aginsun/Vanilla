/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * Vanilla is licensed under the Spout License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.vanilla.plugin.protocol.msg.world.chunk;

import org.apache.commons.lang3.builder.ToStringBuilder;

import org.spout.api.protocol.Session;
import org.spout.api.protocol.reposition.RepositionManager;
import org.spout.api.util.SpoutToStringStyle;

import org.spout.vanilla.api.protocol.msg.VanillaBlockDataChannelMessage;

public final class ChunkDataMessage extends VanillaBlockDataChannelMessage {
	private final int x, z;
	private final boolean contiguous;
	private final boolean[] hasAdditionalData;
	private final byte[][] data;
	private final byte[] biomeData;
	private final boolean unload;
	private final Session session;

	public ChunkDataMessage(int x, int z, boolean contiguous, boolean[] hasAdditionalData, byte[][] data, byte[] biomeData, Session session, RepositionManager rm) {
		this(x, z, contiguous, hasAdditionalData, data, biomeData, false, session, rm);
	}

	public ChunkDataMessage(int x, int z, boolean contiguous, boolean[] hasAdditionalData, byte[][] data, byte[] biomeData, boolean unload, Session session, RepositionManager rm) {
		if (!unload && (hasAdditionalData.length != data.length || data.length != 16)) {
			throw new IllegalArgumentException("Data and hasAdditionalData must have a length of 16");
		}
		this.x = rm.convertChunkX(x);
		this.z = rm.convertChunkZ(z);
		this.contiguous = contiguous;
		this.hasAdditionalData = hasAdditionalData;
		this.data = data;
		this.biomeData = biomeData;
		this.unload = unload;
		this.session = session;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public boolean[] hasAdditionalData() {
		return hasAdditionalData;
	}

	public boolean isContiguous() {
		return contiguous;
	}

	public byte[][] getData() {
		return data;
	}

	public byte[] getBiomeData() {
		return biomeData;
	}

	public boolean shouldUnload() {
		return unload;
	}

	public Session getSession() {
		return session;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, SpoutToStringStyle.INSTANCE)
				.append("x", x)
				.append("z", z)
				.append("hasAdditionalData", hasAdditionalData)
				.append("contiguous", contiguous)
				.append("data", data, false)
				.append("biomeData", data, false)
				.append("unload", unload, false)
				.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ChunkDataMessage other = (ChunkDataMessage) obj;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(this.x, other.x)
				.append(this.z, other.z)
				.append(this.contiguous, other.contiguous)
				.append(this.hasAdditionalData, other.hasAdditionalData)
				.append(this.data, other.data)
				.append(this.biomeData, other.biomeData)
				.append(this.unload, other.unload)
				.isEquals();
	}
}
