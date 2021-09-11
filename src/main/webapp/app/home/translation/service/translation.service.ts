import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from '../../../core/config/application-config.service';
import { Languages } from './language.model';
import { Translation } from './translation.model';

@Injectable({ providedIn: 'root' })
export class TranslationService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  public translateText(translationDTO: Translation): Observable<Translation> {
    return this.http.post<Translation>(this.applicationConfigService.getEndpointFor('api/translate/translate-text'), translationDTO);
  }

  public fetchLanguages(): Observable<Languages> {
    return this.http.get<Languages>(this.applicationConfigService.getEndpointFor('api/translate/list-languages'));
  }
}
