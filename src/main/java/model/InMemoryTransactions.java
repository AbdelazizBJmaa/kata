package model;

import java.math.BigDecimal;
import java.util.List;

public class InMemoryTransactions {
	private final List<Transaction> transactions;

	public InMemoryTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void add(final Transaction transaction) {
		transactions.add(transaction);
	}

	public BigDecimal lastBalance() {
		return transactions.get(transactions.size() - 1).getBalance();

	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

}
