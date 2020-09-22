package com.chb.scheduler.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDTO {

    private Long id;

    private String title;

    private String content;

    private String createDate;
}
