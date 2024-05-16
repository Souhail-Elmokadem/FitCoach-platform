import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatClientComponent } from './chat-client.component';

describe('ChatClientComponent', () => {
  let component: ChatClientComponent;
  let fixture: ComponentFixture<ChatClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ChatClientComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChatClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
