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

    //주석 추가... 샘플
    @PostMapping
    public ResponseEntity<JSONResult> createDiary(DiaryDTO diaryDTO) {
        Diary diary = diaryService.save(diaryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success(diary,"저장 성공"));
    }


    @GetMapping
    public ResponseEntity<JSONResult> getDiaryList() {
        return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(diaryService.getList()));
    }

    @DeleteMapping
    public ResponseEntity<JSONResult> deleteAllDiary() {
        diaryService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null,"모든 데이터 삭제 성공"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<JSONResult> deleteDiary(@PathVariable(value = "id") Long id) {
        diaryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(null,"데이터 삭제 성공"));
    }

}
