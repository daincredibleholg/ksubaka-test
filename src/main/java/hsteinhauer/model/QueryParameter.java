package hsteinhauer.model;

import hsteinhauer.exception.ClientException;

public class QueryParameter {

	private MediaType type;
	private String searchTitle;
	private Api api;

	private static QueryParameter instance;

	private QueryParameter(final MediaType type, final Api api, final String searchTitle) {
		this.type = type;
		this.api = api;
		this.searchTitle = searchTitle;
	}

	public static QueryParameter getInstance() throws ClientException {
		if (instance == null) {
			instance = initInstance();
		}

		return instance;
	}

	private static QueryParameter initInstance() throws ClientException {
		MediaType type = getTypeFromEnvironment();
		Api api = getApiFromEnvironment();
		String searchTitle = getSearchTitleFromEnvironment();

		if (type == null || api == null || searchTitle == null) {
			throw new ClientException("Please provide search type, API and search title.");
		}

		return new QueryParameter(type, api, searchTitle);
	}

	private static String getSearchTitleFromEnvironment() {
		String searchTitle = System.getProperty("title");

		if (searchTitle != null && searchTitle.trim().isEmpty()) {
			return null;
		}

		return searchTitle;
	}

	private static MediaType getTypeFromEnvironment() throws ClientException {
		String type = System.getProperty("type");

		MediaType mediaType = null;

		if (type != null && !type.trim().isEmpty()) {
			try {
				mediaType = MediaType.valueOf(type.toUpperCase());
			} catch (IllegalArgumentException e) {
				throw new ClientException("Unsupported type provided.", e);
			}
		}

		return mediaType;
	}

	public static Api getApiFromEnvironment() throws ClientException {
		String apiParameter = System.getProperty("api");

		Api api = null;

		if (apiParameter != null && ! apiParameter.trim().isEmpty() ) {
			try {
				api = Api.valueOf(apiParameter.toUpperCase());
			} catch (IllegalArgumentException e) {
				throw new ClientException("Unsupported API provided.", e);
			}
		}

		return api;
	}

	public MediaType getType() {
		return this.type;
	}

	public Api getApi() {
		return this.api;
	}

	public String getSearchTitle() {
		return this.searchTitle;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		QueryParameter that = (QueryParameter) o;

		if (getType() != that.getType()) return false;
		if (!getSearchTitle().equals(that.getSearchTitle())) return false;
		return getApi() == that.getApi();

	}

	@Override
	public int hashCode() {
		int result = getType().hashCode();
		result = 31 * result + getSearchTitle().hashCode();
		result = 31 * result + getApi().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "QueryParameter{" +
				"type=" + type +
				", searchTitle='" + searchTitle + '\'' +
				", api=" + api +
				'}';
	}
}
