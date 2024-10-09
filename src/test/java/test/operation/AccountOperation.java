package test.operation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import model.InMemoryTransactions;
import model.Transaction;
import service.Account;


@ExtendWith(MockitoExtension.class)
public class AccountOperation {

	private Account account;

	@Mock
	private InMemoryTransactions transactions;
	
	@BeforeEach
	void init() {
		account = new Account(transactions);
	}
	
	@Test
	public void init_a_transaction_with_a_positive_amount_for_a_deposit() {
		account.deposit(BigDecimal.valueOf(100L));
		verify(transactions, atLeastOnce()).add(any(Transaction.class));
	}
	
	@Test
	public void init_a_transaction_with_a_negative_amount_for_a_deposit() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.valueOf(-100L)));
	}
}
