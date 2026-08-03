package nurzat.junit;

import com.jazz.junit.nurzat.bankapp.Account;
import com.jazz.junit.nurzat.bankapp.CheckingAccount;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountOrderedTest {
    private Account account;
    @Test
    @Order(1)
    void step1OpenAccount(){
        account = new CheckingAccount("Bob");
        Assertions.assertEquals(BigDecimal.ZERO,account.getBalance());
    }

    @Test
    @Order(2)
    void step2Deposit(){
        account.deposit(new BigDecimal("1000"));
        Assertions.assertEquals( new BigDecimal("1000"),account.getBalance());
    }

    @Test
    @Order(3)
    void step3WithDraw(){
        account.withdraw(new BigDecimal("500"));
        Assertions.assertEquals( new BigDecimal("500"),account.getBalance());
    }

}
