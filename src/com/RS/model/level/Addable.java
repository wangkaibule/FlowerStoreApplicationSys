package com.RS.model.level;

import com.RS.model.AccessLevel;
import com.RS.model.AccessLeveled;
import com.RS.model.ProjectItem;

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

	@Override
	public ProjectItem getProjectItem() {
		// TODO Auto-generated method stub
		return leveledItem.getProjectItem();
	}

	@Override
	public long getProjectUID() {
		// TODO Auto-generated method stub
		return leveledItem.getProjectUID();
	}

}