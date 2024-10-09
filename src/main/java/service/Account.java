package service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import model.InMemoryTransactions;
import model.Transaction;

public class Account {

	private InMemoryTransactions transactions;

	public Account(InMemoryTransactions transactions) {
		this.transactions = transactions;
	}

	public void deposit(BigDecimal amount) {

		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Cannot deposit a negative or null amount");
		}

		BigDecimal previousBalance = transactions.lastBalance();
		Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, LocalDateTime.now(), amount,
				previousBalance);
		transactions.add(transaction);
	}

}
