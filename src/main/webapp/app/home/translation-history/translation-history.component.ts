import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { combineLatest } from 'rxjs';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from '../../config/pagination.constants';
import { TranslationHistoryDeleteDialogComponent } from './delete/translation-history-delete-dialog.component';
import { TranslationHistory } from './service/translation-history.model';
import { TranslationHistoryService } from './service/translation-history.service';

@Component({
  selector: 'jhi-translation-history',
  templateUrl: './translation-history.component.html',
  styleUrls: ['./translation-history.component.scss'],
})
export class TranslationHistoryComponent implements OnInit {
  translations: TranslationHistory[] | null = [];
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  isRootSelected: boolean;
  selectedTranslations: any;
  ranks: number[];
  isDeletedDisabled = true;

  constructor(
    private translationHistoryService: TranslationHistoryService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal
  ) {
    this.isRootSelected = false;
    this.ranks = [1, 2, 3, 4, 5];
  }

  ngOnInit(): void {
    this.handleNavigation();
  }

  trackIdentity(index: number, item: TranslationHistory): number {
    return item.id!;
  }

  deleteHistory(): void {
    const modalRef = this.modalService.open(TranslationHistoryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.selectedTranslations = this.selectedTranslations;
    // unsubscribe not needed because closed completes on modal close
    console.log(this.selectedTranslations);
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }

  isAllSelected(): void {
    if (this.translations != null) {
      this.isRootSelected = this.translations.every(function (item: any) {
        return item.isSelected === true;
      });
      this.getCheckedItemList();
    }
  }

  checkUncheckAll(): void {
    if (this.translations != null) {
      for (let i = 0; i < this.translations.length; i++) {
        this.translations[i].isSelected = this.isRootSelected;
      }
    }
    this.getCheckedItemList();
  }

  getCheckedItemList(): void {
    this.selectedTranslations = [];
    if (this.translations != null) {
      for (let i = 0; i < this.translations.length; i++) {
        if (this.translations[i].isSelected === true) {
          this.selectedTranslations.push(this.translations[i].id);
        }
      }
    }
    this.isDeletedButtonDisabled();
  }

  isDeletedButtonDisabled(): void {
    if (this.selectedTranslations !== null && this.selectedTranslations.length !== 0) {
      this.isDeletedDisabled = false;
    } else {
      this.isDeletedDisabled = true;
    }
  }

  updateRank(translationHistory: TranslationHistory): void {
    this.translationHistoryService.update(translationHistory).subscribe(e => console.log(e));
  }

  loadAll(): void {
    this.isLoading = true;
    this.translationHistoryService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<TranslationHistory[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers);
        },
        () => (this.isLoading = false)
      );
  }

  transition(): void {
    this.router.navigate(['./translate-history'], {
      relativeTo: this.activatedRoute.parent,
      queryParams: {
        page: this.page,
        sort: this.predicate + ',' + (this.ascending ? ASC : DESC),
      },
    });
  }

  private handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).subscribe(([data, params]) => {
      console.log(data);
      console.log(params);
      const page = params.get('page');
      this.page = page !== null ? +page : 1;
      const sort = (params.get(SORT) ?? 'createdDate,desc').split(',');
      this.predicate = sort[0];
      this.ascending = sort[1] === ASC;
      this.loadAll();
    });
  }

  private sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? ASC : DESC)];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  private onSuccess(translations: TranslationHistory[] | null, headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.translations = translations;
  }
}
