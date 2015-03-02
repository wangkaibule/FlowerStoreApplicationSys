package com.RS.model;

public abstract class ProjectInfo implements AccessLeveled {
	protected long projectUID = -1L;
	public static final int projectTypeApplication = 0;
	public static final int projectTypeSummary = 1;
	protected boolean modified = false;
	protected ProjectInfo pItem;
	protected int inHandleCount = 0;
	private boolean isDeleted = false;

	public long getProjectUID() {
		return projectUID;
	}

	public boolean deleteProject(){
		isDeleted = true;
		return onDeleteProject();
	}
	
	protected void updateOnUsrExit() {
		ProjectItemLib lib = ProjectItemLib.getProjectLib();
		lib.onUserExit(projectUID);

		if (modified&&!isDeleted) {
			storeContent();
		}
	}
	
	public void updateProject(){
		if(modified&&!isDeleted){
			storeContent();
			modified = false;
		}
	}

	protected abstract void storeContent();

	public abstract int getProjectType();

	public abstract Object getContent();

	public abstract String getAuthorName();

	protected abstract void fillAuthorInfo(TeamMemberInfo info);

	protected abstract boolean onDeleteProject();

	public abstract String getProjectTitle();

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

	public void setModified() {
		this.modified = true;
	}

}
