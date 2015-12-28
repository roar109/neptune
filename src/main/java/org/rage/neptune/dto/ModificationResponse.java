package org.rage.neptune.dto;

public class ModificationResponse {

	private boolean valid;
	private String errorMessage;
	
	public ModificationResponse(boolean valid, String errorMessage){
		this.valid = valid;
		this.errorMessage = errorMessage;
	}
	
	public ModificationResponse(){
		this.valid = Boolean.TRUE;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
