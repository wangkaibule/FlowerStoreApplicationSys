package com.RS.model.level;

import com.RS.model.AccessLevel;
import com.RS.model.AccessLeveled;

public class Modifiable implements AccessLeveled{
AccessLeveled leveledItem;
	
	public Modifiable(AccessLeveled o){
		leveledItem = o;
	}
	@Override
	public AccessLevel getLevel() {
		AccessLevel level = leveledItem.getLevel();
		level.setBit(posModifiable);
		return level;
	}
}
