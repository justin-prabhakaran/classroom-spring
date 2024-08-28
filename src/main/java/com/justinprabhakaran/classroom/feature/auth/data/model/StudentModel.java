package com.justinprabhakaran.classroom.feature.auth.data.model;
import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.YEAR;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="student")
public class StudentModel extends Student {

    @Column(nullable = false)
    protected String passHash;


    public Long getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(Long registerNumber) {
        this.registerNumber = registerNumber;
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

    public YEAR getYear() {
        return year;
    }

    public void setYear(YEAR year) {
        this.year = year;
    }

    public DEPARTMENT getDepartment() {
        return department;
    }

    public void setDepartment(DEPARTMENT department) {
        this.department = department;
    }

    public Character getSection() {
        return section;
    }

    public void setSection(Character section) {
        this.section = section;
    }
}
