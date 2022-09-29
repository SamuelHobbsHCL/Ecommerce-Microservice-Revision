import { TestBed } from '@angular/core/testing';
import { testImports } from '../global/test.global';

import { JwtClientService } from './jwt-client.service';

describe('JwtClientService', () => {
  let service: JwtClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: testImports
    });
    service = TestBed.inject(JwtClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
