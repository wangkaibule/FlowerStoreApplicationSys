package com.RS.model;

import com.RS.model.ProjectInfo;

public interface ProjectFactory {

	ProjectInfo create(String userID);
	ProjectInfo create(long projectUID);
}
