package genericutilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public String getRequiredDate(int days) {
		
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String requiredDate = sim.format(cal.getTime());
		
		return requiredDate;
	}
	
	public int generateRandomNumber() {

		Random random = new Random();
		int randomNum = random.nextInt(1000);
		return randomNum;

	}
	public String getCurrentDateAndTime() {
		Date d=new Date();
		String date = d.toString().replace(" ", "_").replace(":", "_");
		return date;
		
	}
}
