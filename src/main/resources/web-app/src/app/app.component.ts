import {Component, ViewChild} from '@angular/core';
import {getData, TaskData} from "./task-show/Test/JsonDataMat-String";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  data: TaskData = getData();


}
