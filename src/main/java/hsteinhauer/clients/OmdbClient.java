package hsteinhauer.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import hsteinhauer.exception.ClientException;
import hsteinhauer.model.MediaType;
import hsteinhauer.model.OmdbMovie;
import hsteinhauer.model.transfer.OmdbSearchImdbReference;
import hsteinhauer.model.transfer.OmdbSearchResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Set;
import java.util.stream.Collectors;

public class OmdbClient implements ClientStrategy {

	private final Logger logger = LogManager.getLogger(this.getClass());

	private static final String BASE_URL_SEARCH = "http://www.omdbapi.com/?s=%s&type=movie";
	private static final String BASE_URL_DETAILS = "http://www.omdbapi.com/?i=%s&plot=full";

	private final ObjectMapper mapper;

	public OmdbClient() {
		this.mapper = new ObjectMapper();
	}

	OmdbClient(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Set<Media> search(MediaType type, String searchTerm) throws ClientException {
		validateParameters(type, searchTerm);

		Set<Media> results;

		try {
			OmdbSearchResult omdbSearchResults = mapper.readValue(createSafeURL(BASE_URL_SEARCH, searchTerm), OmdbSearchResult.class);

			results = omdbSearchResults.getSearch().stream().map(imdbID -> queryDetails(imdbID, mapper)).collect(Collectors.toSet());
		} catch (IOException e) {
			String error = "Exception occurred while querying for movie details.";
			logger.debug(error, e);
			throw new ClientException(error, e);
		}

		return results;
	}

	private void validateParameters(MediaType type, String searchTerm) throws ClientException {
		if (type == null) {
			throw new ClientException("A valid media type is required");
		}

		if (searchTerm == null || searchTerm.trim().isEmpty()) {
			throw new ClientException("Empty search terms are not supported");
		}
	}

	private OmdbMovie queryDetails(OmdbSearchImdbReference imdbID, ObjectMapper mapper) {
		OmdbMovie result = null;
		try {
			result = mapper.readValue(createSafeURL(BASE_URL_DETAILS, imdbID.getImdbID()), OmdbMovie.class);
		} catch (IOException e) {
			logger.warn("Could not look up information for a movie. Please try http://www.imdb.com/title/{}/ instead.",
					imdbID.getImdbID());
		}

		return result;
	}

	private URL createSafeURL(String base, String parameter) throws UnsupportedEncodingException, MalformedURLException {
		String encodedParameter = URLEncoder.encode(parameter, "UTF-8");

		String url = String.format(base, encodedParameter);

		return new URL(url);
	}

}
