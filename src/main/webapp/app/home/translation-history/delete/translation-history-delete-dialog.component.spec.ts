import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TranslationHistoryDeleteDialogComponent } from './translation-history-delete-dialog.component';

describe('TranslationHistoryDeleteDialogComponent', () => {
  let component: TranslationHistoryDeleteDialogComponent;
  let fixture: ComponentFixture<TranslationHistoryDeleteDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TranslationHistoryDeleteDialogComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TranslationHistoryDeleteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
