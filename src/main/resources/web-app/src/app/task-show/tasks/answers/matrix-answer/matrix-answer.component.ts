import {Component, Input, OnInit} from '@angular/core';
import {Task} from '../../Task';

@Component({
  selector: 'app-matrix-answer',
  templateUrl: './matrix-answer.component.html',
  styleUrls: ['./matrix-answer.component.css','../../input.styles.css']
})
export class MatrixAnswerComponent implements Task {

  constructor() { }
  @Input() data: Array<Array<string>>|undefined;

}
