import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {Student} from "../admin-register-page/Student";
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'app-admin-register-list',
  templateUrl: './admin-register-list.component.html',
  styleUrls: ['./admin-register-list.component.css']
})
export class AdminRegisterListComponent  {
  @Input() studentList: Array<Student> = [];
  @Output() onDeleteStudent: EventEmitter<number> = new EventEmitter<number>();
  @Output() onRedactStudent: EventEmitter<{index:number, fieldName:string, newData:string}>
      = new EventEmitter<{index:number, fieldName:string, newData:string}>();
  constructor(@Inject(DOCUMENT) document: Document
  ) {
  }
  getInputValue(id:string) : string{
    // @ts-ignore
    return document.getElementById(id).value;
  }
  deleteStudent(ind: number) {
    this.onDeleteStudent.emit(ind);
  }
  redactStudent(index:number, fieldName:string, newData:string){
    this.onRedactStudent.emit({index, fieldName, newData})
  }
}
