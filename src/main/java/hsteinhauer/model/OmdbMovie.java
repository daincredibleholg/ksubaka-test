package hsteinhauer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hsteinhauer.clients.Media;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbMovie implements Media {

	private final String title;
	private final String director;
	private final int year;
	private final String imdbID;

	@JsonCreator
	public OmdbMovie(@JsonProperty("Title") final String title,
					 @JsonProperty("Director") final String director,
					 @JsonProperty("Year") final int year,
					 @JsonProperty("imdbID") final String imdbID) {
		this.title = title;
		this.director = director;
		this.year = year;
		this.imdbID = imdbID;
	}

	@Override
	public MediaType getType() {
		return MediaType.MOVIE;
	}

	@Override
	public String getDirector() {
		return this.director;
	}

	@Override
	public int getYearOfCreation() {
		return this.year;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	public String getImdbId() {
		return this.imdbID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OmdbMovie omdbMovie = (OmdbMovie) o;

		if (year != omdbMovie.year) return false;
		if (getTitle() != null ? !getTitle().equals(omdbMovie.getTitle()) : omdbMovie.getTitle() != null) return false;
		return getDirector() != null ? getDirector().equals(omdbMovie.getDirector()) : omdbMovie.getDirector() == null;

	}

	@Override
	public int hashCode() {
		int result = getTitle() != null ? getTitle().hashCode() : 0;
		result = 31 * result + (getDirector() != null ? getDirector().hashCode() : 0);
		result = 31 * result + year;
		return result;
	}

	@Override
	public String toString() {
		return "OmdbMovie{" +
				"title='" + title + '\'' +
				", director='" + director + '\'' +
				", year=" + year +
				'}';
	}
}
