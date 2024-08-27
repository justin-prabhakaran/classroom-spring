package com.justinprabhakaran.classroom.feature.auth.data.datasource;

import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAuthDataSource extends JpaRepository<StudentModel,Long> { }
