package com.RS.model.level;

import com.RS.model.AccessLevel;
import com.RS.model.AccessLeveled;

public class Removable implements AccessLeveled {
	
	AccessLeveled leveledItem;
	
	public Removable(AccessLeveled o){
		leveledItem = o;
	}
	@Override
	public AccessLevel getLevel() {
		AccessLevel level = leveledItem.getLevel();
		level.setBit(posRemovable);
		return level;
	}

}
