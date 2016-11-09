package hsteinhauer.model.transfer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbSearchImdbReference {

	private final String imdbID;

	@JsonCreator
	public OmdbSearchImdbReference(@JsonProperty("imdbID") String imdbID) {
		this.imdbID = imdbID;
	}

	public String getImdbID() {
		return imdbID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OmdbSearchImdbReference that = (OmdbSearchImdbReference) o;

		return getImdbID().equals(that.getImdbID());

	}

	@Override
	public int hashCode() {
		return getImdbID().hashCode();
	}

	@Override
	public String toString() {
		return "OmdbSearchImdbReference{" +
				"imdbID='" + imdbID + '\'' +
				'}';
	}
}
