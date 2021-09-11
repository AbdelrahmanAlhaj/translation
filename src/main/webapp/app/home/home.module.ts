import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SharedModule } from '../shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { TranslationComponent } from './translation/translation.component';
import { TranslationHistoryComponent } from './translation-history/translation-history.component';
import { TranslationHistoryDeleteDialogComponent } from './translation-history/delete/translation-history-delete-dialog.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(HOME_ROUTE)],
  declarations: [HomeComponent, TranslationComponent, TranslationHistoryComponent, TranslationHistoryDeleteDialogComponent],
})
export class HomeModule {}
