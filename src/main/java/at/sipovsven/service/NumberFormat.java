package at.sipovsven.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberFormat {

	public double DecimalFormater(double d) {

		return round(d,2);

	}
	
	public double DecimalFormaterSingleDigit(double d) {
		
		
		return round(d,1);
		
		
		
	}
	public static BigDecimal roundBD(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_EVEN);
	    return bd;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_EVEN);
	    return bd.doubleValue();
	}
}
