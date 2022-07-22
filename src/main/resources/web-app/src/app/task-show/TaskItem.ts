import {Task} from './tasks/Task';
import {Data} from "./Test/JsonData";
import {Type} from "@angular/core";

export class TaskItem{
    constructor(public component: Type<Task>, public data:any) {
    }
}