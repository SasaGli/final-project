package com.iktpreobuka.zavrsniProjekat.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.zavrsniProjekat.entities.SubjectEntity;


@Service
public class SubjectTeacherDAOImpl implements SubjectTeacherDAO {
	@PersistenceContext
	private EntityManager em;
	@Override
	public SubjectEntity findSubjectByTeacherId(Integer id) {
		
		String sql ="select s from subject_teacher s where s.teacher_id = :id";
		Query query=em.createQuery(sql);
		query.setParameter("id",id);
		SubjectEntity result=new SubjectEntity();
		result=(SubjectEntity) query.getSingleResult();
		return result;
	}

}
