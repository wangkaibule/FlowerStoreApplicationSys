package com.RS.model;

import java.util.BitSet;

public interface AccessLeveled {
	static final int posRemovable = 0;
	static final int posModifiable = 1;
	static final int posPrintable = 2;
	static final int posViewable = 3;
	static final int posAddable = 4;
	public AccessLevel getLevel();
}