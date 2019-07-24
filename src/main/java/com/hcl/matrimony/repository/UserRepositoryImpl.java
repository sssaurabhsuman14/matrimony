package com.hcl.matrimony.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.hcl.matrimony.entity.User;
import com.hcl.matrimony.model.SearchModel;

@Repository
@Transactional

public class UserRepositoryImpl implements UserRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchProfilesCustom(SearchModel searchModel){

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("Select * from User where ");

		if(!(ObjectUtils.isEmpty(searchModel.getMinAge()) && ObjectUtils.isEmpty(searchModel.getMaxAge()))) {
			stringBuilder.append("year(date_of_birth) between '"+ searchModel.getMinAge() +"' and '"+ searchModel.getMaxAge() +"' ");
		}

		if(!(ObjectUtils.isEmpty(searchModel.getMinHeight()) && ObjectUtils.isEmpty(searchModel.getMaxHeight()))) {
			if(!(ObjectUtils.isEmpty(searchModel.getMinAge()) && ObjectUtils.isEmpty(searchModel.getMaxAge()))) {
				stringBuilder.append("and ");
			}
			stringBuilder.append("height between '"+ searchModel.getMinHeight() +"' and '"+ searchModel.getMaxHeight() +"' ");
		}

		if(!ObjectUtils.isEmpty(searchModel.getReligion())) {
			if(!(ObjectUtils.isEmpty(searchModel.getMinAge()) && ObjectUtils.isEmpty(searchModel.getMaxAge())) || !(ObjectUtils.isEmpty(searchModel.getMinHeight()) && ObjectUtils.isEmpty(searchModel.getMaxHeight()))) {
				stringBuilder.append("and ");
			}
			stringBuilder.append("religion = '"+ searchModel.getReligion() +"' ");
		}

		if(!ObjectUtils.isEmpty(searchModel.getMaritalStatus())) {
			if(!(ObjectUtils.isEmpty(searchModel.getMinAge()) && ObjectUtils.isEmpty(searchModel.getMaxAge())) || !(ObjectUtils.isEmpty(searchModel.getMinHeight()) && ObjectUtils.isEmpty(searchModel.getMaxHeight())) || (!ObjectUtils.isEmpty(searchModel.getReligion()))) {
				stringBuilder.append("and ");
			}
			stringBuilder.append("marital_status = ' "+ searchModel.getMaritalStatus() +"' ");

		}

		if(!ObjectUtils.isEmpty(searchModel.getCity())) {
			if(!(ObjectUtils.isEmpty(searchModel.getMinAge()) && ObjectUtils.isEmpty(searchModel.getMaxAge())) || !(ObjectUtils.isEmpty(searchModel.getMinHeight()) && ObjectUtils.isEmpty(searchModel.getMaxAge())) || (!ObjectUtils.isEmpty(searchModel.getReligion())) || (!ObjectUtils.isEmpty(searchModel.getMaritalStatus()))) {
				stringBuilder.append("and ");

			}
			stringBuilder.append(" city = '"+ searchModel.getCity() +"' ");
		}

		stringBuilder.append("and user_id  !=  '"+ searchModel.getUserId() +"' ");

		final Query query = entityManager.createNativeQuery(stringBuilder.toString(), User.class);

		final List<User> results = query.getResultList();
		entityManager.close();
		return results;
	}
}
