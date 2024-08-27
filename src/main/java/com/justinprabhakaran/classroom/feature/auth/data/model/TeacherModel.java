package com.justinprabhakaran.classroom.feature.auth.data.model;

import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.ROLE;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "teacher")
public class TeacherModel extends Teacher {
    @Id
    protected long id;
    protected String name;
    protected  String email;
    protected DEPARTMENT department;
    protected ROLE role;


}
