package com.task.translation.web.rest;

import com.task.translation.service.dto.TranslationHistoryDTO;
import com.task.translation.service.translation.TranslationHistoryService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/translate/")
public class TranslateHistoryController {

    private final Logger log = LoggerFactory.getLogger(TranslateHistoryController.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TranslationHistoryService translationHistoryService;

    public TranslateHistoryController(TranslationHistoryService translationHistoryService) {
        this.translationHistoryService = translationHistoryService;
    }

    @DeleteMapping(value = "/histories")
    public void deleteTranslationHistory() {}

    @DeleteMapping("/history")
    public ResponseEntity<Void> deleteUser(@RequestParam List<Long> ids) {
        log.debug("REST request to delete translation: {}", ids);
        translationHistoryService.deleteTranslation(ids);
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName, "translation.deleted", ids.toString())).build();
    }

    @GetMapping("/history")
    public ResponseEntity<List<TranslationHistoryDTO>> getAllTranslationHistory(Pageable pageable) {
        log.debug("REST request to get all User for an admin");
        final Page<TranslationHistoryDTO> page = translationHistoryService.getAllTranslationForUser(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @PutMapping("/history")
    public ResponseEntity<TranslationHistoryDTO> updateTranslationRank(@Valid @RequestBody TranslationHistoryDTO translationHistoryDTO) {
        log.info("REST request to update User : {}", translationHistoryDTO);
        Optional<TranslationHistoryDTO> updatedUser = translationHistoryService.updateTranslationRank(translationHistoryDTO);

        return ResponseUtil.wrapOrNotFound(
            updatedUser,
            HeaderUtil.createAlert(applicationName, "translation.updated", translationHistoryDTO.getId().toString())
        );
    }
}
