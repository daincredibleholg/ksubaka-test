package hsteinhauer.clients;

import hsteinhauer.exception.ClientException;
import hsteinhauer.model.MediaType;
import hsteinhauer.model.QueryParameter;

import java.util.Set;

public interface ClientStrategy {

	Set<Media> search(MediaType type, String searchTerm) throws ClientException;

	Set<Media> search(QueryParameter parameters) throws ClientException;
}
