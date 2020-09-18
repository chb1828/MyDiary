package com.chb.scheduler.domain.controller;

import com.chb.scheduler.domain.dto.DiaryDTO;
import com.chb.scheduler.domain.dto.JSONResult;
import com.chb.scheduler.domain.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity createDiary(DiaryDTO diaryDTO) {
        diaryService.save(diaryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success(null,"저장 성공"));
    }

    @PutMapping
    public ResponseEntity updateDiary(DiaryDTO diaryDTO) {
        diaryService.update(diaryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success(null,"수정 성공"));
    }
}
