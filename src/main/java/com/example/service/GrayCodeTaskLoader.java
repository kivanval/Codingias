package com.example.service;

import com.example.data.TaskRepository;
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
public class GrayCodeTaskLoader implements TaskLoader {

    private TaskRepository taskRepository;

    private int min;

    public static final int DEFAULT_MIN = 5;

    private int max;

    public static final int DEFAULT_MAX = 10;

    private int frequency;

    public static final int DEFAULT_FREQUENCY = 10;

    @Autowired
    public GrayCodeTaskLoader(TaskRepository taskRepository) {
        this(taskRepository, DEFAULT_MIN, DEFAULT_MAX, DEFAULT_FREQUENCY);
    }

    public GrayCodeTaskLoader(TaskRepository taskRepository, int min, int max) {
        this(taskRepository, min, max, DEFAULT_FREQUENCY);
    }

    public GrayCodeTaskLoader(TaskRepository taskRepository, int min, int max, int frequency) {
        this.taskRepository = taskRepository;
        this.min = min;
        this.max = max;
        this.frequency = frequency;
    }

    @Override
    public void loadCodingTasks() {
        loadTasks(GrayCodeTaskLoader::grayCoding, Task.Type.CODING);
    }

    @Override
    public void loadDecodingTasks() {
        loadTasks(GrayCodeTaskLoader::grayDecoding, Task.Type.DECODING);
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
                taskRepository.save(task);
                log.info("save task {}", task);
            }
        }
    }

    private Task getTask(IntBinaryOperator converter, int input, int length, boolean isTraining, Task.Type taskType) {
        ElementData inputElementData = new ElementData(ElementData.ANY_SEQUENCE);
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
