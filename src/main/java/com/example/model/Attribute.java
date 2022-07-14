package com.example.model;

import lombok.Data;

@Data
public class Attribute<T> {

    String description;
    T value;
    Type type;

    public enum Type {
        FIELD
    }


}
