package com.riwi.PruebaDesempeno.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionQuestionReq {
    
    @NotBlank(message = "the Option question is required")
    private String text;
    
    @NotNull(message = "the user status is required")
    private Boolean active;

    @NotNull(message = "the id question is required")
    private int id_question;
}
