package com.chb.scheduler.domain.controller;

import com.chb.scheduler.domain.dto.DiaryDTO;
import com.chb.scheduler.domain.dto.JSONResult;
import com.chb.scheduler.domain.entity.Diary;
import com.chb.scheduler.domain.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity<JSONResult> createDiary(DiaryDTO diaryDTO) {
/*        if(diaryDTO.getCreateDate()==null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.fail("생성 날짜 없음"));
        }
        if(diaryDTO.getTitle()==null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.fail("제목 없음"));
        }*/
        Diary diary = diaryService.save(diaryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success(diary,"저장 성공"));
    }

    @PutMapping
    public ResponseEntity<JSONResult> updateDiary(DiaryDTO diaryDTO) {
        Diary diary = diaryService.update(diaryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(diary,"수정 성공"));
    }

    @GetMapping
    public ResponseEntity<JSONResult> getDiaryList() {
        return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(diaryService.getList()));
    }

}
