package com.example.service;

public interface CodingTaskLoader {

    default void loadAll() {
        loadCodingTasks();
        loadDecodingTasks();
    }

    void loadCodingTasks();

    void loadDecodingTasks();
}
