package hsteinhauer.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import hsteinhauer.model.MediaType;
import hsteinhauer.model.Movie;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OmdbClient implements ClientStrategy {

	private static final String BASE_URL = "http://www.omdbapi.com/?s=%s&type=movie";

	private final ObjectMapper mapper;

	public OmdbClient() {
		this.mapper = new ObjectMapper();
	}

	public OmdbClient(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Media> search(MediaType type, String searchTerm) {
		List<Media> results = new ArrayList<>();

		try {
			results = Arrays.asList(mapper.readValue(new URL(String.format(BASE_URL, searchTerm)), Movie[].class));
		} catch (IOException e) {
			// FIXME Needs proper handling
			e.printStackTrace();
		}

		return results;
	}

	private OkHttpClient initHttpClient() {
		return new OkHttpClient();
	}

}
