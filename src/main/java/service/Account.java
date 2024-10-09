package service;

import java.math.BigDecimal;

import model.InMemoryTransactions;

public class Account {
	
	private InMemoryTransactions transactions;
	
	public Account(InMemoryTransactions transactions) {
		this.transactions = transactions;
	}

	public void deposit(BigDecimal amount) {
		
		
	}

}
