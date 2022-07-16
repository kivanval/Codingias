package com.example.service;

import com.example.model.Task;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.IntBinaryOperator;

@Getter
public final class GrayCodeSupplier extends TaskSupplier {

    private final int min;

    private final int max;

    private final double effectiveShare;

    private static final double DEFAULT_EFFECTIVE_SHARE = 0.1D;

    public GrayCodeSupplier(int min, int max, double effectiveShare) {
        this.min = min;
        this.max = max;
        this.effectiveShare = effectiveShare;
    }

    public GrayCodeSupplier(int min, int max) {
        this.min = min;
        this.max = max;
        this.effectiveShare = DEFAULT_EFFECTIVE_SHARE;
    }

    @Override
    protected ArrayList<Task> generateCodingTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        //TODO
        return tasks;
    }

    @Override
    protected Collection<Task> generateDecodingTasks() {
        //TODO
        return null;
    }

    private ArrayList<Task> generateTasks(IntBinaryOperator converter, final int length) {
        final int start = 1 << length;
        final int startReflection = ~start;
        final int end = start << 1;
        ArrayList<Task> tasks = new ArrayList<>(start - 1);
        for (int i = start; i < end; i++) {
            int input = i & startReflection;
            int image = converter.applyAsInt(input, length);
        }
        //TODO
        return tasks;
    }

    private int grayCodeAlgorithm(final int input, final int length) {
        final int mask = -(1 << length - 1);
        int image = input & mask;
        for (int i = length - 1; i > 0; i--) {
            int left = (input >>> i) & 1;
            int right = (input >>> (i - 1)) & 1;
            int value = (left ^ right) << (i - 1);
            image |= value;
        }
        return image;
    }
}
