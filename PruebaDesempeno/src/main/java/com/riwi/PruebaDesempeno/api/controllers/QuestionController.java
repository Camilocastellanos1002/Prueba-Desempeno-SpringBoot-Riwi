package com.riwi.PruebaDesempeno.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.PruebaDesempeno.api.dto.request.QuestionReq;
import com.riwi.PruebaDesempeno.api.dto.response.QuestionResp;
import com.riwi.PruebaDesempeno.infrastructure.abstract_services.IQuestionService;
import com.riwi.PruebaDesempeno.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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

        @GetMapping
        @Operation(summary = "Obtiene las preguntas de forma paginada y organizada")
        public ResponseEntity<Page<QuestionResp>>getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestHeader(required = false)SortType sortType
        ){
            if (Objects.isNull(sortType)) {
                sortType = SortType.NONE;
            }
            return ResponseEntity.ok(this.questionService.getAll(page-1, size, sortType));
        }
        
        @PutMapping(path = "/{id}")
        @Operation(summary = "Actualiza una pregunta ")
        @ApiResponse(responseCode = "400", description = "Cuando el id no es valido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
        public ResponseEntity<QuestionResp> update(
                @PathVariable Integer id, @Validated @RequestBody QuestionReq request) {
            return ResponseEntity.ok(this.questionService.update(request, id));
        }

}
