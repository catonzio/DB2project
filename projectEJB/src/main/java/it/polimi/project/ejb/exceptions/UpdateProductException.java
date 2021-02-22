package it.polimi.project.ejb.exceptions;

public class UpdateProductException extends Exception {
	private static final long serialVersionUID = 1L;

	public UpdateProductException(String message) {
		super(message);
	}
}
