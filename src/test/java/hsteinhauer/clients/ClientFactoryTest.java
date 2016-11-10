package hsteinhauer.clients;

import hsteinhauer.exception.ClientException;
import hsteinhauer.model.Api;
import hsteinhauer.model.QueryParameter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientFactoryTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void validApiReturnsInstance() throws ClientException {
		QueryParameter queryParameter = mock(QueryParameter.class);

		when(queryParameter.getApi()).thenReturn(Api.OMDB);

		ClientStrategy actualResult = ClientFactory.getClient(queryParameter);

		assertThat(actualResult, is(not(nullValue())));
		assertThat(actualResult, is(instanceOf(OmdbClient.class)));
	}

	@Test
	public void noApiThrowsException() throws ClientException {
		expectedException.expect(ClientException.class);

		ClientFactory.getClient(mock(QueryParameter.class));
	}
}