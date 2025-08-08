import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeailedCategory } from './deailed-category';

describe('DeailedCategory', () => {
  let component: DeailedCategory;
  let fixture: ComponentFixture<DeailedCategory>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeailedCategory]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeailedCategory);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
