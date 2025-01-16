package test.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import exception.NonSufficientFundsException;
import model.InMemoryTransactions;
import model.Transaction;
import service.Account;

@ExtendWith(MockitoExtension.class)
public class AccountOperationTest {

	private Account account;

	@Mock
	private InMemoryTransactions transactions;

	@BeforeEach
	void init() {
		account = new Account(transactions);
	}

	@Test
	public void init_a_transaction_with_a_positive_amount_for_a_deposit() {
		// Arrange
		BigDecimal depositAmount = new BigDecimal(100L);

		// Act
		account.deposit(depositAmount);

		// Assert
		verify(transactions, atLeastOnce()).add(any(Transaction.class));
	}

	@Test
	public void init_a_transaction_with_a_negative_amount_for_a_deposit() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.valueOf(-100L)));
	}

	@Test
	public void init_a_transaction_with_a_null_amount_for_a_deposit() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> account.deposit(null));
	}

	@Test
	public void transaction_with_a_positive_amount_equals_to_the_balance_for_a_withdraw() throws NonSufficientFundsException {

		// Arrange
		BigDecimal depositAmount = new BigDecimal("1500.00");
		BigDecimal withdrawalAmount = new BigDecimal("500.00");

		// Act
		account.deposit(depositAmount);
		
		when(transactions.lastBalance()).thenReturn(new BigDecimal("1500.00"));
		account.withdraw(withdrawalAmount);

		// Assert
		verify(transactions, times(2)).add(any(Transaction.class));
	}

	@Test
	public void transaction_with_a_positive_amount_greater_than_the_balance_for_a_withdraw() {
		// Arrange
		BigDecimal depositAmount = new BigDecimal("500.00");
		BigDecimal withdrawalAmount = new BigDecimal("1000.00");

		// Act
		account.deposit(depositAmount);

		// Assert
		Assertions.assertThrows(NonSufficientFundsException.class, () -> account.withdraw(withdrawalAmount));
	}
	
	@Test
	void  transaction_to_print() {
        
		//Arrange
		LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 9, 0, 0);
		Transaction transaction = new Transaction(Transaction.Type.DEPOSIT , localDateTime , BigDecimal.TEN , BigDecimal.TEN);
		
		//Act
		String line = account.printHistory(List.of(transaction));
		
		//Assert
		assertEquals("operation | date  | amount | balance\n" + "DEPOSIT | 09/10/2024  | 10.00 | 10.00", line);
	
		
		
	}
}
