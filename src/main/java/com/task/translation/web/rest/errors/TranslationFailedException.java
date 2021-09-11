package com.task.translation.web.rest.errors;

public class TranslationFailedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public TranslationFailedException() {
        super(ErrorConstants.TRANSLATION_FAILED_TYPE, "Translation Failed!", "translation", "translationfailed");
    }
}
