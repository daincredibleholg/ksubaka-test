package hsteinhauer.clients;

import hsteinhauer.model.MediaType;

import java.util.List;

public interface ClientStrategy {

	List<Media> search(MediaType type, String searchTerm);

}
