package com.chb.scheduler.domain.service;

import com.chb.scheduler.domain.dto.DiaryDTO;
import com.chb.scheduler.domain.entity.Diary;
import com.chb.scheduler.domain.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public void save(DiaryDTO diaryDTO) {
        Diary diary = diaryRepository.save(Diary.builder()
                .title(diaryDTO.getTitle())
                .content(diaryDTO.getContent())
                .build());
    }

    public void update(DiaryDTO diaryDTO) {
        Diary diary = diaryRepository.save(Diary.builder()
                .id(diaryDTO.getId())
                .title(diaryDTO.getTitle())
                .content(diaryDTO.getContent())
                .build());
    }
}
