package com.putin.question_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.putin.question_service.Model.Question;
import com.putin.question_service.Model.QuestionWrapper;
import com.putin.question_service.Model.Response;
import com.putin.question_service.service.QuestionService;



@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/allquestions")
	public ResponseEntity<List<Question>> getALLquestions() {
		
		return questionService.getALLquestions();
		
	} 
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		return questionService.getQuestionByCategory(category);
	}
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(
		@RequestParam String categoryName,@RequestParam Integer numQuestions)
		{
		return questionService.getQuestionsForQuiz(categoryName,numQuestions);
	}
	
    @PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds){
		return questionService.getQuestionFromId(questionIds);
	}
     
	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
	{
		return questionService.getScore(responses);
	}


}
