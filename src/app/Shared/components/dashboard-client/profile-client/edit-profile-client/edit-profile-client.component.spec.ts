import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfileClientComponent } from './edit-profile-client.component';

describe('EditProfileClientComponent', () => {
  let component: EditProfileClientComponent;
  let fixture: ComponentFixture<EditProfileClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditProfileClientComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditProfileClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
