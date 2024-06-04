package com.riwi.PruebaDesempeno.infrastructure.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.PruebaDesempeno.api.dto.request.OptionQuestionReq;
import com.riwi.PruebaDesempeno.api.dto.response.OptionQuestionResp;
import com.riwi.PruebaDesempeno.domain.entities.OptionQuestion;
import com.riwi.PruebaDesempeno.domain.repositories.OptionQuestionRepository;
import com.riwi.PruebaDesempeno.infrastructure.abstract_services.IOptionQuestionService;
import com.riwi.PruebaDesempeno.util.enums.SortType;
import com.riwi.PruebaDesempeno.util.exceptions.BadRequestException;
import com.riwi.PruebaDesempeno.util.messages.ErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class OptionQuestionService implements IOptionQuestionService{

    @Autowired
    private final OptionQuestionRepository optionQuestionRepository;

    /*MEtodos CRUD */
        @Override
        public OptionQuestionResp create(OptionQuestionReq request) {
            OptionQuestion optionQuestion = this.optionQuestionReqToEntity(request); //creacion de la entidad opcion de pregunta desdel el request

            return this.optionQuestionEntityToResponse(this.optionQuestionRepository.save(optionQuestion)); //se regresa un response a partir de la entidad opcion de pregunta y se guarda en el repositorio
        }

        @SuppressWarnings("null")
        @Override
        public Page<OptionQuestionResp> getAll(int page, int size, SortType sortType) {
            if (page < 0) page =0; /* si es numero negativo, regresar a la pagina 0 */
                
            PageRequest pagination = null;
            switch (sortType) {
                case NONE -> pagination = PageRequest.of(page, size); 
                case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending()); //organizar de forma ascendente por titulo de lesson
                case DESC -> pagination  = PageRequest.of(page, size,Sort.by(FIELD_BY_SORT).descending()); //organizar de forma descendente por titulo de lesson
            }

            return this.optionQuestionRepository.findAll(pagination).map(this::optionQuestionEntityToResponse); //se nesesita devolver un response de la entidad 
        }

        @Override
        public OptionQuestionResp getById(Integer id) {
            return this.optionQuestionEntityToResponse(this.finndOptionQuestion(id)); //se busca la opcion por id y se construye la endidad del response
        }

        @Override
        public OptionQuestionResp update(OptionQuestionReq request, Integer id) {
            OptionQuestion optionQuestion = this.finndOptionQuestion(id);//se busca la opcion de pregunta por id para actualizar

            OptionQuestion optionQuestionUpdate = this.optionQuestionReqToEntity(request); //se crea la entidad pregunta desde el request

            optionQuestionUpdate.setQuestion(optionQuestion.getQuestion()); //se guarda las opciones

            return this.optionQuestionEntityToResponse(this.optionQuestionRepository.save(optionQuestionUpdate)); // se guarda la pregunta actualizada
        }

        @Override
        public void delete(Integer id) {
            this.optionQuestionRepository.delete(this.finndOptionQuestion(id)); //se busca la opcion con el id ingresado y se elmina del repositorio
        }
    /* Metodos propios */
        /* Entiddad a resp */
        private OptionQuestionResp optionQuestionEntityToResponse (OptionQuestion entity){
            return OptionQuestionResp.builder()
                    .option_id(entity.getId())
                    .text(entity.getText())
                    .active(entity.getActive())
                    .question_id(entity.getQuestion().getId())
                    .build();
        }
        /*req to entidad */
        private OptionQuestion optionQuestionReqToEntity(OptionQuestionReq request){
            return OptionQuestion.builder()
                    .id(request.getId_question())
                    .text(request.getText())
                    .build();
        }
        /*buscador */
        private OptionQuestion finndOptionQuestion(Integer id){
            //funcion para buscar opcion pregunta
             return this.optionQuestionRepository.findById(id).orElseThrow(()-> new BadRequestException(ErrorMessage.idNotFound("OptionQuestion")));
        }
    
}
