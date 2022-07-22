import {Component, Input, OnInit} from '@angular/core';
import {Task} from '../../Task'
@Component({
  selector: 'app-string-answer',
  templateUrl: './string-answer.component.html',
  styleUrls: ['./string-answer.component.css']
})
export class StringAnswerComponent implements Task {

  constructor() { }

  ngOnInit(): void {
  }

  @Input() data: string|undefined;

}
