package com.justinprabhakaran.classroom.feature.auth.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;



@Entity
public class TeacherModel {
    @Id
    String name;

}
