package com.riwi.PruebaDesempeno.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.PruebaDesempeno.api.dto.response.SurveyResp;
import com.riwi.PruebaDesempeno.infrastructure.abstract_services.ISurveyService;
import com.riwi.PruebaDesempeno.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping(path = "/surveys")
@Data
@AllArgsConstructor
public class SurveyController {
    /* inyeccion de dependencias */
    @Autowired
    private final ISurveyService surveyService;

    /*Peticiones HTTP */
    @GetMapping
        @Operation(summary = "Obtiene las encuestas de forma paginada y organizada")
        public ResponseEntity<Page<SurveyResp>>getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestHeader(required = false)SortType sortType
        ){
            if (Objects.isNull(sortType)) {
                sortType = SortType.NONE;
            }
            return ResponseEntity.ok(this.surveyService.getAll(page-1, size, sortType));
        }
}
