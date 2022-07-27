package com.example.service;

import com.example.data.JpaTaskRepository;
import com.example.model.Coding;
import com.example.model.Element;
import com.example.model.ElementData;
import com.example.model.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.IntBinaryOperator;

@Getter
@Setter
@Slf4j
@Service
public class JpaGrayCodeTaskLoader extends JpaCodingTaskLoader {
    private int min;

    public static final int DEFAULT_MIN = 8;
    private int max;

    public static final int DEFAULT_MAX = 8;

    private int frequency;

    public static final int DEFAULT_FREQUENCY = 10;

    @Autowired
    public JpaGrayCodeTaskLoader(JpaTaskRepository jpaTaskRepository) {
        this(jpaTaskRepository, null, DEFAULT_MIN, DEFAULT_MAX, DEFAULT_FREQUENCY);
    }

    public JpaGrayCodeTaskLoader(JpaTaskRepository jpaTaskRepository, Coding coding, int min, int max) {
        this(jpaTaskRepository, coding, min, max, DEFAULT_FREQUENCY);
    }

    public JpaGrayCodeTaskLoader(JpaTaskRepository jpaTaskRepository, Coding coding, int min, int max, int frequency) {
        super(jpaTaskRepository, coding);
        this.min = min;
        this.max = max;
        this.frequency = frequency;
    }

    @Override
    public void loadCodingTasks() {
        loadTasks(JpaGrayCodeTaskLoader::grayCoding, Task.Type.CODING);
    }

    @Override
    public void loadDecodingTasks() {
        loadTasks(JpaGrayCodeTaskLoader::grayDecoding, Task.Type.DECODING);
    }

    private void loadTasks(IntBinaryOperator converter, Task.Type taskType) {
        for (int length = min; length <= max; length++) {
            int start = 1 << length;
            int startReflection = ~start;
            int end = start << 1;
            for (int i = start, j = 0; i < end; i++, j++, j %= frequency) {
                int input = i & startReflection;
                boolean isTraining = j == frequency - 1;
                Task task = getTask(converter, input, length, isTraining, taskType);
                jpaTaskRepository.save(task);
                log.info("save task {}", task);
            }
        }
    }

    private Task getTask(IntBinaryOperator converter, int input, int length, boolean isTraining, Task.Type taskType) {
        boolean isDecoding = taskType == Task.Type.DECODING;
        ElementData inputElementData = new ElementData(ElementData.ANY_SEQUENCE);
        int image = converter.applyAsInt(input, length);
        ElementData imageElementData = new ElementData(inputToString(image, length));
        Element element = new Element(null, inputElementData, imageElementData);
        String description = getDescriptionTask(input, length, isDecoding);
        Task task = new Task(description, isTraining, taskType)
                .addElement(element);
        task.setCoding(coding);
        return task;
    }

    public static final String PREAMBLE_FOR_CODING = "Закодуйте число %s₂ кодом Грея";

    public static final String PREAMBLE_FOR_DECODING = "Розкодуйте число %s₂, яке закодоване кодом Грея";

    private String getDescriptionTask(int input, int length, boolean isDecoding) {
        return (isDecoding ? PREAMBLE_FOR_DECODING : PREAMBLE_FOR_CODING)
                .formatted(inputToString(input, length));
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

    public static int grayDecoding(int input, int length) {
        final int startBit = (input >>> (length - 1)) & 1;
        int image = 0;
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
