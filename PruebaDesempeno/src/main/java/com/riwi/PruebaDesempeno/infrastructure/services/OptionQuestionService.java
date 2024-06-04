package com.riwi.PruebaDesempeno.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.PruebaDesempeno.api.dto.request.OptionQuestionReq;
import com.riwi.PruebaDesempeno.api.dto.response.OptionQuestionResp;
import com.riwi.PruebaDesempeno.domain.repositories.OptionQuestionRepository;
import com.riwi.PruebaDesempeno.infrastructure.abstract_services.IOptionQuestionService;
import com.riwi.PruebaDesempeno.util.enums.SortType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class OptionQuestionService implements IOptionQuestionService{

    @Autowired
    private final OptionQuestionRepository optionQuestionRepository;
    @Override
    public OptionQuestionResp create(OptionQuestionReq request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Page<OptionQuestionResp> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public OptionQuestionResp getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public OptionQuestionResp update(OptionQuestionReq request, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
