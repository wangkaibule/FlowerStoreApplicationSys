package com.RS.model;

import com.RS.model.factory.*;

public class TheFactory {
	public static ProjectFactory getProjectFactory(int projectType){
		
		ProjectFactory project = null;
		
		switch(projectType){
		case ProjectInfo.projectTypeApplication:
			project = new ApplicationProjectFactory();
			break;
		}

		return project;
	}

}