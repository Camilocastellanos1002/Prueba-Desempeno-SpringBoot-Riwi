package com.riwi.PruebaDesempeno.infrastructure.abstract_services;

import com.riwi.PruebaDesempeno.api.dto.request.UserReq;
import com.riwi.PruebaDesempeno.api.dto.response.UserResp;

public interface IUserService extends CRUDService<UserReq, UserResp, Integer>{
    public final String FIELD_BY_SORT = "name"; /*Variable a utilizar para la paginacion */
}
