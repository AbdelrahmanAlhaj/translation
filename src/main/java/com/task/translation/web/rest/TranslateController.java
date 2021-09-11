package com.task.translation.web.rest;

import com.task.translation.service.dto.TranslationDTO;
import com.task.translation.service.translation.TranslationService;
import java.io.IOException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/translate/")
public class TranslateController {

    private final Logger log = LoggerFactory.getLogger(TranslateController.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TranslationService translationService;

    public TranslateController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping(value = "/translate-text")
    public ResponseEntity<TranslationDTO> callTranslation(@Valid @RequestBody TranslationDTO translateDto)
        throws InterruptedException, IOException {
        log.info("call translation");
        return ResponseUtil.wrapOrNotFound(
            translationService.translateText(translateDto),
            HeaderUtil.createAlert(applicationName, "translation.successfully", translateDto.getQ())
        );
    }

    @GetMapping(value = "/list-languages")
    public ResponseEntity<String> getLanguagesList() throws InterruptedException, IOException {
        return new ResponseEntity<>(translationService.getListLanguages(), HttpStatus.OK);
    }
}
