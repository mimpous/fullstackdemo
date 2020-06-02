package com.evaluation.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.evaluation.backend.model.persistence.Tutorial;
import com.evaluation.backend.model.persistence.repositories.TutorialRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class TutorialsControllerTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JacksonTester<Tutorial> json;

	@Autowired
	TutorialRepository tutorialRepository;

	private Tutorial createAnEntryInTheH2AndReturn(String title) {

		tutorialRepository.deleteAll();

		Tutorial tutorial = new Tutorial();
		tutorial.setTitle(title);
		tutorial.setDescription("this is a first title");
		tutorial.setPublished(false);

		tutorialRepository.save(tutorial);
		return tutorial;
	}

	/**
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void getAllTutorials() throws Exception {

		createAnEntryInTheH2AndReturn("Some Title");

		MvcResult result = mvc.perform(get(new URI("/api/tutorials")).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andReturn();

		long resultLength = objectMapper.readValue(result.getResponse().getContentAsString(), Tutorial[].class).length;

		// length is including the data in h2 db
		assertEquals(resultLength, 1);

	}

	/**
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void getAllTutorialsByTitle() throws Exception {

		Tutorial createdTutorial = createAnEntryInTheH2AndReturn("Title1");

		MvcResult result = mvc.perform(get(new URI("/api/tutorials?title=Title1"))
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		Tutorial[] returnedTutorial =
				objectMapper.readValue(result.getResponse().getContentAsString(), Tutorial[].class);

		assertEquals(createdTutorial.getDescription(), returnedTutorial[0].getDescription());

	}

	/**
	 * Test for create a new Tutorial
	 * 
	 * @throws Exception when User creation fails in the system
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void createNewTutorial() throws Exception {

		Tutorial tutorial = new Tutorial();
		tutorial.setTitle("this is a title");
		tutorial.setDescription("this is a first title");
		tutorial.setPublished(false);

		mvc.perform(post(new URI("/api/tutorials")).content(json.write(tutorial).getJson())
				.contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated());

	}

}
