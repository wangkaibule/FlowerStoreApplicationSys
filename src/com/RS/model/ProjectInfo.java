package com.RS.model;

import java.util.ArrayList;

public abstract class ProjectInfo implements AccessLeveled {
	protected long projectUID = -1L;
	public static final int projectTypeApplication = 0;
	public static final int projectTypeSummary = 1;
	protected boolean modified = false;
	protected ProjectInfo pItem;

	public long getProjectUID() {
		return projectUID;
	}

	public void setProjectUID(long id) {
		projectUID = id;
	}

	public abstract int getProjectType();

	public abstract String getProjectTitle();

	public abstract Object getContent();

	public abstract String getAuthor();

	public abstract void fillAuthorInfo(TeamMemberInfo info);

	@Override
	public AccessLevel getLevel() {

		return new AccessLevel(pItem);
	}

	@Override
	public ProjectInfo getProjectItem() {
		return pItem;
	}

	public int getProjectTypeApplication() {
		return projectTypeApplication;
	}

	public int getProjectTypeSummary() {
		return projectTypeSummary;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean b) {
		this.modified = true;
	}
}
