package hsteinhauer.model.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbSearchResult {

	private final Set<OmdbSearchImdbReference> search;

	@JsonCreator
	public OmdbSearchResult(@JsonProperty("Search") Set<OmdbSearchImdbReference> search) {
		this.search = search;
	}

	public Set<OmdbSearchImdbReference> getSearch() {
		return search;
	}

}
