package com.task.translation.service.dto;

public class TranslateDataResponse {

    private TranslationsResponse data;

    public class TranslationsResponse {

        private Translations translations;

        public Translations getTranslations() {
            return translations;
        }

        public TranslationsResponse setTranslations(Translations translations) {
            this.translations = translations;
            return this;
        }

        public class Translations {

            private String translatedText;

            public String getTranslatedText() {
                return translatedText;
            }

            public Translations setTranslatedText(String translatedText) {
                this.translatedText = translatedText;
                return this;
            }
        }
    }

    public TranslationsResponse getData() {
        return data;
    }

    public TranslateDataResponse setData(TranslationsResponse data) {
        this.data = data;
        return this;
    }
}
