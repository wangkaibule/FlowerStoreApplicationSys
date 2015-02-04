package com.RS.model.level;

import com.RS.model.AccessLevel;
import com.RS.model.AccessLeveled;
import com.RS.model.ProjectInfo;
import com.RS.model.ProjectInfo;

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
	@Override
	public ProjectInfo getProjectItem() {
		// TODO Auto-generated method stub
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
}
