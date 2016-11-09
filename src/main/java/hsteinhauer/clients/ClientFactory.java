package hsteinhauer.clients;

import hsteinhauer.exception.ClientException;
import hsteinhauer.model.QueryParameter;

public class ClientFactory {

	private ClientFactory() {
	}

	public static ClientStrategy getClient(QueryParameter parameters) throws ClientException {
		switch (parameters.getApi()) {
			case OMDB:
				return new OmdbClient();
			default:
				throw new ClientException("Unsupported API provided.");
		}
	}
}
