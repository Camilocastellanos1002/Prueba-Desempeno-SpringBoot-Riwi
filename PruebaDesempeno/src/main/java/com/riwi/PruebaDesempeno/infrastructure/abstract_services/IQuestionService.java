package com.riwi.PruebaDesempeno.infrastructure.abstract_services;

import com.riwi.PruebaDesempeno.api.dto.request.QuestionReq;
import com.riwi.PruebaDesempeno.api.dto.response.QuestionResp;

public interface IQuestionService extends CRUDService<QuestionReq, QuestionResp, Integer>{
    
    public final String FIELD_BY_SORT = "text"; /*Variable a utilizar para la paginacion */
}
