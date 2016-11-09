package hsteinhauer.exception;

public class ClientException extends Exception {

	public ClientException(Throwable e) {
		super(e);
	}

	public ClientException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
