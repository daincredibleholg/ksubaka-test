package hsteinhauer.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import hsteinhauer.exception.ClientException;
import hsteinhauer.model.MediaType;
import hsteinhauer.model.OmdbMovie;
import hsteinhauer.model.transfer.OmdbSearchImdbReference;
import hsteinhauer.model.transfer.OmdbSearchResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OmdbClientTest {

	@Mock
	private ObjectMapper mapper;

	@InjectMocks
	private OmdbClient omdbClient;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void successfulRequestReturnsList() throws Exception {
		String imdbID = "tt0071853";

		OmdbSearchImdbReference imdbRef = new OmdbSearchImdbReference(imdbID);
		Set<OmdbSearchImdbReference> references = new HashSet<>();
		references.add(imdbRef);
		OmdbSearchResult searchResult = new OmdbSearchResult(references);

		OmdbMovie expectedOmdbMovie = new OmdbMovie("Life Of Brian", "Terry Jones", 1979, imdbID);

		when(mapper.readValue(ArgumentMatchers.<URL>any(), eq(OmdbSearchResult.class))).thenReturn(searchResult);
		when(mapper.readValue(ArgumentMatchers.<URL>any(), eq(OmdbMovie.class))).thenReturn(expectedOmdbMovie);

		Set<Media> actualResult = omdbClient.search(MediaType.MOVIE, "Life Of Brian");

		assertThat(actualResult, containsInAnyOrder(expectedOmdbMovie));
	}

	@Test
	public void multipleResultsReturnsList() throws Exception {
		String firstImdbID = "tt0079470";
		String secondImdbID = "tt0071853";

		Set<OmdbSearchImdbReference> references = new HashSet<>(2);
		references.add(new OmdbSearchImdbReference(firstImdbID));
		references.add(new OmdbSearchImdbReference(secondImdbID));
		OmdbSearchResult searchResult = new OmdbSearchResult(references);

		OmdbMovie firstFoundOmdbMovie = new OmdbMovie("Monty Python's Life Of Brian", "Terry Jones", 1979, firstImdbID);
		OmdbMovie secondFoundOmdbMovie = new OmdbMovie("Monty Python and the Holy Grail", "Terry Jones, Terry Gilliam", 1975, secondImdbID);
		OmdbMovie[] movies = {firstFoundOmdbMovie, secondFoundOmdbMovie};

		when(mapper.readValue(ArgumentMatchers.<URL>any(), eq(OmdbSearchResult.class))).thenReturn(searchResult);
		when(mapper.readValue(ArgumentMatchers.<URL>any(), eq(OmdbMovie.class)))
				.thenReturn(firstFoundOmdbMovie)
				.thenReturn(secondFoundOmdbMovie);

		Set<Media> actualResult = omdbClient.search(MediaType.MOVIE, "Monty Python");

		assertThat(actualResult, containsInAnyOrder(firstFoundOmdbMovie, secondFoundOmdbMovie));
	}

	@Test
	public void exceptionIsThrownOnIOProblems() throws Exception {
		when(mapper.readValue(ArgumentMatchers.<URL>any(), eq(OmdbSearchResult.class))).thenThrow(IOException.class);

		expectedException.expect(ClientException.class);

		omdbClient.search(MediaType.MOVIE, "You will never find me");
	}

}
