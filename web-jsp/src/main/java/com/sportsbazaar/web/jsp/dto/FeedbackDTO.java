package com.sportsbazaar.web.jsp.dto;

import java.io.Serializable;

public class FeedbackDTO implements Serializable {

	private static final long serialVersionUID = -5681081155291632136L;

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String feedbackMessage;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFeedbackMessage() {
		return feedbackMessage;
	}

	public void setFeedbackMessage(String feedbackMessage) {
		this.feedbackMessage = feedbackMessage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FeedbackDTO [firstName=").append(firstName).append(", lastName=").append(lastName)
				.append(", email=").append(email).append(", feedbackMessage=").append(feedbackMessage).append("]");
		return builder.toString();
	}
	
}
