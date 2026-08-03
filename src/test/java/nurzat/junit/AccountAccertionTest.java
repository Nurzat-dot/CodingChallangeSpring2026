package nurzat.junit;

import com.jazz.junit.nurzat.bankapp.*;
import com.jazz.junit.nurzat.bankapp.notification.EmailNotificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@DisplayName("Account demo for all assertions")
public class AccountAccertionTest {

    @Test
    @DisplayName("1")
    void equalsCheck() {
        Account account = new SavingsAccount("Bob", new BigDecimal("500"), new BigDecimal("0.05"));
        Assertions.assertEquals(new BigDecimal("500"), account.getBalance());
        Assertions.assertEquals("Bob", account.getOwner());
    }

    @Test
    @DisplayName("2")
    void booleanCheck() {
        Account account = new CheckingAccount("Bob");
        Assertions.assertNotEquals(new BigDecimal("1000"), account.getBalance());
    }

    @Test
    @DisplayName("3")
    void notEqualsCheck() {
        Account account = new CheckingAccount("Bob", new BigDecimal("10"));
        Assertions.assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
        Assertions.assertFalse(account.getOwner().isBlank());
    }

    @Test
    @DisplayName("5")
    void nullCheck() {
        Bank bank = new Bank("Bakai", new EmailNotificationService());
        Assertions.assertNotNull(bank.getName());
    }

    @Test
    @DisplayName("4")
    void sameCheck() {
        Bank bank = new Bank("Bakai", new EmailNotificationService());
        Account account = bank.openAccount(new CheckingAccount("Alice"));
        Account fromBank = bank.findByOwner("Alice");
        Assertions.assertSame(account, fromBank, "Should return same object");

    }

    @Test
    @DisplayName("6")
    void assertAllCheck() {
        Account account = new BusinessAccount("Nurzat",new BigDecimal("100000"));
        Assertions.assertAll("Состояние нового счета",
                ()->Assertions.assertEquals("Nurzat",account.getOwner()),
                ()->Assertions.assertEquals(AccountType.BUSINESS,account.getType()),
                ()->Assertions.assertEquals(new BigDecimal("100000"),account.getBalance()),
                ()->Assertions.assertNotNull(account.getBalance())
                );
    }

}