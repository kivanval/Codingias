import {Component} from "@angular/core";
import {TaskItem} from "./TaskItem";
import {TaskData} from "./Test/JsonData";
import {MatrixTaskComponent} from "./tasks/inputs/matrix-task/matrix-task.component";
import {StringAnswerComponent} from "./tasks/answers/string-answer/string-answer.component";
import {MatrixAnswerComponent} from "./tasks/answers/matrix-answer/matrix-answer.component";
import {ArrayAnswerComponent} from "./tasks/answers/array-answer/array-answer.component";
import {ArrayInputComponent} from "../array-input/array-input.component";

export function getStrategyComponent(data: TaskData): Array<TaskItem>{
    const res = [];
    switch (data.input.type){
        case 'matrix':
            res.push(new TaskItem(MatrixTaskComponent, data.input.data));
            break;
        case 'array':
            res.push(new TaskItem(ArrayInputComponent, data.input.data));
            break;
    }
    switch (data.answer.type){
        case 'matrix':
            res.push(new TaskItem(MatrixAnswerComponent, data.answer.data));
            break;
        case 'string':
            res.push(new TaskItem(StringAnswerComponent, data.answer.data))
            break;
        case 'array':
            res.push(new TaskItem(ArrayAnswerComponent, data.answer.data))
            break;

    }
    return res;
}