package com.RS.model;

public class Addable implements AccessLeveled {
	AccessLeveled leveledItem ;
	
	public Addable(AccessLeveled o) {
		
		leveledItem = o;
	}

	@Override
	public AccessLevel getLevel() {
		AccessLevel level = leveledItem.getLevel();
		level.setBit(posAddable);
		return level;
	}

}
