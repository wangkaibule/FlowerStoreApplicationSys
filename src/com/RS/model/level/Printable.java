package com.RS.model.level;

import com.RS.model.AccessLevel;
import com.RS.model.AccessLeveled;

public class Printable implements AccessLeveled {
	
	AccessLeveled leveledItem;
	
	public Printable(AccessLeveled o) {
		leveledItem = o;
		
	}

	@Override
	public AccessLevel getLevel() {
		AccessLevel level = leveledItem.getLevel();
		level.setBit(posPrintable);
		return level;
	}

}
