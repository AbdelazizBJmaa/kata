package service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import model.InMemoryTransactions;
import model.Transaction;

public class Account {

	private InMemoryTransactions transactions;

	public Account(InMemoryTransactions transactions) {
		this.transactions = transactions;
	}

	public void deposit(BigDecimal amount) {

		if (Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Cannot deposit a negative or null amount");
		}

		BigDecimal previousBalance = Objects.isNull(transactions.lastBalance()) ? BigDecimal.ZERO : transactions.lastBalance();
		Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, LocalDateTime.now(), amount,
				previousBalance.add(amount));
		transactions.add(transaction);
	}

	public void withdraw(BigDecimal withdrawalAmount) {
		BigDecimal previousBalance = Objects.isNull(transactions.lastBalance()) ? BigDecimal.ZERO : transactions.lastBalance();
		
		Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, LocalDateTime.now(), withdrawalAmount.negate(),
				previousBalance.subtract(withdrawalAmount));
		transactions.add(transaction);
		
	}

}
