package com.example.service;

import com.example.model.ElementData;
import com.example.model.Element;
import com.example.model.Task;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.function.IntBinaryOperator;

@Getter
@RequiredArgsConstructor
public final class GrayCodeSupplier extends TaskSupplier {

    private final int min;

    private final int max;

    private final double effectiveShare;

    private static final double DEFAULT_EFFECTIVE_SHARE = 0.25D;

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
    protected ArrayList<Task> generateDecodingTasks() {
        return generateTasks(GrayCodeSupplier::reverseGrayCoding, Task.Type.DECODING);
    }

    private ArrayList<Task> generateTasks(IntBinaryOperator converter, Task.Type taskType) {
        final int initialCapacity = (2 << max + 1) - (2 << min);
        ArrayList<Task> tasks = new ArrayList<>(initialCapacity);
        for (int length = min; length <= max; length++) {
            int start = 1 << length;
            int startReflection = ~start;
            int end = start << 1;
            int frequency = (int) (1 / effectiveShare);
            for (int i = start, j = 0; i < end; i++, j++, j %= frequency) {
                int input = i & startReflection;
                boolean isTraining = j == frequency - 1;
                tasks.add(getTask(converter, input, length, isTraining, taskType));
            }
        }
        return tasks;
    }

    private Task getTask(IntBinaryOperator converter, int input, int length, boolean isTraining, Task.Type taskType) {
        ElementData inputElementData = new ElementData(Element.ANY_SEQUENCE);
        int image = converter.applyAsInt(input, length);
        ElementData imageElementData = new ElementData(inputToString(image, length));
        Element element = new Element(null, inputElementData, imageElementData);
        String description = getDescriptionTask(input, length);
        return new Task(description, isTraining, taskType)
                .addElement(element);
    }

    public static final String DEFAULT_PREAMBLE = "Закодуйте повідомлення нижче кодом Грея:";

    private String getDescriptionTask(int input, int length) {
        return DEFAULT_PREAMBLE + "\n" + inputToString(input, length);
    }

    private String inputToString(int input, int length) {
        String binaryInput = Integer.toBinaryString(input);
        int escapeSpace = length - binaryInput.length();
        return "0".repeat(escapeSpace) + Integer.toBinaryString(input);
    }

    public static int grayCoding(int input, int length) {
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

    public static int reverseGrayCoding(int input, int length) {
        int image = 0;
        final int startBit = (input >>> (length - 1)) & 1;
        for (int i = 1; i <= length; i++) {
            int value = startBit;
            for (int j = 1; j < i; j++) {
                int bit = (input >>> (length - j - 1)) & 1;
                value ^= bit;
            }
            value <<= (length - i);
            image |= value;
        }
        return image;
    }
}
