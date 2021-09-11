package com.task.translation.service.dto;

import com.task.translation.domain.TranslateHistoryEntity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class TranslationHistoryDTO {

    private Long id;

    private String sourceLanguage;
    private String targetLanguage;

    private String text;
    private String translatedText;

    @Min(1)
    @Max(5)
    private int rank;

    public TranslationHistoryDTO() {
        // Empty constructor needed for Jackson.
    }

    public TranslationHistoryDTO(TranslateHistoryEntity translateHistoryEntity) {
        this.id = translateHistoryEntity.getId();
        this.sourceLanguage = translateHistoryEntity.getSourceLanguage();
        this.targetLanguage = translateHistoryEntity.getTargetLanguage();
        this.text = translateHistoryEntity.getText();
        this.translatedText = translateHistoryEntity.getTranslatedText();
        this.rank = translateHistoryEntity.getRank();
    }

    public Long getId() {
        return id;
    }

    public TranslationHistoryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public TranslationHistoryDTO setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
        return this;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public TranslationHistoryDTO setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
        return this;
    }

    public String getText() {
        return text;
    }

    public TranslationHistoryDTO setText(String text) {
        this.text = text;
        return this;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public TranslationHistoryDTO setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
        return this;
    }

    public int getRank() {
        return rank;
    }

    public TranslationHistoryDTO setRank(int rank) {
        this.rank = rank;
        return this;
    }
}
