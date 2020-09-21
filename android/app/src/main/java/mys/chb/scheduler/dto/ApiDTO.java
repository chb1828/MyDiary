package mys.chb.scheduler.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiDTO {

    private String result;
    private String message;
    private List<DiaryDTO> data;
}
