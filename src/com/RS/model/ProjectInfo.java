package com.RS.model;

import java.util.ArrayList;

public abstract class ProjectInfo implements AccessLeveled {
	protected long projectUID = -1L;
	public static final int projectTypeApplication = 0;
	public static final int projectTypeSummary = 1;
	protected boolean modified = false;
	protected ProjectInfo pItem;
	private int inHandleCount = 0;

	public long getProjectUID() {
		return projectUID;
	}

	public void setProjectUID(long id) {
		projectUID = id;
	}

	public void updateModification() {
		boolean temp = false;
		synchronized (this) {
			inHandleCount--;
			if (inHandleCount <= 0) {
				temp = true;
				ProjectItemLib lib = ProjectItemLib.getProjectLib();
				lib.saveProject(projectUID);
			}
			if (temp == true && modified) {
				storeContent();
			}
		}
	}

	protected abstract void storeContent();

	public abstract int getProjectType();

	public abstract Object getContent();

	public abstract String getAuthorName();

	public abstract void fillAuthorInfo(TeamMemberInfo info);

	@Override
	public AccessLevel getLevel() {

		return new AccessLevel(pItem);
	}

	public abstract String getProjectTitle();

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
