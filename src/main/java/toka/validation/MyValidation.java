/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toka.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author RTAP4
 */
public class MyValidation {
	List<String> errors = new ArrayList<String>();

	public boolean validWithParthen(String regex, String value) {

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		boolean val = matcher.matches();

		return val;
	}

	public List<String> listError(String error) {

		errors.add(error);
		return errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
