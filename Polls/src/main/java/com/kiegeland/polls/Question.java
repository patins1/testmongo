package com.kiegeland.polls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Question {

	public Question() {

	}

	@Id
	public String url;

	public String question;
	public String published_at;
	public List<Choice> choices = new ArrayList<Choice>();

}
