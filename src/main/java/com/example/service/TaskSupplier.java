package com.example.service;

import com.example.model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.function.Supplier;

public abstract class TaskSupplier implements Supplier<Collection<Task>> {

    private Collection<Task> tasks;

    private boolean generated;

    @Override
    public final Collection<Task> get() {
        if (!generated) {
            tasks = generate();
            generated = true;
        }
        return tasks;
    }

    protected final Collection<Task> generate() {
        Collection<Task> generatedTasks = new LinkedList<>();
        generatedTasks.addAll(generateCodingTasks());
        generatedTasks.addAll(generateDecodingTasks());
        return Collections.unmodifiableCollection(generatedTasks);
    }

    protected ArrayList<Task> generateCodingTasks() {
        return new ArrayList<>();
    }

    protected Collection<Task> generateDecodingTasks() {
        return new ArrayList<>();
    }
}
