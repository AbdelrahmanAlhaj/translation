import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TranslationHistoryComponent } from './translation-history.component';

describe('TranslationHistoryComponent', () => {
  let component: TranslationHistoryComponent;
  let fixture: ComponentFixture<TranslationHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TranslationHistoryComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TranslationHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
