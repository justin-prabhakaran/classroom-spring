package com.justinprabhakaran.classroom.feature.auth.domain.entity;

import com.justinprabhakaran.classroom.core.utils.DEPARTMENT;
import com.justinprabhakaran.classroom.core.utils.YEAR;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class Student {

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @Id
    protected Long registerNumber;

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
    protected YEAR year;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected DEPARTMENT department;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    @Column(nullable = false)
    protected Character section;


}
