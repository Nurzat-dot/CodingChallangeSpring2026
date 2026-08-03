package nurzat.junit;

import com.jazz.junit.nurzat.bankapp.Account;
import com.jazz.junit.nurzat.bankapp.CheckingAccount;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

public class AccountBasicTest {
    private Account account;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Before All: 1 раз до всех тестов класса");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each: перед каждым тестом создаем новый счет");
        account = new CheckingAccount("Bob", new BigDecimal("100"));
    }

    @AfterEach
    void afterEachTests() {
        System.out.println("After Each: тест закончился баланс " + account.getBalance());
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("After All: 1 раз после всех тестов");
    }

    @Test
    @DisplayName("Депозит увеличивает баланс")
    void depositIncresesBalance() {
        account.deposit(new BigDecimal("50"));
        Assertions.assertEquals(new BigDecimal("150"), account.getBalance());
    }

    @Test
    @DisplayName("Снятия уменьшает баланс ")
    void withDrawalDecreasesBalance() {
        account.withdraw(new BigDecimal("30"));
        Assertions.assertEquals(new BigDecimal("70"), account.getBalance());
    }
    @Test
    @DisplayName("Начальный баланс сохраняется")
    void intialBalanceIsSet () {
        Assertions.assertEquals(new BigDecimal("100"), account.getBalance());
    }
}