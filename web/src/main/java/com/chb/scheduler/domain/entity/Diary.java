package com.chb.scheduler.domain.entity;

import com.chb.scheduler.domain.entity.audit.DateAudit;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Diary extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String content;

    @NotNull
    private LocalDate createDate;

}
