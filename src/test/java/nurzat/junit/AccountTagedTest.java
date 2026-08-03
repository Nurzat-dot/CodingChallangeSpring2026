package nurzat.junit;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Apple Pay")
public class AccountTagedTest {
    @Test
    @Tag("Smoke")
    void openAccount(){

    }

}
