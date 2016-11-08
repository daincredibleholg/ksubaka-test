package hsteinhauer.model;

import hsteinhauer.clients.Media;

public class Movie implements Media {

	private final String title;
	private final String director;
	private final int year;

	public Movie(final String title, final String director, final int year) {
		this.title = title;
		this.director = director;
		this.year = year;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Movie movie = (Movie) o;

		if (year != movie.year) return false;
		if (getTitle() != null ? !getTitle().equals(movie.getTitle()) : movie.getTitle() != null) return false;
		return getDirector() != null ? getDirector().equals(movie.getDirector()) : movie.getDirector() == null;

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
		return "Movie{" +
				"title='" + title + '\'' +
				", director='" + director + '\'' +
				", year=" + year +
				'}';
	}
}
