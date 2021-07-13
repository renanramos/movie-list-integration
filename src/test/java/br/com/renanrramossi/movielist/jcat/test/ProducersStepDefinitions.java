package br.com.renanrramossi.movielist.jcat.test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.renanrramossi.movielist.integration.ProducerStatisticDTO;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.junit.CucumberOptions;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

@CucumberOptions(features = "src/test/resources/functional-tests")
public class ProducersStepDefinitions {

	private static final String MIN_PRODUCER_KEY = "min";
	private static final String MAX_PRODUCER_KEY = "max";

	private static ResponseOptions<Response> response;
	private static RestAssuredExtension restAssuredExtension;
	private static ObjectMapper mapper;
	private static TypeReference<Map<String, ProducerStatisticDTO>> typeReference = new TypeReference<Map<String, ProducerStatisticDTO>>() {
	};

	@Before
	public void setup() {
		restAssuredExtension = new RestAssuredExtension();
		mapper = new ObjectMapper();
	}

	@Given("I perform GET operation for <\\/producers>")
	public void getAllEmployees() {
		response = restAssuredExtension.get();
	}

	@Then("the response is the producers")
	public void receiveEmployeeList() {
		Map<String, ProducerStatisticDTO> producers = mapper.convertValue(response.getBody().as(JsonNode.class),
				typeReference);

		assertThat(producers, notNullValue());
		assertThat(producers.get(MIN_PRODUCER_KEY), notNullValue());
		assertThat(producers.get(MAX_PRODUCER_KEY), notNullValue());

		final ProducerStatisticDTO producerStatisticDTOMaxInterval = producers.get(MAX_PRODUCER_KEY);
		final ProducerStatisticDTO producerStatisticDTOMinInterval = producers.get(MIN_PRODUCER_KEY);

		assertThat(producerStatisticDTOMaxInterval.getInterval(),
				greaterThan(producerStatisticDTOMinInterval.getInterval()));
	}
}
