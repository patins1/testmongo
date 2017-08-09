package com.kiegeland.polls;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PollsApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testVot() {
		Question original = this.restTemplate.getForObject("/mockdata", Question.class);
		this.restTemplate.getForObject("/vote/questions/1/choices/3", Choice.class);
		Choice choice = this.restTemplate.getForObject("/questions/1/choices/3", Choice.class);
		Assert.assertEquals(original.choices.get(2).votes + 1, choice.votes);
	}

}
