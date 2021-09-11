import { Routes } from '@angular/router';
import { HomeComponent } from './home.component';
import { TranslationHistoryComponent } from './translation-history/translation-history.component';

export const HOME_ROUTE: Routes = [
  {
    path: '',
    component: HomeComponent,
    data: {
      pageTitle: 'home.title',
    },
  },
  {
    path: 'translate-history',
    component: TranslationHistoryComponent,
    data: {
      pageTitle: 'home.title',
    },
  },
];
