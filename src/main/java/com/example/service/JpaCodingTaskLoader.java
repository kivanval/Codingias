package com.example.service;

import com.example.data.JpaTaskRepository;
import com.example.model.Coding;

public abstract class JpaCodingTaskLoader implements CodingTaskLoader {

    protected JpaTaskRepository jpaTaskRepository;

    protected Coding coding;

    protected JpaCodingTaskLoader(JpaTaskRepository jpaTaskRepository, Coding coding) {
        this.jpaTaskRepository = jpaTaskRepository;
        this.coding = coding;
    }
}
