package com.chb.scheduler.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDTO {

    private Long id;

    private String title;

    private String content;
}
