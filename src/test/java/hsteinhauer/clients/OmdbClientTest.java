package hsteinhauer.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import hsteinhauer.model.MediaType;
import hsteinhauer.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
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

	@Test
	public void successfulRequestReturnsList() throws IOException {
		Movie expectedMovie = new Movie("Monty Python's Life Of Brian", "Terry Jones", 1979);

		Movie[] movies = { expectedMovie };

		when(mapper.readValue(ArgumentMatchers.<URL>any(), eq(Movie[].class))).thenReturn(movies);

		List<Media> actualResult = omdbClient.search(MediaType.MOVIE, "The Life Of Brian");

		assertThat(actualResult, hasItem(expectedMovie));

	}

	@Test
	public void multipleResultsReturnsList() throws IOException {
		Movie firstFoundMovie  = new Movie("Monty Python's Life Of Brian", "Terry Jones", 1979);
		Movie secondFoundMovie = new Movie("Monty Python and the Holy Grail", "Terry Jones, Terry Gilliam", 1975);
		Movie[] movies = { firstFoundMovie, secondFoundMovie };

		when(mapper.readValue(ArgumentMatchers.<URL>any(), eq(Movie[].class))).thenReturn(movies);

		List<Media> actualResult = omdbClient.search(MediaType.MOVIE, "Monty Python");

		assertThat(actualResult, containsInAnyOrder(firstFoundMovie, secondFoundMovie));

	}

}
