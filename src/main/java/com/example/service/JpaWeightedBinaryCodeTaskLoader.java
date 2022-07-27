package com.example.service;

import com.example.data.JpaTaskRepository;
import com.example.model.Coding;
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
public class JpaWeightedBinaryCodeTaskLoader extends JpaCodingTaskLoader {


    private int min;

    public static final int DEFAULT_MIN = 1;

    private int max;

    public static final int DEFAULT_MAX = 5;

    private int frequency;

    public static final int DEFAULT_FREQUENCY = 10;

    @Autowired
    public JpaWeightedBinaryCodeTaskLoader(JpaTaskRepository jpaTaskRepository) {
        this(jpaTaskRepository, null, DEFAULT_MIN, DEFAULT_MAX, DEFAULT_FREQUENCY);
    }

    public JpaWeightedBinaryCodeTaskLoader(JpaTaskRepository jpaTaskRepository, Coding coding, int min, int max) {
        this(jpaTaskRepository, coding, min, max, DEFAULT_FREQUENCY);
    }

    public JpaWeightedBinaryCodeTaskLoader(JpaTaskRepository jpaTaskRepository, Coding coding, int min, int max, int frequency) {
        super(jpaTaskRepository, coding);
        this.min = min;
        this.max = max;
        this.frequency = frequency;
    }

    private static final int[][] TETRADS = {
            {8, 4, 2, 1}, {5, 2, 1, 1}, {2, 4, 2, 1}
    };
    @Override
    public void loadCodingTasks() {

    }

    @Override
    public void loadDecodingTasks() {

    }

    private void loadTasks(IntBinaryOperator converter, Task.Type taskType) {
        int firstNum = 1;
        int secondNum;
        secondNum = 1;

        for (int length = min; length <= max; length++) {


        }
    }

    private Task getTask(IntBinaryOperator converter, int input, int length, boolean isTraining, Task.Type taskType) {
        //TODO
        return null;
    }

    public static final String DEFAULT_PREAMBLE = "Закодуйте повідомлення нижче кодом Грея:";

    private String getDescriptionTask(int input, int length) {
        return DEFAULT_PREAMBLE;
    }
}
