package com.zagorskidev.rescuecrm.utils;

import com.zagorskidev.rescuecrm.entity.Rescuer;

/**
 * Bean containing rescuer with information if it is attached (depending on context).
 * Attached flag is designed for flexible local use, contrary to 'state' property which is DB persistence field.
 * @author tomek
 *
 */
public class RescuerWithAttachedFlag {

	private Rescuer rescuer;
	private boolean attached;
	
	public RescuerWithAttachedFlag() {}
	
	public RescuerWithAttachedFlag(Rescuer rescuer, boolean attached) {
		
		super();
		this.rescuer = rescuer;
		this.attached = attached;
	}

	public Rescuer getRescuer() {
		return rescuer;
	}

	public void setRescuer(Rescuer rescuer) {
		this.rescuer = rescuer;
	}

	public boolean isAttached() {
		return attached;
	}

	public void setAttached(boolean attached) {
		this.attached = attached;
	}
}
