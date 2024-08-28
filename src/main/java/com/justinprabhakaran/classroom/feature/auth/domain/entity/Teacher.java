package com.justinprabhakaran.classroom.feature.auth.domain.entity;

import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.ROLE;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class Teacher {
    @Id
    protected long teacherId;


    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @Column(nullable = false)
    protected String name;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @Column(nullable = false,unique = true)
    protected String email;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected DEPARTMENT department;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected ROLE role;


}
