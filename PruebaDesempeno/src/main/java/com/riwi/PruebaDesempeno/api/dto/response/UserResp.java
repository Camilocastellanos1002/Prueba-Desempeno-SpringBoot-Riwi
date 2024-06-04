package com.riwi.PruebaDesempeno.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResp {
    
    private int user_id;

    private String user_name;

    private String password;

    private String email;

    private Boolean active;

    private List<SurveyBasicResp> Surveys; /* respondo con la lista de encuestas*/

}
