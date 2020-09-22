package com.chb.scheduler.domain.service;

import com.chb.scheduler.domain.dto.DiaryDTO;
import com.chb.scheduler.domain.entity.Diary;
import com.chb.scheduler.domain.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public Diary save(DiaryDTO diaryDTO) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate createDate = LocalDate.parse(diaryDTO.getCreateDate(), format);
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

    public void delete(Long id) {
        diaryRepository.deleteById(id);
    }

    public void deleteAll() {
        diaryRepository.deleteAll();
    }
}
