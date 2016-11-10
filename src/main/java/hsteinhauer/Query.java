package hsteinhauer;

import hsteinhauer.clients.ClientFactory;
import hsteinhauer.clients.ClientStrategy;
import hsteinhauer.clients.Media;
import hsteinhauer.exception.ClientException;
import hsteinhauer.model.QueryParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Query {

	private final Logger logger = LogManager.getLogger(Query.class);

	private Query() {
	}

	public static void main (String... args) {
		Query query = new Query();
		query.run();
	}

	private void run() {
		try {
			QueryParameter parameters = QueryParameter.getInstance();

			Set<Media> foundMedia = executeQuery(parameters);
			printMediaInfo(foundMedia);
		} catch (ClientException e) {
			showErrorAndUsage(e);
		}

	}

	private void printMediaInfo(Set<Media> foundMedia) {
		for (Media currentMedia : foundMedia) {
			System.out.println(String.format("Title: '%s', Year: %d, Director: %s",
					currentMedia.getTitle(),
					currentMedia.getYearOfCreation(),
					currentMedia.getDirector()));
		}
	}

	private Set<Media> executeQuery(QueryParameter parameters) throws ClientException {
		ClientStrategy client = ClientFactory.getClient(parameters);

		return client.search(parameters);
	}

	private void showErrorAndUsage(ClientException e) {
		logger.error(e);
		printUsage();
	}

	private void printUsage() {
		System.err.println("Usage: java -Dtype=[movie] -Dapi=[omdb] -Dtitle=<Title you search for> ksubaka-query.jar");
	}


}
