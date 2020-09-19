/* Copyright 2020  All Rights Reserved.
 * MAYANK CONFIDENTIAL
 *
 * Title      		： 	com.mayank.app.ws.controller.model.response.ReturnQuery
 * Description      ：   Response Object being provided by service in response to user's request for sum problem
 * 
 * Update History
 * 2020/09/19  Mayank  Initial Version
 */

package com.mayank.app.ws.controller.model.response;

import java.util.List;

/**
 * Object in ResponsetBody while making response to user with sum problem.
 * 
 * @author Mayank
 */
public class ReturnQuery {

	private List<Integer> numList;
	private String firstIntro;
	private String questionContents;

	public List<Integer> getNumList() {
		return numList;
	}

	public void setNumList(List<Integer> numList) {
		this.numList = numList;
	}

	public String getFirstIntro() {
		return firstIntro;
	}

	public void setFirstIntro(String firstIntro) {
		this.firstIntro = firstIntro;
	}

	public String getQuestionContents() {
		return questionContents;
	}

	public void setQuestionContents(String questionContents) {
		this.questionContents = questionContents;
	}

}
