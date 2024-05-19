import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfileCoachComponent } from './edit-profile-coach.component';

describe('EditProfileCoachComponent', () => {
  let component: EditProfileCoachComponent;
  let fixture: ComponentFixture<EditProfileCoachComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditProfileCoachComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditProfileCoachComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
