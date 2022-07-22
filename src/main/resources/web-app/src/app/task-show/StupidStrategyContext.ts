import {Component} from "@angular/core";
import {TaskItem} from "./TaskItem";
import {TaskData} from "./Test/JsonData";
import {MatrixTaskComponent} from "./tasks/inputs/matrix-task/matrix-task.component";
import {StringAnswerComponent} from "./tasks/answers/string-answer/string-answer.component";
import {MatrixAnswerComponent} from "./tasks/answers/matrix-answer/matrix-answer.component";

export function getStrategyComponent(data: TaskData): Array<TaskItem>{
    const res = [];
    switch (data.input.type){
        case 'matrix':
            res.push(new TaskItem(MatrixTaskComponent, data.input.data));
            break;
    }
    switch (data.answer.type){
        case 'matrix':
            res.push(new TaskItem(MatrixAnswerComponent, data.input.data));
            break;
        case 'string':
            res.push(new TaskItem(StringAnswerComponent, data.answer.data))
            break;
    }
    return res;
}