package util;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	
	/**
	 * Incrementa uma data em um dia
	 * 
	 * @param data
	 * @return
	 */
	public static Date adicionaUmDia(Date data) {
		return adicionaDias(data, 1);
	}
	
	/**
	 * Incrementa uma data em uma quantidade de dias passada como parâmetro
	 * 
	 * @param data
	 * @return
	 */
	public static Date adicionaDias(Date data, int dias) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DAY_OF_MONTH, dias);
		return c.getTime();
	}
	

}
