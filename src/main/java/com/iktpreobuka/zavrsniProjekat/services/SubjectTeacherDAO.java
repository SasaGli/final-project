package com.iktpreobuka.zavrsniProjekat.services;

import com.iktpreobuka.zavrsniProjekat.entities.SubjectEntity;

public interface SubjectTeacherDAO {
	public SubjectEntity findSubjectByTeacherId(Integer id);

}
