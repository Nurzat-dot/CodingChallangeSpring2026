package nurzat.junit;

import com.jazz.junit.nurzat.bankapp.Account;
import com.jazz.junit.nurzat.bankapp.CheckingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AccountTimeOutTest {
    @Test
    void test1(){
        Account account = new CheckingAccount("Bob");
        Assertions.assertTimeout(Duration.ofMillis(200),
                () ->account.slowDeposit(new BigDecimal("10"),50)
                );
    }
    @Test
    @Timeout(value = 200,unit = TimeUnit.MILLISECONDS)
    void test2() throws InterruptedException{
        Account account = new CheckingAccount("Bob");
        account.slowDeposit(new BigDecimal("10"),100);
        Assertions.assertEquals(new BigDecimal("10"),account.getBalance());
    }
}
