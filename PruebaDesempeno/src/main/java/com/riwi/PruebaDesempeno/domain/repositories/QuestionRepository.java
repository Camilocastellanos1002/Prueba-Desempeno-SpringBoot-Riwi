package com.riwi.PruebaDesempeno.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.PruebaDesempeno.domain.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    
}
