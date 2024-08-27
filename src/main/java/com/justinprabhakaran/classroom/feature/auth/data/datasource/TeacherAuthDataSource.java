package com.justinprabhakaran.classroom.feature.auth.data.datasource;

import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherAuthDataSource extends JpaRepository<TeacherModel,Long> {

    @Query(value = "SELECT t FROM teacher t WHERE t.email = 1?",nativeQuery = true)
    Optional<TeacherModel> findByEmail(String email);

}
