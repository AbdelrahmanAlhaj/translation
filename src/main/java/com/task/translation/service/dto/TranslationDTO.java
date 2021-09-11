package com.task.translation.service.dto;

import javax.validation.constraints.NotBlank;

public class TranslationDTO {

    private String source;
    private String target;

    @NotBlank
    private String q;

    private String translatedText;

    public String getSource() {
        return source;
    }

    public TranslationDTO setSource(String source) {
        this.source = source;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public TranslationDTO setTarget(String target) {
        this.target = target;
        return this;
    }

    public String getQ() {
        return q;
    }

    public TranslationDTO setQ(String q) {
        this.q = q;
        return this;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public TranslationDTO setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
        return this;
    }
}
