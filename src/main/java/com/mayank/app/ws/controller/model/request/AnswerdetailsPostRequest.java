/* Copyright 2020  All Rights Reserved.
 * MAYANK CONFIDENTIAL
 *
 * Title      		： 	com.mayank.app.ws.controller.model.request.AnswerdetailsPostRequest
 * Description      ：   Request Object in the RequestBody when user asks Service to check the answer of sum problem
 * 
 * Update History
 * 2020/09/19  Mayank  Initial Version
 */
package com.mayank.app.ws.controller.model.request;

import javax.validation.constraints.NotNull;

/**
 * Class in RequestBody while making request to service to confirm the answer.
 * 
 * @author Mayank
 */
public class AnswerdetailsPostRequest {

	@NotNull(message = "answer can't be null")
	private int answer;

	@NotNull(message = "num1 can't be null")
	private int num1;

	@NotNull(message = "num2 can't be null")
	private int num2;

	@NotNull(message = "num3 can't be null")
	private int num3;

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public int getNum3() {
		return num3;
	}

	public void setNum3(int num3) {
		this.num3 = num3;
	}

}
