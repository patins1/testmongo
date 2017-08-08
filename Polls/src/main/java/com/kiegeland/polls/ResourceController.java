package com.kiegeland.polls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@Autowired
	ResourceRepository repo;

	@RequestMapping("/")
	public Map<String, String> getEntry() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("questions_url", "/questions");
		return result;
	}

	@PostMapping("/createquestion")
	public Question createQuestion(@RequestPart("content") Question res) {
		return repo.save(res);
	}

	@RequestMapping("/questions/{question_id}")
	public Question getQuestion(@PathVariable("question_id") String question_id) {
		return repo.findByUrl("/questions/" + question_id);
	}

	@RequestMapping("/questions/{question_id}/choices/{choice_id}")
	public Choice getChoice(@PathVariable("question_id") String question_id, @PathVariable("choice_id") String choice_id) {
		Question q = getQuestion(question_id);
		if (q != null) {
			String url = "/questions/" + question_id + "/choices/" + choice_id;
			return q.choices.stream().filter((x) -> url.equals(x.url)).findFirst().orElse(null);
		}
		return null;
	}

	@RequestMapping("/vote/questions/{question_id}/choices/{choice_id}")
	public Choice vote(@PathVariable("question_id") String question_id, @PathVariable("choice_id") String choice_id) {
		Question q = getQuestion(question_id);
		if (q != null) {
			String url = "/questions/" + question_id + "/choices/" + choice_id;
			Choice c = q.choices.stream().filter((x) -> url.equals(x.url)).findFirst().orElse(null);
			if (c != null) {
				c.votes += 1;
				repo.save(q);
				return c;
			}
		}
		return null;
	}

	@GetMapping("/listall")
	public List<Question> all() {
		List<Question> result = repo.findAll();
		return result;
	}

	@GetMapping("/mockdata")
	public Question createMockdata() {
		repo.deleteAll();
		Question question = new Question();
		question.url = "/questions/1";
		question.question = "Favourite programming language?";
		question.published_at = "2014-11-11T08:40:51.620Z";
		question.choices.add(new Choice("Swift", "/questions/1/choices/1", 2048));
		question.choices.add(new Choice("Python", "/questions/1/choices/2", 1024));
		question.choices.add(new Choice("Objective-C", "/questions/1/choices/3", 512));
		question.choices.add(new Choice("Ruby", "/questions/1/choices/4", 256));
		return repo.save(question);
	}

	@GetMapping("/removeall")
	public List<Question> removeall() {
		repo.deleteAll();
		List<Question> result = repo.findAll();
		return result;
	}

}
