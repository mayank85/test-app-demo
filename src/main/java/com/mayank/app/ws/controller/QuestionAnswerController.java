/* Copyright 2020  All Rights Reserved.
 * MAYANK CONFIDENTIAL
 *
 * Title      		： 	com.mayank.app.ws.controller.QuestionAnswerController
 * Description      ：   Question/Answer and check result exchange between User/Service.
 * 
 * Update History
 * 2020/09/19  Mayank  Initial Version
 */
package com.mayank.app.ws.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mayank.app.ws.controller.model.request.AnswerdetailsPostRequest;
import com.mayank.app.ws.controller.model.response.CheckAnswerResponse;
import com.mayank.app.ws.controller.model.response.ReturnQuery;

/**
 * Controller for Question Answer exchange service
 * 
 * <pre>
 * When User makes a request to server to provide it with
 * a summation related problem, the server responds with a 
 * question. User reverts back to server with the answer.
 * Server check and verify the original question and expected 
 * answer and then responds accordingly.
 * </pre>
 *
 * @author Mayank
 */
@RestController
@RequestMapping("/")
public class QuestionAnswerController {

	@Autowired
	private Environment env;

	List<Integer> myNumList;
	private int num1;
	private int num2;
	private int num3;

	/**
	 * 
	 * Get Question from service
	 * 
	 * @param firstIntro
	 * @return ResponseEntity<ReturnQuery>
	 */
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ReturnQuery> getQuery(
			@RequestParam(value = "iceBreaker", defaultValue = "Hey Mayank! Can you provide me a question with numbers to add?") String firstIntro)

	{
		// Getting the value of random number range from property file
		String prop = env.getProperty("test.number.limit");
		// Parsing the random number range value into integer
		int randomNumber = Integer.parseInt(prop);
		// Generating 3 random integer numbers from the given range
		num1 = (int) (Math.random() * randomNumber + 1);
		num2 = (int) (Math.random() * randomNumber + 1);
		num3 = (int) (Math.random() * randomNumber + 1);
		// Creating a new arraylist with the generated numbers and save as variable on
		// the class level for future reference
		myNumList = new ArrayList<>();
		myNumList.add(num1);
		myNumList.add(num2);
		myNumList.add(num3);

		// Creating a response object and set the values
		ReturnQuery returnQuery = new ReturnQuery();
		returnQuery.setNumList(myNumList);
		returnQuery.setFirstIntro(firstIntro);
		returnQuery.setQuestionContents(
				String.format("Here you go, solve the question: Please sum the numbers %d, %d, %d", num1, num2, num3));

		return new ResponseEntity<ReturnQuery>(returnQuery, HttpStatus.OK);

	}

	/**
	 * 
	 * Confirm Answer service
	 * 
	 * @param answerDetails
	 * @return ResponseEntity<CheckAnswerResponse>
	 */
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CheckAnswerResponse> confirmAnswer(
			@Valid @RequestBody AnswerdetailsPostRequest answerDetails) {
		// Extract the user answer and question from requestBody
		int clientAnswer = answerDetails.getAnswer();
		int num1 = answerDetails.getNum1();
		int num2 = answerDetails.getNum2();
		int num3 = answerDetails.getNum3();

		// Calculate the sum with original numbers at service side
		int serverAnswer = sum(myNumList);

		// Create a new arraylist object with numbers received from user side
		List<Integer> tempList = new ArrayList<>();
		tempList.add(num1);
		tempList.add(num2);
		tempList.add(num3);

		// Sorting both lists i.e. original list as well as newly created tempList for
		// fair comparison
		Collections.sort(myNumList);
		Collections.sort(tempList);

		// Getting result after comparison of both lists
		boolean matchWithQuestion = myNumList.equals(tempList);

		// Creating a new response object to be sent in responseBody
		CheckAnswerResponse checkResponse = new CheckAnswerResponse();

		// Checking the different conditions for appropriate response
		if (serverAnswer == clientAnswer) {

			if (matchWithQuestion) {

				checkResponse.setResultMessage("Great! That was bang on the target");

				return new ResponseEntity<CheckAnswerResponse>(checkResponse, HttpStatus.OK);

			} else {

				checkResponse
						.setResultMessage("Sorry! The sum is correct but you have tempered with original question");

				return new ResponseEntity<CheckAnswerResponse>(checkResponse, HttpStatus.EXPECTATION_FAILED);

			}

		} else {

			checkResponse.setResultMessage("Sorry Boss! The sum total is wrong! Please try again");

			return new ResponseEntity<CheckAnswerResponse>(checkResponse, HttpStatus.BAD_REQUEST);

		}

	}

	/**
	 * 
	 * Summing the original numbers in the question provided by service
	 * 
	 * @param list
	 * @return sumTotal
	 */
	public int sum(List<Integer> list) {
		int sumTotal = 0;

		for (int i : list)
			sumTotal = sumTotal + i;

		return sumTotal;
	}

}
