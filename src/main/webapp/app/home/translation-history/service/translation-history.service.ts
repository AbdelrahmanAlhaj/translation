import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from '../../../core/config/application-config.service';
import { createRequestOption } from '../../../core/request/request-util';
import { Pagination } from '../../../core/request/request.model';
import { TranslationHistory } from './translation-history.model';

@Injectable({ providedIn: 'root' })
export class TranslationHistoryService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  query(req?: Pagination): Observable<HttpResponse<TranslationHistory[]>> {
    const options = createRequestOption(req);
    return this.http.get<TranslationHistory[]>(this.applicationConfigService.getEndpointFor('api/translate/history'), {
      params: options,
      observe: 'response',
    });
  }

  update(translationHistory: TranslationHistory): Observable<TranslationHistory> {
    return this.http.put<TranslationHistory>(this.applicationConfigService.getEndpointFor('api/translate/history'), translationHistory);
  }

  delete(selectedTranslations: number[]): Observable<{}> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      params: {
        ids: selectedTranslations,
      },
    };
    return this.http.delete(this.applicationConfigService.getEndpointFor('api/translate/history'), options);
  }
}
