package model;

import java.math.BigDecimal;
import java.util.List;

public record InMemoryTransactions (List<Transaction> transactions){

	public void add(final Transaction transaction) {
		transactions.add(transaction);
	}

	public BigDecimal lastBalance() {
		return transactions.get(transactions.size() - 1).getBalance();

	}

}
