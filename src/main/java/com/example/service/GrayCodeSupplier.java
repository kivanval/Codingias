package com.example.service;

import com.example.model.Element;
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
        return generateTasks(GrayCodeSupplier::grayCoding, Task.Type.CODING);
    }

    @Override
    protected Collection<Task> generateDecodingTasks() {
        return generateTasks(GrayCodeSupplier::reverseGrayCoding, Task.Type.DECODING);
    }

    private ArrayList<Task> generateTasks(final IntBinaryOperator converter, final Task.Type taskType) {
        final ArrayList<Task> tasks = new ArrayList<>();
        for (int length = min; length <= max; length++) {
            final int start = 1 << length;
            final int startReflection = ~start;
            final int end = start << 1;
            final int frequency = (int) (1 / effectiveShare);
            for (int i = start, j = 0; i < end; i++, j++, j %= frequency) {
                final int input = i & startReflection;
                final Element inputElement = new Element(null, Element.ANY_SEQUENCE, true);
                final int image = converter.applyAsInt(input, length);
                final Element imageElement = new Element(null, "%d".formatted(image), false);
                inputElement.setImage(imageElement);
                final String description = getDescriptionTask(input);
                final boolean isTraining = j == frequency - 1;
                final Task task = new Task(description, isTraining, taskType)
                        .addInputElement(inputElement);
                tasks.add(task);
            }
        }
        return tasks;
    }

    private String getDescriptionTask(int input) {
        return """
                Закодуйте повідомлення нижче кодом Грея:
                %s
                """.formatted(Integer.toBinaryString(input));

    }

    public static int grayCoding(final int input, final int length) {
        final int mask = -(1 << length - 1);
        int image = input & mask;
        for (int i = length - 1; i > 0; i--) {
            final int left = (input >>> i) & 1;
            final int right = (input >>> (i - 1)) & 1;
            final int value = (left ^ right) << (i - 1);
            image |= value;
        }
        return image;
    }

    public static int reverseGrayCoding(final int input, final int length) {
        int image = 0;
        final int startBit = (input >>> (length - 1)) & 1;
        for (int i = 1; i <= length; i++) {
            int value = startBit;
            for (int j = 1; j < i; j++) {
                final int bit = (input >>> (length - j - 1)) & 1;
                value ^= bit;
            }
            value <<= (length - i);
            image |= value;
        }
        return image;
    }
}
