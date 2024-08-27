package com.justinprabhakaran.classroom.feature.auth.data.model;


import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.YEAR;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name="student")
public class StudentModel extends Student {
    @Id
    protected long registerNumber;
}
