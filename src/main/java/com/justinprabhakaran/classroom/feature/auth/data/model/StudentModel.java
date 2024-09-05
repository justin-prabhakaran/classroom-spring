package com.justinprabhakaran.classroom.feature.auth.data.model;
import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.SROLE;
import com.justinprabhakaran.classroom.core.utils.YEAR;
import com.justinprabhakaran.classroom.feature.auth.domain.entity.Student;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="student")
public class StudentModel extends Student {
    public SROLE getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(SROLE securityRole) {
        this.securityRole = securityRole;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public Character getSection() {
        return section;
    }

    public void setSection(Character section) {
        this.section = section;
    }

    public DEPARTMENT getDepartment() {
        return department;
    }

    public void setDepartment(DEPARTMENT department) {
        this.department = department;
    }

    public YEAR getYear() {
        return year;
    }

    public void setYear(YEAR year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(Long registerNumber) {
        this.registerNumber = registerNumber;
    }
}
