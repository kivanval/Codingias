import { Component} from '@angular/core';
import {Student} from "./Student";
import {Event} from "@angular/router";
@Component({
  selector: 'app-admin-register-page',
  templateUrl: './admin-register-page.component.html',
  styleUrls: ['./admin-register-page.component.css']
})
export class AdminRegisterPageComponent {
  students: Array<Student> = [{name:'Никита', surname:'Пауков',email:'nikpaukov@gmail.com'},
    {name:'Никита', surname:'Пауков',email:'nikpaukov@gmail.com'}];
  addStudent(student: Student){
    this.students.push(student);
  }
  addStudents(students: Array<Student>){
    console.log(students);
    this.students = this.students.concat(students);
  }
  deleteStudent(ind: number){
    this.students.splice(ind, 1);
  }
  redactStudent(data: {index:number, fieldName:string, newData:string}){
    // @ts-ignore
    this.students[data.index][data.fieldName] = data.newData;

  }
}
