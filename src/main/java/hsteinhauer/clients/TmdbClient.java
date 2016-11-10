package hsteinhauer.clients;

import hsteinhauer.exception.ClientException;
import hsteinhauer.model.MediaType;
import hsteinhauer.model.QueryConfiguration;
import hsteinhauer.model.QueryParameter;

import java.util.Set;

public class TmdbClient implements ClientStrategy {

	static final String API_KEY_PROPERTY = "tmdb.api.key";
	private final String apiKey;

	public TmdbClient() throws ClientException {
		this(QueryConfiguration.getInstance());
	}

	TmdbClient(QueryConfiguration configuration) throws ClientException {
		this.apiKey = getApiKey(configuration);

		if (apiKey == null) {
			throw new ClientException("No API key found");
		}
	}

	private String getApiKey(QueryConfiguration configuration) {
		return configuration.getProperty(API_KEY_PROPERTY);
	}

	@Override
	public Set<Media> search(MediaType type, String searchTerm) throws ClientException {
		return null;
	}

	@Override
	public Set<Media> search(QueryParameter parameters) throws ClientException {
		return search(parameters.getType(), parameters.getSearchTitle());
	}
}
