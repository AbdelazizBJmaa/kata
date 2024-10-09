package service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import model.Transaction;

public class LineFormatter {
	
	private static final String DATE_PATTERN = "dd/MM/yyyy";

	public String format(Transaction transaction) {
		// TODO Auto-generated method stub
		return String.format("%s | %s  | %s | %s",transaction.getType().toString(),dateFormat(transaction.getTime()),decimalFormat(transaction.getAmount()), decimalFormat(transaction.getBalance()));
	}
	
	private String dateFormat(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
	} 
	
	private String decimalFormat(BigDecimal amount) {
		return new DecimalFormat("0.00" , new DecimalFormatSymbols(Locale.US)).format(amount);
	}

}
