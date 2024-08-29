package com.justinprabhakaran.classroom.feature.auth.data.datasource;

import com.justinprabhakaran.classroom.feature.auth.data.model.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherAuthDataSource extends JpaRepository<TeacherModel,Long> {

    @Query("SELECT t FROM TeacherModel t WHERE t.email = :email")
    Optional<TeacherModel> findByEmail(@Param("email") String email);
}
