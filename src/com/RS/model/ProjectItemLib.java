package com.RS.model;

//TODO ALL ProjectItem operates should use this class.

/*
 * Project will be saved in database as many parts.
 * the first parts (no. 0 part) should be the title of projects and the list of team members
 * */
public class ProjectItemLib {
	private static ProjectItemLib singleton=null;
	
	private ProjectItemLib(){
		
	}
	
	//TODO ProjectLib should be created by application when application is started.
	public static ProjectItemLib getProjectLib(){
		if(singleton == null){
			singleton = new ProjectItemLib();
			return singleton;
		}else{
			return singleton;
		}
	}

	//TODO functions below are not completed .
	public boolean update(long projectUID,int part){
		
		return DataBaseInterface.updatePart(projectUID,part);
	}
	
	public ProjectItem getProject(long projectUID){
		return new ProjectItem();
	}
	
	public ProjectItem createProject(){
		return new ProjectItem();
	}
	
	public boolean saveProject(long projectUID){
		return true;
	}
	
	public boolean deleteProject(long projectUID){
		return true;
	}
}
