package nurzat.junit;

import com.jazz.junit.nurzat.bankapp.Account;
import com.jazz.junit.nurzat.bankapp.CheckingAccount;
import com.jazz.junit.nurzat.bankapp.exceptions.InsuffcientFundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountExceptionTest {
    @Test
    @DisplayName("Снятия больше баланса")
    void withdrawToMuchThrows(){
        Account account = new CheckingAccount("Bob", new BigDecimal("1000"));
        InsuffcientFundsException ex = Assertions.assertThrows(
        InsuffcientFundsException.class,
                ()->account.withdraw(new BigDecimal("1500"))
        );
        Assertions.assertTrue(ex.getMessage().contains("Not enough  funds"));
    }
}
