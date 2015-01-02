package com.RS.model;

import java.util.BitSet;

import com.sun.glass.ui.Window.Level;

public class AccessLevel implements AccessLeveled {
	private BitSet level;
	private AccessLeveled leveledItem;
	
	
	public AccessLevel(AccessLeveled o){
		leveledItem = o;
		level = new BitSet(5);
		
		level.clear();
	}

	public boolean isRemovable() {
		return level.get(posRemovable);
	}

	public boolean isModifiable() {
		return level.get(posModifiable);
	}

	public boolean isPrintable() {
		return level.get(posPrintable);
	}

	public boolean isViewable() {
		return level.get(posPrintable);
	}

	public boolean isAddable() {
		return level.get(posAddable);
	}
	
	public void setBit(int pos){
		level.set(pos);
	}

	@Override
	public AccessLevel getLevel() {
		return this;
	}
	
	
}
