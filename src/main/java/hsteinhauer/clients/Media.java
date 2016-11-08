package hsteinhauer.clients;

import hsteinhauer.model.MediaType;

public interface Media {

	MediaType getType();
	String getDirector();
	int getYearOfCreation();
	String getTitle();
}
