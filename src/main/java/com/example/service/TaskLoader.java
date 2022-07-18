package com.example.service;

public interface TaskLoader {

    default void loadAll() {
        loadCodingTasks();
        loadDecodingTasks();
    }

    void loadCodingTasks();

    void loadDecodingTasks();
}
