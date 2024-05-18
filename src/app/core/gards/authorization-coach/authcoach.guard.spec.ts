import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authcoachGuard } from './authcoach.guard';

describe('authcoachGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => authcoachGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
