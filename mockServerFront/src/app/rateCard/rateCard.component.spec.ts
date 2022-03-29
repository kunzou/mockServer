import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RateCardComponent } from './rateCard.component';

describe('RateCardComponent', () => {
  let component: RateCardComponent;
  let fixture: ComponentFixture<RateCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RateCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RateCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});