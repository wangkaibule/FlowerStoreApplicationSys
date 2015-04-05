package com.RS.model;

public interface AccessLeveled {
	static final int posRemovable = 0;
	static final int posModifiable = 1;
	static final int posNotPrintable = 2;
	static final int posNotViewable = 3;
	public AccessLevel getLevel();
	public ProjectInfo getProjectItem();
	public long getProjectUID();
	public String getProjectTitle();
}
