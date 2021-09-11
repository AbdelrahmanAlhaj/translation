package com.task.translation.service.translation;

public class TranslationFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TranslationFailedException() {
        super("Translation Failed! Not supported language");
    }

    public TranslationFailedException(String msg) {
        super("Translation Failed! : cause: " + msg);
    }
}
