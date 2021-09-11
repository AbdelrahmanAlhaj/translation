import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslationHistoryService } from '../service/translation-history.service';

@Component({
  selector: 'jhi-translation-history-delete-dialog',
  templateUrl: './translation-history-delete-dialog.component.html',
  styleUrls: ['./translation-history-delete-dialog.component.scss'],
})
export class TranslationHistoryDeleteDialogComponent {
  selectedTranslations: number[] = [];

  constructor(private translationHistoryService: TranslationHistoryService, private activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(): void {
    this.translationHistoryService.delete(this.selectedTranslations).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
