/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toka.validation;

import java.util.Iterator;

/**
 *
 * @author ivan
 */
public class ValidPhone {
	public boolean iSphoneValide(String phoneNumber) {
		if ("0783".equals(phoneNumber)) {
			return true;
		} else {
			return false;
		}

	}

	public static void main(String... dd) throws Exception {
	}
}
