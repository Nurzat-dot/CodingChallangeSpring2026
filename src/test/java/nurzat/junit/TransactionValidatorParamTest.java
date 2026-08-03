package nurzat.junit;

import com.jazz.junit.nurzat.bankapp.Account;
import com.jazz.junit.nurzat.bankapp.CheckingAccount;
import com.jazz.junit.nurzat.bankapp.TransactionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionValidatorParamTest {

    @ParameterizedTest(name = "[{index}]amount = {0} - валидный")
    @ValueSource(strings = {"0.01","1","100","1000","10000","100000","1000000"})
    void validAmountsTest(String amount){
        Assertions.assertTrue(TransactionValidator.isValidAmount(new BigDecimal(amount)));
    }
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ","\t","\n"})
    void blanksOwnersAreInvalid(String owner){
        Assertions.assertFalse(TransactionValidator.isValidOwner(owner));
    }

    @ParameterizedTest
    @CsvSource({"10,5,15",
    "200,300,500",
    "0,1,1"})
    void depositAddToBalance(String initial,String depositAmount,String exspectedBalance){
        Account account = new CheckingAccount("Bob",new BigDecimal(initial));
        account.deposit(new BigDecimal(depositAmount));
        assertEquals(new BigDecimal(exspectedBalance),account.getBalance());
    }
    @ParameterizedTest
    @MethodSource("withDrawScenarios")
    void withDrawScenariosTest(String owner,String initial,String draw,String equals){
        Account account = new CheckingAccount(owner,new BigDecimal(initial));
        account.withdraw(new BigDecimal(draw));
        assertEquals(new BigDecimal(equals),account.getBalance());
    }

    static Stream<Arguments>withDrawScenarios(){
        return Stream.of(
                Arguments.of("Bob","100","40","60"),
                Arguments.of("Aleks","500","500","0"),
                Arguments.of("Steve","1000","1","999")
        );
    }
}
