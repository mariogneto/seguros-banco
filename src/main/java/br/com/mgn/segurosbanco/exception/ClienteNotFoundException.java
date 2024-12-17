package br.com.mgn.segurosbanco.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
