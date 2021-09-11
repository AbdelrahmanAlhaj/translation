package com.task.translation.service.translation;

import com.task.translation.repository.TranslateHistoryRepository;
import com.task.translation.service.UserService;
import com.task.translation.service.dto.TranslationHistoryDTO;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TranslationHistoryService {

    private final Logger log = LoggerFactory.getLogger(TranslationHistoryService.class);

    private final TranslateHistoryRepository translateHistoryRepository;

    private final UserService userService;

    public TranslationHistoryService(TranslateHistoryRepository translateHistoryRepository, UserService userService) {
        this.translateHistoryRepository = translateHistoryRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public Page<TranslationHistoryDTO> getAllTranslationForUser(Pageable pageable) {
        return translateHistoryRepository.findAllByUserId(userService.getCurrentUserId(), pageable).map(TranslationHistoryDTO::new);
    }

    public void deleteTranslation(List<Long> ids) {
        translateHistoryRepository.deleteAllWithIds(ids);
        log.debug("Deleted translation: {}", ids);
    }

    public Optional<TranslationHistoryDTO> updateTranslationRank(TranslationHistoryDTO translationHistoryDTO) {
        log.debug("Deleted translation: {}", translationHistoryDTO);

        Optional<TranslationHistoryDTO> updatedHistoryDTO = Optional
            .of(translateHistoryRepository.findById(translationHistoryDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(
                translation -> {
                    translation.setRank(translationHistoryDTO.getRank());
                    translateHistoryRepository.save(translation);
                    return translation;
                }
            )
            .map(TranslationHistoryDTO::new);

        return updatedHistoryDTO;
    }
}
