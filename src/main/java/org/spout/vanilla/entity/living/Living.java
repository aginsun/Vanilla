/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.entity.living;

import org.spout.api.geo.cuboid.Block;
import org.spout.api.geo.discrete.Point;
import org.spout.api.geo.discrete.Transform;
import org.spout.api.util.BlockIterator;

import org.spout.vanilla.entity.VanillaControllerType;
import org.spout.vanilla.entity.VanillaEntityController;
import org.spout.vanilla.entity.component.HeadOwner;
import org.spout.vanilla.entity.component.SuffocationOwner;
import org.spout.vanilla.entity.component.basic.HeadComponent;
import org.spout.vanilla.entity.component.basic.SuffocationComponent;

public abstract class Living extends VanillaEntityController implements HeadOwner, SuffocationOwner {
	protected SuffocationComponent suffocationProcess;
	protected HeadComponent headProcess;
	protected boolean crouching;

	protected Living(VanillaControllerType type) {
		super(type);
	}

	@Override
	public void onAttached() {
		super.onAttached();
		headProcess = this.addComponent(HeadComponent.class);
		suffocationProcess = this.addComponent(SuffocationComponent.class);
	}

	/**
	 * Gets the suffocation component (air)
	 * @return entity suffocation process
	 */
	public SuffocationComponent getSuffocation() {
		return suffocationProcess;
	}



	public boolean isCrouching() {
		return crouching;
	}

	public void setCrouching(boolean crouching) {
		this.crouching = crouching;
	}

	//TODO Need to remove this or do this better...
}
