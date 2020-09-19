package com.mayank.app.ws;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.mayank.app.ws.controller.model.request.AnswerdetailsPostRequest;
import com.mayank.app.ws.controller.model.response.ReturnQuery;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	@Order(1)
	public void sendQuestion_whenClientRequests_receiveOK() {
		
		ResponseEntity<Object> response = testRestTemplate.getForEntity("/", Object.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
	
	
	@Test
	@Order(2)
	public void sendQuestion_whenClientRequests_receiveQuestionObject_OK() {
		
		ResponseEntity<ReturnQuery> response = testRestTemplate.getForEntity("/", ReturnQuery.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getClass()).isEqualTo(ReturnQuery.class);
		assertThat(response.getBody().getNumList().size()).isEqualTo(3);
		
	}
		
	
	@Test
	@Order(3)
	public void postAnswers_byClient_toServer_receiveNotOK() {
		
		AnswerdetailsPostRequest answerdetailsPostRequest = new AnswerdetailsPostRequest();
		
		answerdetailsPostRequest.setNum1(10);
		answerdetailsPostRequest.setNum2(9);
		answerdetailsPostRequest.setNum3(47);
		answerdetailsPostRequest.setAnswer(66);
		
		ResponseEntity<Object> response = testRestTemplate.postForEntity("/", answerdetailsPostRequest, Object.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
	}
	
	@Test
	@Order(4)
	public void postAnswers_byClient_toServer_InvalidRequest() {
		
		AnswerdetailsPostRequest answerdetailsPostRequest = new AnswerdetailsPostRequest();
		
		answerdetailsPostRequest.setNum1(3);
		answerdetailsPostRequest.setNum2(59);
		answerdetailsPostRequest.setAnswer(87);
		
		ResponseEntity<Object> response = testRestTemplate.postForEntity("/", answerdetailsPostRequest, Object.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
	}
	
	@Test
	@Order(5)
	public void postAnswers_byClient_toServer_receiveOK() {
		
		ResponseEntity<ReturnQuery> response = testRestTemplate.getForEntity("/", ReturnQuery.class);
		
		AnswerdetailsPostRequest answerdetailsPostRequest = new AnswerdetailsPostRequest();
		
		int num1 = response.getBody().getNumList().get(0);
		int num2 = response.getBody().getNumList().get(1);
		int num3 = response.getBody().getNumList().get(2);
		
		answerdetailsPostRequest.setNum1(num1);
		answerdetailsPostRequest.setNum2(num2);
		answerdetailsPostRequest.setNum3(num3);
		answerdetailsPostRequest.setAnswer(num1 + num2 + num3);
		
		ResponseEntity<Object> resultResponse = testRestTemplate.postForEntity("/", answerdetailsPostRequest, Object.class);
		assertThat(resultResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
		
		
	
}
