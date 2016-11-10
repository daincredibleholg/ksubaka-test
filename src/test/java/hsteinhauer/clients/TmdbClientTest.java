package hsteinhauer.clients;

import hsteinhauer.exception.ClientException;
import hsteinhauer.model.QueryConfiguration;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TmdbClientTest {

	private static final String API_KEY_VALUE = "test-api-key-value";

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	QueryConfiguration config = mock(QueryConfiguration.class);

	@Test
	public void noApiKeyPropertyShouldThrowException() throws ClientException {
		expectedException.expect(ClientException.class);

		new TmdbClient(config);
	}

	@Test
	public void existingApiKeyThrowsNoException() throws ClientException {
		setUpValidApiKey();

		TmdbClient client = new TmdbClient(config);

		assertThat(client, is(not(nullValue())));
	}

	private void setUpValidApiKey() {
		when(config.getProperty(eq(TmdbClient.API_KEY_PROPERTY))).thenReturn(API_KEY_VALUE);
	}

}