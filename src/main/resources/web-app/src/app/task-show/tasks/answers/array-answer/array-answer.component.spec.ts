import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArrayAnswerComponent } from './array-answer.component';

describe('ArrayAnswerComponent', () => {
  let component: ArrayAnswerComponent;
  let fixture: ComponentFixture<ArrayAnswerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArrayAnswerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArrayAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
