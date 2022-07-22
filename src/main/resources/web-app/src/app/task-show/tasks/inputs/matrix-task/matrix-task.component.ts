import {Component, Input, OnInit} from '@angular/core';
import {Task} from '../../Task';
@Component({
  selector: 'app-matrix-task',
  templateUrl: './matrix-task.component.html',
  styleUrls: ['./matrix-task.component.css', '../../input.styles.css']
})
export class MatrixTaskComponent implements Task {

  constructor() { }
  @Input() data: Array<Array<string>> = [['1'],['1']];

}
