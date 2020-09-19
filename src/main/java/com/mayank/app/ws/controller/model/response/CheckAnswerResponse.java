/* Copyright 2020  All Rights Reserved.
 * MAYANK CONFIDENTIAL
 *
 * Title      		： 	com.mayank.app.ws.controller.model.response.CheckAnswerResponse
 * Description      ：   Response Object being provided by service as a check result of user's answer
 * 
 * Update History
 * 2020/09/19  Mayank  Initial Version
 */
package com.mayank.app.ws.controller.model.response;

/**
 * Object in ResponsetBody while making response to user with check result.
 * 
 * @author Mayank
 */
public class CheckAnswerResponse {

	private String resultMessage;

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
