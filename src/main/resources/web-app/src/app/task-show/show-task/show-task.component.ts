import {Component, Directive, Input, OnInit, ViewChild} from '@angular/core';
import {TaskData} from "../Test/JsonData";
import {InputDirective} from "../InputDirective";
import {AnswerDirective} from "../AnswerDirective";
import {getStrategyComponent} from "../StupidStrategyContext";
import {TaskItem} from "../TaskItem";
import {Task} from '../tasks/Task'
@Component({
  selector: 'app-show-task',
  templateUrl: './show-task.component.html',
  styleUrls: ['./show-task.component.css']
})
export class ShowTaskComponent implements OnInit{

  constructor() { }
  @Input() data: TaskData = {input:{type:"matrix", data:[['1','1','1']]}, answer:{type:"matrix", data:[['1','1','1']]}};
  @ViewChild(InputDirective, {static: true}) inputDir!: InputDirective;
  @ViewChild(AnswerDirective, {static: true}) answerDir!: AnswerDirective;


  ngOnInit(): void {
    const components = getStrategyComponent(this.data);
    this.loadComponents(components);
  }
  loadComponents(components : Array<TaskItem>):void {
    this.loadData(components[0], this.inputDir);
    this.loadData(components[1], this.answerDir);
  }
  private loadData(taskItem: TaskItem,dir: InputDirective|AnswerDirective):void{
    const inputComponentRef = dir.viewContainerRef.createComponent<Task>(taskItem.component);
    inputComponentRef.instance.data = taskItem.data;
  }
}
