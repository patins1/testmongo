package com.kiegeland.polls;

public class Choice {

	public Choice() {

	}

	public Choice(String choice, String url, int votes) {
		this.choice = choice;
		this.url = url;
		this.votes = votes;
	}

	public String choice;
	public String url;
	public int votes;

}
