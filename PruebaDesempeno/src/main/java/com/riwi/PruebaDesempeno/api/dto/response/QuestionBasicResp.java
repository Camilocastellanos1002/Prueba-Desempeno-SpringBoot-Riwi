package com.riwi.PruebaDesempeno.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBasicResp {
    
    private int question_id;

    private String text;

    private String type;

    private Boolean active;
}
