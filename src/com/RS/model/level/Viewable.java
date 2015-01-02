package com.RS.model.level;

import com.RS.model.AccessLevel;
import com.RS.model.AccessLeveled;

public class Viewable implements AccessLeveled {
	AccessLeveled leveledItem;
	
	public Viewable(AccessLeveled o) {

		leveledItem = o;
	}

	@Override
	public AccessLevel getLevel() {
		AccessLevel level = leveledItem.getLevel();
		level.setBit(posViewable);
		return level;
	}

}
