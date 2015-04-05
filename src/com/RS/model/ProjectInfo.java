package com.RS.model;

import java.util.List;

import wkBule.docFiller.xml.contentDescriptor.Content;

public abstract class ProjectInfo implements AccessLeveled {
	protected long projectUID = -1L;
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
	
	public static List<Content> getDefinedProjects(){
		return new TheFactory().getProjectsList();
	}
	
	public String getEditorPage(){
		return TheFactory.getProjectsMap().get(getProjectType()).getEditorPage();
	}

	protected abstract void storeContent();

	public abstract String getProjectType();

	public abstract Object getContent();

	public abstract String getAuthorName();

	protected abstract void fillAuthorInfo(TeamMemberInfo info);

	protected abstract boolean onDeleteProject();

	abstract public Object getViewer();

	public abstract String getProjectTitle();
	
	@Override
	public AccessLevel getLevel() {

		return new AccessLevel(pItem);
	}

	@Override
	public ProjectInfo getProjectItem() {
		return pItem;
	}

	public void setModified() {
		this.modified = true;
	}
}
