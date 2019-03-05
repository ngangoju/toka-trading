/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toka.common;

import java.util.Random;

/**
 *
 * @author Eric
 */
public class NumberGeneration {

	Formating fmt = new Formating();

	public String generateAppointNumber() {
		long last = 0;
		String requisitionDateId = null;
		int auto = 0;

		String y = null;
		String m = null;
		String d = null;
		String hr = null;
		String min = null;
		String sec = null;
		try {
			Random rand = new Random();
			auto = rand.nextInt(10);
			String thedate[] = fmt.getCurrentMysqlDateFormt().split(" ");
			String theTime[] = thedate[1].split(":");
			String YMD[] = thedate[0].split("-");
			y = YMD[0].substring(2, 4);
			m = YMD[1];
			d = YMD[2];
			hr = theTime[0];
			min = theTime[1];
			sec = theTime[2];
			requisitionDateId = "AP" + d + m + y + sec;
		} catch (Exception j) {

		}

		return requisitionDateId;

	}

	public String generatecOnsultationNumber() {
		long last = 0;
		String requisitionDateId = null;
		int auto = 0;

		String y = null;
		String m = null;
		String d = null;
		String hr = null;
		String min = null;
		String sec = null;
		try {
			Random rand = new Random();
			auto = rand.nextInt(10);
			String thedate[] = fmt.getCurrentMysqlDateFormt().split(" ");
			String theTime[] = thedate[1].split(":");
			String YMD[] = thedate[0].split("-");
			y = YMD[0].substring(2, 4);
			m = YMD[1];
			d = YMD[2];
			hr = theTime[0];
			min = theTime[1];
			sec = theTime[2];
			requisitionDateId = "CS" + d + m + y + sec;
		} catch (Exception j) {

		}

		return requisitionDateId;

	}
}
