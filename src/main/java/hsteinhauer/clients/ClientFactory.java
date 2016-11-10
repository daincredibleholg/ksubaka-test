package hsteinhauer.clients;

import hsteinhauer.exception.ClientException;
import hsteinhauer.model.QueryParameter;

public class ClientFactory {

	private ClientFactory() {
	}

	public static ClientStrategy getClient(QueryParameter parameters) throws ClientException {
		if (parameters == null || parameters.getApi() == null) {
			throw new ClientException(new IllegalArgumentException("Query parameters must contain API."));
		}

		switch (parameters.getApi()) {
			case OMDB:
				return new OmdbClient();
			case TMDB:
				return new TmdbClient();
			default:
				throw new ClientException("Unsupported API provided.");
		}
	}
}
