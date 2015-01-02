package com.RS.model;

import java.util.BitSet;

public class AccessLevel implements AccessLeveled {
	private BitSet level;
	private AccessLeveled leveledItem;
	public AccessLevel(AccessLeveled o){
		level = new BitSet(5);
		leveledItem = o;
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
		return leveledItem.getLevel();
	}

	@Override
	public ProjectItem getProjectItem() {
		
		return leveledItem.getProjectItem();
	}

	@Override
	public long getProjectUID() {
		// TODO Auto-generated method stub
		return leveledItem.getProjectUID();
	}
	
	
}
