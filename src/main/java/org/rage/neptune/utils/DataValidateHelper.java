package org.rage.neptune.utils;

public class DataValidateHelper {

	public static void validateInputData(final String string){
		if(string == null || string.trim().length() == 0){
			throw new IllegalArgumentException("parameter invalid");
		}
	}
}
