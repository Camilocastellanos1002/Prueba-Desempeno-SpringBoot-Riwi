package com.riwi.PruebaDesempeno.infrastructure.services;

import org.springframework.data.domain.Page;

import com.riwi.PruebaDesempeno.api.dto.request.QuestionReq;
import com.riwi.PruebaDesempeno.api.dto.response.QuestionResp;
import com.riwi.PruebaDesempeno.infrastructure.abstract_services.IQuestionService;
import com.riwi.PruebaDesempeno.util.enums.SortType;

public class QuestionService implements IQuestionService{

    @Override
    public QuestionResp create(QuestionReq request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Page<QuestionResp> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public QuestionResp getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public QuestionResp update(QuestionReq request, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
