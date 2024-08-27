package com.justinprabhakaran.classroom.core.utils;

public interface Usecase<ArgType,ReturnType>{
    ReturnType execute(ArgType args);
}
