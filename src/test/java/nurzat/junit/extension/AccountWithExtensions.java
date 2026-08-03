package nurzat.junit.extension;

import com.jazz.junit.nurzat.bankapp.Account;
import com.jazz.junit.nurzat.bankapp.CheckingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountWithExtensions {

    private Account account;

    @BeforeEach
    void setUp(){
        account = new CheckingAccount("Bob",new BigDecimal("1000"));
        System.out.println("Before each setup");
    }

    @Test
    void test1(){
        account.deposit(new BigDecimal("50"));
        Assertions.assertEquals(new BigDecimal("1050"),account.getBalance());
        System.out.println("test 1");
    }

    @Test
    void test2(){
        account.deposit(new BigDecimal("100"));
        Assertions.assertEquals(new BigDecimal("1100"),account.getBalance());
        System.out.println("test 2");
    }

    @Test
    @Disabled
    void test3(){
        account.deposit(new BigDecimal("100"));
        Assertions.assertEquals(new BigDecimal("1100"),account.getBalance());
        System.out.println("test 3");
    }
}

