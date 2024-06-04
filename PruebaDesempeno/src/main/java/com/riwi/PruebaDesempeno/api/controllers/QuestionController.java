package com.riwi.PruebaDesempeno.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.PruebaDesempeno.api.dto.request.QuestionReq;
import com.riwi.PruebaDesempeno.api.dto.response.QuestionResp;
import com.riwi.PruebaDesempeno.infrastructure.abstract_services.IQuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping(path = "/questions")
@Data
@AllArgsConstructor
public class QuestionController {

    /*Inyeccion de dependencias */
        @Autowired
        private final IQuestionService questionService;

    /*Peticiones HTTP */

    @PostMapping
        @Operation(summary = "Crea una pregunta")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
        public ResponseEntity<QuestionResp> create(
                @Validated QuestionReq request) {
            return ResponseEntity.ok(this.questionService.create(request));
        }
}
