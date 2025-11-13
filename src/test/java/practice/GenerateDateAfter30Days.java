package practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GenerateDateAfter30Days {

	public static void main(String[] args) {

		Date d=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		sim.format(d);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String requiredDate = sim.format(cal.getTime());
		System.out.println(requiredDate);
	}

}
