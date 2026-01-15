package com.putin.question_service.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.putin.question_service.Model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(
        value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RAND()",
        nativeQuery = true
    )
    List<Integer> findRandomQuestionByCategory(
            @org.springframework.data.repository.query.Param("category") String category,
            Integer numQuestions
    );
}
