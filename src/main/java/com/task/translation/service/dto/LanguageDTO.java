package com.task.translation.service.dto;

public class LanguageDTO {

    private String languageName;
    private String languageCode;

    public String getLanguageName() {
        return languageName;
    }

    public LanguageDTO setLanguageName(String languageName) {
        this.languageName = languageName;
        return this;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public LanguageDTO setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        return this;
    }
}
