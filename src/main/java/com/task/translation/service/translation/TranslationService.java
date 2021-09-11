package com.task.translation.service.translation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.translation.domain.TranslateHistoryEntity;
import com.task.translation.repository.TranslateHistoryRepository;
import com.task.translation.security.AuthoritiesConstants;
import com.task.translation.service.MailService;
import com.task.translation.service.UserService;
import com.task.translation.service.dto.TranslateDataResponse;
import com.task.translation.service.dto.TranslationDTO;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    @Value("${translate.languages.list-language-uri}")
    private String listLanguagesUri;

    @Value("${translate.languages.translate-uri}")
    private String translateLanguagesUri;

    @Value("${translate.languages.x-rapidapi-key-name}")
    private String xRapidApiKeyName;

    @Value("${translate.languages.x-rapidapi-key-value}")
    private String xRapidApiKeyValue;

    @Value("${translate.languages.x-rapidapi-host-name}")
    private String xRapidApiHostName;

    @Value("${translate.languages.x-rapidapi-host-value}")
    private String xRapidApiHostValue;

    private final TranslateHistoryRepository translateHistoryRepository;

    private final UserService userService;

    private final MailService mailService;

    public TranslationService(TranslateHistoryRepository translateHistoryRepository, UserService userService, MailService mailService) {
        this.translateHistoryRepository = translateHistoryRepository;
        this.userService = userService;
        this.mailService = mailService;
    }

    public String getListLanguages() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(URI.create(listLanguagesUri))
            .header(xRapidApiKeyName, xRapidApiKeyValue)
            .header(xRapidApiHostName, xRapidApiHostValue)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public Optional<TranslationDTO> translateText(TranslationDTO translateDto) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(URI.create(translateLanguagesUri))
            .header("content-type", "application/json")
            .header(xRapidApiKeyName, xRapidApiKeyValue)
            .header(xRapidApiHostName, xRapidApiHostValue)
            .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(translateDto)))
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
        if (response.statusCode() == HttpStatus.OK.value()) {
            TranslateDataResponse translateResponse = objectMapper.readValue(response.body(), TranslateDataResponse.class);
            String translatedText = translateResponse.getData().getTranslations().getTranslatedText();
            userService
                .getUserByLogin()
                .ifPresent(
                    user -> {
                        TranslateHistoryEntity translateHistoryEntity = new TranslateHistoryEntity()
                            .setText(translateDto.getQ())
                            .setTranslatedText(translatedText)
                            .setSourceLanguage(translateDto.getSource())
                            .setTargetLanguage(translateDto.getTarget())
                            .setUser(user);
                        mailService.sendTranslationMail(userService.getUsersWithAuthority(AuthoritiesConstants.ADMIN), translateDto);
                        translateHistoryRepository.save(translateHistoryEntity);
                    }
                );
            return Optional.of(translateDto.setTranslatedText(translatedText));
        } else {
            throw new TranslationFailedException(response.body());
        }
    }
}
