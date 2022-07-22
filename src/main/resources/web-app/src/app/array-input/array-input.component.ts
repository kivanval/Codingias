import { Component, OnInit } from '@angular/core';

import{Task} from '../task-show/tasks/Task';
@Component({
  selector: 'app-array-input',
  templateUrl: './array-input.component.html',
  styleUrls: ['./array-input.component.css']
})
export class ArrayInputComponent implements Task {

  constructor() { }

  ngOnInit(): void {
  }

  data: Array<string> | undefined;

}
