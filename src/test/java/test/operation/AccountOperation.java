package test.operation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
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
		//Arrange
		BigDecimal depositAmount = new BigDecimal(100L);
		
		//Act
		account.deposit(depositAmount);
		
		//Assert
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
	public void transaction_with_a_positive_amount_equals_to_the_balance_for_a_withdraw() {
		
	    // Arrange
	    BigDecimal depositAmount = new BigDecimal("1000.00");
	    BigDecimal withdrawalAmount = new BigDecimal("500.00");
		
	    
	    //Act
		account.deposit(depositAmount);
		account.withdraw(withdrawalAmount);
		
		//Assert
		verify(transactions, times(2)).add(any(Transaction.class));
	}
}
