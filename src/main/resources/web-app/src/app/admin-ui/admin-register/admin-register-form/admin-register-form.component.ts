import {Component, ElementRef, EventEmitter, OnInit, Output} from '@angular/core';
import {Student} from "../admin-register-page/Student";
import readXlsxFile, {Row} from 'read-excel-file'

@Component({
  selector: 'app-admin-register-form',
  templateUrl: './admin-register-form.component.html',
  styleUrls: ['./admin-register-form.component.css']
})
export class AdminRegisterFormComponent {
  @Output() onNewStudent: EventEmitter<Student> = new EventEmitter<Student>();
  @Output() onNewStudents: EventEmitter<any> = new EventEmitter<any>();
  groupName = '';

  addStudent(student: Student) {
    this.onNewStudent.emit(student);
  }


  private readExel(input: HTMLInputElement) {
    // @ts-ignore
    const file = input.files[0];
    readXlsxFile(file).then(data => this.onNewStudents.emit(this.convertToStudentsArray(data)));
  }

  private convertToStudentsArray(jsonMatrix: Array<Row>) {
    const res: Array<Student> = [];
    jsonMatrix.forEach(el => res.push({
      surname: el[0].toString(),
      name: el[1].toString(),
      email: el[2].toString()
    }));
    return res;
  }

  importData() {
    let input = document.createElement('input');
    input.type = 'file';

    input.onchange = _ => {
      this.readExel(input);
    };
    input.click();

  }

}
