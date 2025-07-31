import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorMyCourse } from './instructor-my-course';

describe('InstructorMyCourse', () => {
  let component: InstructorMyCourse;
  let fixture: ComponentFixture<InstructorMyCourse>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InstructorMyCourse]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InstructorMyCourse);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
