package com.riwi.PruebaDesempeno.domain.entities;

import com.riwi.PruebaDesempeno.util.enums.Active;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "optionquestions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionQuestion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String text;

    private Active active;

    /*Relacion con question */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id",referencedColumnName = "id")
    private Question question;

}
