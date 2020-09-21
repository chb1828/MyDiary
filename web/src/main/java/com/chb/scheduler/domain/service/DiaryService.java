package com.chb.scheduler.domain.service;

import com.chb.scheduler.domain.dto.DiaryDTO;
import com.chb.scheduler.domain.entity.Diary;
import com.chb.scheduler.domain.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public Diary save(DiaryDTO diaryDTO) {
        Diary diary = diaryRepository.save(Diary.builder()
                .title(diaryDTO.getTitle())
                .content(diaryDTO.getContent())
                .createDate(diaryDTO.getCreateDate())
                .build());
        return diary;
    }

    public Diary update(DiaryDTO diaryDTO) {
        if(diaryDTO.getId()==null) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
        LocalDateTime createDate = diaryRepository.findById(diaryDTO.getId()).get().getCreateDate();
        Diary diary = diaryRepository.save(Diary.builder()
                .id(diaryDTO.getId())
                .title(diaryDTO.getTitle())
                .content(diaryDTO.getContent())
                .createDate(createDate)
                .build());
        return diary;
    }

    public List<Diary> getList() {
        return diaryRepository.findAll();
    }


}
