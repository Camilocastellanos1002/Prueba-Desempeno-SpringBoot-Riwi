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

import com.riwi.PruebaDesempeno.api.dto.response.UserResp;
import com.riwi.PruebaDesempeno.infrastructure.abstract_services.IUserService;
import com.riwi.PruebaDesempeno.util.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping(path = "/users")
@Data
@AllArgsConstructor
public class UserController {
    
    /*Inyeccion de dependencias */
        @Autowired
        private final IUserService userService;

    /*Peticiones HTTP */
        @GetMapping
        @Operation(summary = "Obtiene los usuarios de forma paginada y organizada")
        public ResponseEntity<Page<UserResp>>getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestHeader(required = false)SortType sortType
        ){
            if (Objects.isNull(sortType)) {
                sortType = SortType.NONE;
            }
            return ResponseEntity.ok(this.userService.getAll(page-1, size, sortType));
        }
}
