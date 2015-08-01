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

	public boolean isNotPrintable() {
		return level.get(posNotPrintable);
	}

	public boolean isNotViewable() {
		return level.get(posNotViewable);
	}

	
	public void setBit(int pos){
		level.set(pos);
	}

	@Override
	public AccessLevel getLevel() {
		return leveledItem.getLevel();
	}

	@Override
	public ProjectInfo getProjectItem() {
		
		return leveledItem.getProjectItem();
	}

	@Override
	public long getProjectUID() {
		// TODO Auto-generated method stub
		return leveledItem.getProjectUID();
	}

	@Override
	public String getProjectTitle() {
		// TODO Auto-generated method stub
		return leveledItem.getProjectTitle();
	}
	
	@Override
	public String toString(){
		String result = "";
		if(this.isModifiable())
			result+="\'modifiable\'";
		if(this.isNotPrintable())
			result+=",\'notPrintable\'";
		if(this.isNotViewable())
			result+=",\'notViewable\'";
		if(this.isRemovable())
			result+=",\'removable\'";
		return result;
	}
}
