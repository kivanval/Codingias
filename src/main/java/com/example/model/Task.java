package com.example.model;

import lombok.Data;

import java.util.Collection;

@Data
public class Task {

    String description;
    Collection<Attribute<?>> definition;
    Collection<Attribute<?>> value;
    boolean training;
    Type type;

    public enum Type {
        CODING, DECODING
    }


}
