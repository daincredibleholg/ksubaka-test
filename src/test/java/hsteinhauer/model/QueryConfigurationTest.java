package hsteinhauer.model;

import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class QueryConfigurationTest {

	@Test
	public void existingPropertyReturnsExpectedValue() {
		String key = "test.property.key";
		String value = "test-value";

		Properties givenProperties = new Properties();
		givenProperties.setProperty(key, value);

		QueryConfiguration configuration = new QueryConfiguration(givenProperties);

		String actualValue = configuration.getProperty(key);

		assertThat(actualValue, is(value));
	}

	@Test
	public void nonExistingPropertyReturnsNullValue() {
		String nonExistingKey = "test.property.key.non.existent";
		String key = "test.property.key";
		String value = "test-value";

		Properties givenProperties = new Properties();
		givenProperties.setProperty(key, value);

		QueryConfiguration configuration = new QueryConfiguration(givenProperties);

		String actualValue = configuration.getProperty(nonExistingKey);

		assertThat(actualValue, is(nullValue()));

	}


}