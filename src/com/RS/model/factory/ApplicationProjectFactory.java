package com.RS.model.factory;

import com.RS.model.ApplicationProject;
import com.RS.model.ProjectInfo;
import com.RS.model.Tester;
import com.RS.model.TheFactory;

public class ApplicationProjectFactory implements ProjectFactory{

	@Override
	public ProjectInfo create() {
		// TODO Auto-generated method stub
//		return new ApplicationProject();
		return Tester.createAppllicationProject();
	}

}
