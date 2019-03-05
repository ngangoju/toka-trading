/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toka.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ngabo
 */
public class Formating {

	public static String getCurrentMysqlDateFormt() {
		try {
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(dt);
		} catch (Exception jj) {

		}
		return null;
	}

	public static String getCurrentMysqlDateFormtNOTime() {
		try {
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(dt);
		} catch (Exception jj) {

		}
		return null;
	}

	public static Date getCurrentDateFormtNOTime() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dt = new java.util.Date();
		Date rdate = null;
		try {

			sdf.format(dt);
			rdate = sdf.parse(sdf.format(dt));
		} catch (Exception jj) {

		}
		return rdate;

	}

	public static int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	public Date getMysqlDateFormt(String dateValue) {
		Date datee = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			datee = new java.sql.Date(simpleDateFormat.parse(dateValue).getTime());
		} catch (ParseException ex) {

			return null;
		}
		return datee;
	}

	public static Date getMysqlTimeFormt(String timeValue) {
		Date timee = null;
		SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
		try {
			timee = new java.sql.Time(simpleTimeFormat.parse(timeValue).getTime());
		} catch (ParseException ex) {

			return null;
		}
		return timee;
	}

	public static java.util.Date getFormtDateReturnMysqlFormat(String inputDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date d = null;
		try {
			d = sdf.parse(inputDate);
		} catch (ParseException ex) {
			// Logger.getLogger(requestRequisitionController.class.getName()).log(Level.SEVERE,
			// null, ex);
		}

		sdf.applyPattern("yyyy-MM-dd");
		return sdf.parse(sdf.format(d));

	}

	public static String getMysqlFormatV2(Date inputDate) throws ParseException {
		return new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(inputDate);
	}

	public static Date getFormtTimeReturnMysqlFormat(String inputTime) throws ParseException {
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm a");
		java.util.Date d2 = null;
		try {
			d2 = sdf2.parse(inputTime);
		} catch (ParseException ex) {

		}
		sdf2.applyPattern("hh:mm:ss a");

		return (Date) (sdf2.parse(sdf2.format(d2)));

	}
	public static String getMysqlFormatV3(Date inputDate) throws ParseException {
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(inputDate);
	}
	public static void main(String... aa) {

	}
}
