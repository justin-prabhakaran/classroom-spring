package com.justinprabhakaran.classroom.feature.auth.data.model;


import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.ROLE;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Teacher;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Entity
@Table(name = "teacher")
public class TeacherModel extends Teacher {
    @Column(nullable = false)
    protected String passHash;

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DEPARTMENT getDepartment() {
        return department;
    }

    public void setDepartment(DEPARTMENT department) {
        this.department = department;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }
}
