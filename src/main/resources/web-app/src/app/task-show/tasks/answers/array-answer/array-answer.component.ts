import { Component, OnInit } from '@angular/core';
import{Task} from '../../Task'
@Component({
  selector: 'app-array-answer',
  templateUrl: './array-answer.component.html',
  styleUrls: ['./array-answer.component.css', '../../input.styles.css']
})
export class ArrayAnswerComponent implements Task {
  data: Array<string>|undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
