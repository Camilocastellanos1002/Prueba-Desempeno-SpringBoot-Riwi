package com.riwi.PruebaDesempeno.infrastructure.abstract_services;

import com.riwi.PruebaDesempeno.api.dto.request.SurveyReq;
import com.riwi.PruebaDesempeno.api.dto.response.SurveyResp;

public interface ISurveyService  extends CRUDService<SurveyReq, SurveyResp, Integer>{
    public final String FIELD_BY_SORT = "title"; /*Variable a utilizar para la paginacion */
}
