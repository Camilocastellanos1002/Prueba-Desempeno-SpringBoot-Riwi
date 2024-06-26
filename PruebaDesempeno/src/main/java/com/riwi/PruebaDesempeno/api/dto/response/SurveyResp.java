package com.riwi.PruebaDesempeno.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResp {

    private int survey_id;

    private String title;

    private String description;

    private LocalDateTime creationDate;

    private Boolean active;

    private int user_id;

    private List<QuestionBasicResp> questions;
}
