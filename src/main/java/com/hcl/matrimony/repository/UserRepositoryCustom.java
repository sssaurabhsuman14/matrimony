package com.hcl.matrimony.repository;

import java.util.List;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.model.SearchModel;

//@Repository
public interface UserRepositoryCustom {

	public List<User> searchProfilesCustom(SearchModel searchModel);

}

