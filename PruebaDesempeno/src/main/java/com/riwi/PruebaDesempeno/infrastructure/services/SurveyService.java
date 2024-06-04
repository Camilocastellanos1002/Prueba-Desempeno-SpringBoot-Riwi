package com.riwi.PruebaDesempeno.infrastructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.PruebaDesempeno.api.dto.request.SurveyReq;
import com.riwi.PruebaDesempeno.api.dto.response.QuestionBasicResp;
import com.riwi.PruebaDesempeno.api.dto.response.SurveyResp;
import com.riwi.PruebaDesempeno.domain.entities.Question;
import com.riwi.PruebaDesempeno.domain.entities.Survey;
import com.riwi.PruebaDesempeno.domain.repositories.SurveyRepository;
import com.riwi.PruebaDesempeno.infrastructure.abstract_services.ISurveyService;
import com.riwi.PruebaDesempeno.util.enums.SortType;
import com.riwi.PruebaDesempeno.util.exceptions.BadRequestException;
import com.riwi.PruebaDesempeno.util.messages.ErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class SurveyService implements ISurveyService {
    
    /*dependencias */
        @Autowired
        private final SurveyRepository surveyRepository;
    
    /*Metodos CRUD */
        @Override
        public SurveyResp create(SurveyReq request) {

            Survey survey = this.surveyReqToEntity(request); //creacion de la entidad pregunta desdel el request

            survey.setQuestions(new ArrayList<>());

            return this.surveyEntityToResponse(this.surveyRepository.save(survey)); //se regresa un response a partir de la entidad encuesta y se guarda en el repositorio
        }

        @SuppressWarnings("null")
        @Override
        public Page<SurveyResp> getAll(int page, int size, SortType sortType) {
            if (page < 0) page =0; /* si es numero negativo, regresar a la pagina 0 */
                
            PageRequest pagination = null;
            switch (sortType) {
                case NONE -> pagination = PageRequest.of(page, size); 
                case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending()); //organizar de forma ascendente por titulo de lesson
                case DESC -> pagination  = PageRequest.of(page, size,Sort.by(FIELD_BY_SORT).descending()); //organizar de forma descendente por titulo de lesson
            }

            return this.surveyRepository.findAll(pagination).map(this::surveyEntityToResponse); //se nesesita devolver un response de la entidad 
        }

        @Override
        public SurveyResp getById(Integer id) {
            return this.surveyEntityToResponse(this.findSurvey(id)); //se busca la encuesta por id y se construye la endidad del response
        }

        @Override
        public SurveyResp update(SurveyReq request, Integer id) {
            Survey survey = this.findSurvey(id);//se busca la encuesta por id para actualizar

            Survey surveyUpdate = this.surveyReqToEntity(request); //se crea la entidad encuesta desde el request

            surveyUpdate.setId(survey.getId());
            surveyUpdate.setQuestions(survey.getQuestions()); //obtiene la lista de preguntas

            return this.surveyEntityToResponse(this.surveyRepository.save(surveyUpdate)); // se guarda la encuesta actualizada
        }

        @Override
        public void delete(Integer id) {
            this.surveyRepository.delete(this.findSurvey(id)); //se busca la encuesta con el id ingresado y se elmina del repositorio
        }
    
    /*Metodos propios */
        /* entidad to response */
        private SurveyResp surveyEntityToResponse(Survey entity){
            List<QuestionBasicResp> questions = entity.getQuestions() //se obtiene lista de inscripciones
                                                .stream()   //conversion a coleccion
                                                .map(this::questionBasicEntityToResponse) //mapeo de lista de entidades a response
                                                .collect(Collectors.toList());  //coleccion a lista
            
            return SurveyResp.builder()
                    .survey_id(entity.getId())
                    .title(entity.getTitle())
                    .description(entity.getDescription())
                    .creationDate(entity.getCreationDate())
                    .active(entity.getActive())
                    .user_id(entity.getUser().getId())
                    .questions(questions)
                    .build();
        }
        private QuestionBasicResp questionBasicEntityToResponse(Question entity){
            return QuestionBasicResp.builder()
                    .question_id(entity.getId())
                    .text(entity.getText())
                    .type(entity.getType())
                    .active(entity.getActive())
                    .build();
        }
        /* request to entidad */
        private Survey surveyReqToEntity(SurveyReq request){
            return Survey.builder()
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .creationDate(request.getCreationDate())
                    .active(request.getActive())
                    .build();
        }
        /* buscador */
        private Survey findSurvey (Integer id){
            //funcion para buscar usuario
             return this.surveyRepository.findById(id).orElseThrow(()-> new BadRequestException(ErrorMessage.idNotFound("Survey")));
        }
    
}
