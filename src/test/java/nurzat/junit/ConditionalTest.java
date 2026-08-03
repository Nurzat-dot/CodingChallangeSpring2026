package nurzat.junit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayName("Account: условия выполнение")
public class ConditionalTest {
    @Test
    @Disabled
    @DisplayName("Тест временно выключен")
    void temporarilyDisabled(){
        throw new IllegalStateException("Shoul not run");
    }
    @Test
    @DisabledOnOs(OS.WINDOWS)
    @DisplayName("Не запустится на Windows")
    void notOnWindows(){
        System.out.println("Не запустится на Windows");
    }
    @Test
    @EnabledOnOs(OS.MAC)
    @DisplayName("Запустится только на Mac")
    void onlyMac(){
        System.out.println("Запустится только на Mac");
    }
    @Test
    @EnabledIfEnvironmentVariable(named = "EVN",matches = "QA")
    @DisplayName("Запустится только для QA")
    void onlyOnQA(){
        System.out.println("Запустится только для QA");
    }
}
// Amazon.com prod -> среда для реальных людей(клиентов)
// Dev.amazon.com dev -> среда для разработчиков
// QA.amazon.com      -> среда для QA
// staging.amazon.com -> промежуточноя среда(тестовая)для заказчиков