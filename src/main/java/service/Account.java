package service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import exception.NonSufficientFundsException;
import model.InMemoryTransactions;
import model.Transaction;

public class Account {

	private final InMemoryTransactions transactions;

	public Account(InMemoryTransactions transactions) {
		this.transactions = transactions;
	}

	public void deposit(BigDecimal amount) {

		validateAmount(amount);

		BigDecimal previousBalance = getBalance();

		recordTransaction(Transaction.Type.DEPOSIT, amount, previousBalance);
	}

	public void withdraw(BigDecimal withdrawalAmount) throws NonSufficientFundsException {
		BigDecimal previousBalance = getBalance();

		validateAmount(withdrawalAmount);

		if (previousBalance.compareTo(withdrawalAmount) < 0) {
			throw new NonSufficientFundsException("Insufficient funds");
		}

		recordTransaction(Transaction.Type.WITHDRAW, withdrawalAmount.negate(), previousBalance);

	}

	private void recordTransaction(Transaction.Type type, BigDecimal amount, BigDecimal previousBalance) {
		Transaction transaction = new Transaction(type, LocalDateTime.now(), amount, previousBalance.add(amount));
		transactions.add(transaction);
	}

	private BigDecimal getBalance() {
		return Objects.isNull(transactions.lastBalance()) ? BigDecimal.ZERO : transactions.lastBalance();

	}

	private void validateAmount(BigDecimal amount) {
		if (Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Cannot deposit a negative or null amount");
		}
	}

}
