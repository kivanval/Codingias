package com.example.model;

import lombok.Data;

@Data
public class Task<D, V> {

    String description;
    Attribute<D> definition;
    Attribute<V> value;
    boolean training;
    Type type;

    public enum Type {
        CODING, DECODING
    }


}
