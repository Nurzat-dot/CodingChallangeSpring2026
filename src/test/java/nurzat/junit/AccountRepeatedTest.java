package nurzat.junit;

import com.jazz.junit.nurzat.bankapp.Account;
import com.jazz.junit.nurzat.bankapp.CheckingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.math.BigDecimal;

public class AccountRepeatedTest {
    @RepeatedTest(5)
    @DisplayName("Депозит стабильно работает")
    void depositAlwaysWork(){
        Account account = new CheckingAccount("Bob");
        account.deposit(new BigDecimal("1000"));
        Assertions.assertEquals(new BigDecimal("1000"),account.getBalance());
    }
}
