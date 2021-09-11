import { Component, OnInit } from '@angular/core';
import { Language, Languages } from './service/language.model';
import { Translation } from './service/translation.model';
import { TranslationService } from './service/translation.service';

@Component({
  selector: 'jhi-translation',
  templateUrl: './translation.component.html',
  styleUrls: ['./translation.component.scss'],
})
export class TranslationComponent implements OnInit {
  translation: Translation = new Translation('', 'en', 'ar', '');
  languages: Language[] | null = null;
  isLoading = false;

  constructor(private translationService: TranslationService) {}

  ngOnInit(): void {
    this.listAllLanguages();
  }

  translateText(): void {
    this.translation.translatedText = '';
    this.isLoading = true;
    this.translationService.translateText(this.translation).subscribe(
      (x: Translation) => {
        this.translation = x;
        this.isLoading = false;
      },
      err => {
        console.error(err);
        this.isLoading = false;
      }
    );
  }

  listAllLanguages(): void {
    this.translationService.fetchLanguages().subscribe(
      (x: Languages) => {
        this.languages = x.languages;
      },
      err => console.error(err)
    );
  }
}
