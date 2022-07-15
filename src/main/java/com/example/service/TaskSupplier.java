package com.example.service;

import com.example.model.Task;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Supplier;

public abstract class TaskSupplier implements Supplier<Collection<Task>> {

    private final Collection<Task> tasks = new LinkedList<>();

    private boolean generated;

    @Override
    public Collection<Task> get() {
        if (!generated) {
            tasks.addAll(generate());
            generated = true;
        }
        return tasks;
    }

    protected abstract Collection<Task> generate();
}
