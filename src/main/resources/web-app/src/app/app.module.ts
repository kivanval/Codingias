import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ShowTaskComponent } from './task-show/show-task/show-task.component';
import { MatrixTaskComponent } from './task-show/tasks/inputs/matrix-task/matrix-task.component';
import { StringAnswerComponent } from './task-show/tasks/answers/string-answer/string-answer.component';
import { MatrixAnswerComponent } from './task-show/tasks/answers/matrix-answer/matrix-answer.component';
import {InputDirective} from "./task-show/InputDirective";
import {AnswerDirective} from "./task-show/AnswerDirective";
@NgModule({
  declarations: [
    AppComponent,
    ShowTaskComponent,
    MatrixTaskComponent,
    StringAnswerComponent,
    MatrixAnswerComponent,
    InputDirective,
    AnswerDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
