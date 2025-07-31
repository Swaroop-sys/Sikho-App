import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewCourse } from './create-new-course';

describe('CreateNewCourse', () => {
  let component: CreateNewCourse;
  let fixture: ComponentFixture<CreateNewCourse>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateNewCourse]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateNewCourse);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
