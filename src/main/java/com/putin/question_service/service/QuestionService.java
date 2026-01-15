package com.putin.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.putin.question_service.Model.Question;
import com.putin.question_service.Model.QuestionWrapper;
import com.putin.question_service.Model.Response;
import com.putin.question_service.dao.QuestionDao;



@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getALLquestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
		     return new ResponseEntity<>( questionDao.findByCategory(category),HttpStatus.OK);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}
	public ResponseEntity<String> addQuestion(Question question) {
		questionDao.save(question);
		return new ResponseEntity<>("succes",HttpStatus.CREATED);
		
		
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		List<Integer> questions=questionDao.findRandomQuestionByCategory(categoryName, numQuestions); 
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
       List<QuestionWrapper> wrappers=new ArrayList<>();
	   List<Question> questions=new ArrayList<>();

	   for(Integer id:questionIds){
		questions.add(questionDao.findById(id).get());
	   }

	   for(Question question:questions){
		QuestionWrapper wrapper = new QuestionWrapper();
		wrapper.setId(question.getId());
		wrapper.setQuestionTitle(question.getQuestionTitle());
		wrapper.setOption1(question.getOption1());
		wrapper.setOption2(question.getOption2());
		wrapper.setOption2(question.getOption3());
		wrapper.setOption2(question.getOption4());
		wrappers.add(wrapper);
	   }
	   return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right=0;
		for(Response response: responses){
			Question question = questionDao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer()))
				right++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
