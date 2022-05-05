// package com.example.controller;

// import java.util.List;
// import java.util.Optional;

// import com.example.model.Question;
// import com.example.service.QuestionService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.PutMapping;

// @RestController
// @CrossOrigin
// @RequestMapping(value="/api/question")
// public class QuestionControleur {

// @Autowired
// private QuestionService questionService;

// @GetMapping("/")
// public List<Question> allItems(){
// return questionService.getAllQuestion();
// }

// @GetMapping("/{questionID}")
// public Question read(@PathVariable(value = "questionID") int id){
// Optional<Question> question = questionService.getQuestion(id);

// if(!question.isPresent()){
// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune question
// n'existe à cet id");
// }

// return question.get();
// }

// @PostMapping("/create")
// public Question create(@RequestBody Question question){
// return questionService.saveQuestion(question);
// }

// @PutMapping(value="/{questionID}")
// public Question update(@PathVariable(value = "questionID") int id,
// @RequestBody Question question) {

// if(id != question.getId()){
// throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "l'id de la
// question passé en paramètre est différent de celui saisi.");
// }

// Optional<Question> questionTMP = questionService.getQuestion(id);

// if(!questionTMP.isPresent()){
// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun média de
// description n'existe à cet id");
// }

// Question newQuestion = questionTMP.get();
// newQuestion.setRang(question.getRang());
// newQuestion.setLabel(question.getLabel());

// return questionService.saveQuestion(newQuestion);
// }

// @DeleteMapping("/{questionID}")
// public void delete(@PathVariable(value = "questionID") int id){
// questionService.deleteQuestion(id);
// }

// }
