package com.riwi.PruebaDesempeno.infrastructure.abstract_services;

import com.riwi.PruebaDesempeno.api.dto.request.OptionQuestionReq;
import com.riwi.PruebaDesempeno.api.dto.response.OptionQuestionResp;

public interface IOptionQuestionService extends CRUDService<OptionQuestionReq, OptionQuestionResp, Integer>{
    
    public final String FIELD_BY_SORT = "text"; /*Variable a utilizar para la paginacion */
}
