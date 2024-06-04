package com.riwi.PruebaDesempeno.infrastructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.PruebaDesempeno.api.dto.request.QuestionReq;
import com.riwi.PruebaDesempeno.api.dto.response.OptionQuestionResp;
import com.riwi.PruebaDesempeno.api.dto.response.QuestionResp;
import com.riwi.PruebaDesempeno.domain.entities.OptionQuestion;
import com.riwi.PruebaDesempeno.domain.entities.Question;
import com.riwi.PruebaDesempeno.domain.repositories.QuestionRepository;
import com.riwi.PruebaDesempeno.infrastructure.abstract_services.IQuestionService;
import com.riwi.PruebaDesempeno.util.enums.SortType;
import com.riwi.PruebaDesempeno.util.exceptions.BadRequestException;
import com.riwi.PruebaDesempeno.util.messages.ErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class QuestionService implements IQuestionService{

        
    /* dependencias */
        @Autowired
        private final QuestionRepository questionRepository;

    /*metodos CRUD */
        @Override
        public QuestionResp create(QuestionReq request) {
            Question question = this.questionReqToEntity(request); //creacion de la entidad pregunta desdel el request

            question.setOptionQuestions(new ArrayList<>()); //lista vacia de opciones

            return this.questionEntityToResponse(this.questionRepository.save(question)); //se regresa un response a partir de la entidad pregunta y se guarda en el repositorio
        }

        @SuppressWarnings("null")
        @Override
        public Page<QuestionResp> getAll(int page, int size, SortType sortType) {
            if (page < 0) page =0; /* si es numero negativo, regresar a la pagina 0 */
                
            PageRequest pagination = null;
            switch (sortType) {
                case NONE -> pagination = PageRequest.of(page, size); 
                case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending()); //organizar de forma ascendente por titulo de lesson
                case DESC -> pagination  = PageRequest.of(page, size,Sort.by(FIELD_BY_SORT).descending()); //organizar de forma descendente por titulo de lesson
            }

            return this.questionRepository.findAll(pagination).map(this::questionEntityToResponse); //se nesesita devolver un response de la entidad 
        }

        @Override
        public QuestionResp getById(Integer id) {
            return this.questionEntityToResponse(this.findQuestion(id)); //se busca la pregunta por id y se construye la endidad del response
        }

        @Override
        public QuestionResp update(QuestionReq request, Integer id) {
            Question question = this.findQuestion(id); //se busca la pregunta por id para actualizar

            Question questionUpdate = this.questionReqToEntity(request); //se crea la entidad pregunta desde el request

            questionUpdate.setOptionQuestions(question.getOptionQuestions()); //se guarda las opciones

            return this.questionEntityToResponse(this.questionRepository.save(questionUpdate)); // se guarda la pregunta actualizada
        }
        


        @Override
        public void delete(Integer id) {
            this.questionRepository.delete(this.findQuestion(id)); //se busca la pregunta con el id ingresado y se elmina del repositorio
        }
    /* Metodos propios */
        
        /* Entidad a response */
            private QuestionResp questionEntityToResponse(Question entity){
                /* valor a las tablas relacionadas */
                List<OptionQuestionResp> optionQuestion = entity.getOptionQuestions() //se obtiene lista de inscripciones
                                                            .stream()   //conversion a coleccion
                                                            .map(this::optionQuestionEntityToResponse) //mapeo de lista de entidades a response
                                                            .collect(Collectors.toList());  //coleccion a lista

                return QuestionResp.builder()
                        .question_id(entity.getId())
                        .text(entity.getText())
                        .type(entity.getType())
                        .active(entity.getActive())
                        .survey_id(entity.getSurvey().getId())
                        .optionQuestions(optionQuestion)
                        .build();                                     
            }
            private OptionQuestionResp optionQuestionEntityToResponse(OptionQuestion entity){
                return OptionQuestionResp.builder()
                        .option_id(entity.getId())
                        .text(entity.getText())
                        .active(entity.getActive())
                        .question_id(entity.getQuestion().getId())
                        .build();
            }
            /*
            private QuestionBasicResp questionBasicEntityToResponse(Question entity){
                return QuestionBasicResp.builder()
                        .question_id(entity.getId())
                        .text(entity.getText())
                        .type(entity.getType())
                        .active(entity.getActive())
                        .build();
            }*/

        /* request a entidad */
        private Question questionReqToEntity(QuestionReq request){
            return Question.builder()
                    .text(request.getText())
                    .type(request.getType())
                    .active(request.getActive())
                    .build();
        }

        /* buscadores */
        private Question findQuestion(Integer id){
             //funcion para buscar usuario
             return this.questionRepository.findById(id).orElseThrow(()-> new BadRequestException(ErrorMessage.idNotFound("Question")));
        }
}
