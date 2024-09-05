package com.justinprabhakaran.classroom.feature.auth.data.datasource;

import com.justinprabhakaran.classroom.feature.auth.data.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

@Repository
public interface StudentAuthDataSource extends JpaRepository<StudentModel,Long> { 
    
    @Query("SELECT s FROM StudentModel s WHERE s.email = :email")
    Optional<StudentModel> findByEmail(@Param("email") String email);

//    @Query("SELECT s FROM StudentModel s WHERE s.name = :name")
//    Optional<StudentModel> findByName(@Param("name") String name);
    
}
