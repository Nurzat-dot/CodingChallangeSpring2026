package nurzat.junit;

import com.jazz.junit.nurzat.bankapp.Account;
import com.jazz.junit.nurzat.bankapp.CheckingAccount;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AccountAssumtionsTest {

    @Test
    void runOnlyOnStaging(){
        Assumptions.assumeFalse("stage".equals("stage"),"Skipped if env != stage");
        Account account = new CheckingAccount("Bob",new BigDecimal("1000"));
    }
}
