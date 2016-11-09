package hsteinhauer.exception;

public class ClientException extends Exception {

	public ClientException(Throwable e) {
		super(e);
	}

	public ClientException(String error, Throwable throwable) {
		super(error, throwable);
	}

	public ClientException(String error) {
		super(error);
	}
}
