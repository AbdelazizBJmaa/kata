package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
	
	public enum Type {
		DEPOSIT,
		WITHDRAW,
	}
	
	private final LocalDateTime time;
	private final BigDecimal amount;
	private final BigDecimal balance;
    private final Type type;
    
	public Transaction(Type type,LocalDateTime time, BigDecimal amount, BigDecimal balance) {
		this.time = time;
		this.amount = amount;
		this.balance = balance;
		this.type = type;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public Type getType() {
		return type;
	}
}
