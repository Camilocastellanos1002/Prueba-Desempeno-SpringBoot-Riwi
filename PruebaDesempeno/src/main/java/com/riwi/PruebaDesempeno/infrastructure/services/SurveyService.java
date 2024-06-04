package com.riwi.PruebaDesempeno.infrastructure.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.PruebaDesempeno.api.dto.request.SurveyReq;
import com.riwi.PruebaDesempeno.api.dto.response.SurveyResp;
import com.riwi.PruebaDesempeno.infrastructure.abstract_services.ISurveyService;
import com.riwi.PruebaDesempeno.util.enums.SortType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class SurveyService implements ISurveyService {
    
    
    @Override
    public SurveyResp create(SurveyReq request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Page<SurveyResp> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public SurveyResp getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public SurveyResp update(SurveyReq request, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
