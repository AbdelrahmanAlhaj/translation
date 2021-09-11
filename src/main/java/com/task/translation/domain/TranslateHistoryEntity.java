package com.task.translation.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "translate_history")
public class TranslateHistoryEntity extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Positive
    @NotNull
    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    @OneToOne(optional = false)
    private User user;

    @Column(length = 5)
    private String sourceLanguage;

    @Column(length = 5)
    private String targetLanguage;

    @Column(length = 1000)
    private String text;

    @Column(length = 1000)
    private String translatedText;

    @Column(length = 1)
    @NotNull
    private int rank;

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public TranslateHistoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public TranslateHistoryEntity setUser(User user) {
        this.user = user;
        return this;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public TranslateHistoryEntity setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
        return this;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public TranslateHistoryEntity setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
        return this;
    }

    public String getText() {
        return text;
    }

    public TranslateHistoryEntity setText(String text) {
        this.text = text;
        return this;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public TranslateHistoryEntity setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
        return this;
    }

    public int getRank() {
        return rank;
    }

    public TranslateHistoryEntity setRank(int rank) {
        this.rank = rank;
        return this;
    }
}
