package nurzat.junit;

import com.jazz.junit.nurzat.bankapp.Account;
import com.jazz.junit.nurzat.bankapp.Bank;
import com.jazz.junit.nurzat.bankapp.CheckingAccount;
import com.jazz.junit.nurzat.bankapp.exceptions.AccountNotFoundException;
import com.jazz.junit.nurzat.bankapp.notification.EmailNotificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankNestedTest {

    private Bank bank;

    @BeforeEach
    void createBank(){
        bank = new Bank("Bakai bank",new EmailNotificationService());
    }

    @Test
    void bankHasName(){
        assertEquals("Bakai bank",bank.getName());
    }

    @Nested
    class EmptyBank{
        @Test
        void hasNoAccounts(){
            assertEquals(0,bank.accountsCount());
        }

        @Test
        void findMissingOwner(){
            assertThrows(AccountNotFoundException.class,
                    () ->bank.findByOwner("Nurzat")
                    );
        }


        @Nested
        class BankWithOneAccount{
            @BeforeEach
            void openOneAccount(){
                Account account =bank.openAccount(new CheckingAccount("Bob"));
                account.deposit(new BigDecimal("1000"));
            }


        }
            @Test
            void countIsOne(){
                assertEquals(1,bank.accountsCount());
            }

            @Test
            void findOwner(){
                Account accountBob = bank.findByOwner("Bob");
                assertEquals(new BigDecimal("1000"),accountBob.getBalance());
            }

    }
}
