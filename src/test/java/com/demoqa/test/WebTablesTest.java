package com.demoqa.test;

import com.demoqa.core.BaseTest;
import com.demoqa.core.page.WebTablesPage;
import com.microsoft.playwright.Locator;
import lombok.Data;
import org.example.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTablesTest extends BaseTest {

    private WebTablesPage table;

    @BeforeEach
    void openTable(){
        table = new WebTablesPage(page).open();
        assertThat(table.rows()).hasCount(3);
    }

    @Nested
    @DisplayName("Default Table")
    class DefaultTable{
        @Test
        @DisplayName("Column Headers")
        void headers(){
            assertThat(table.headers()).hasText(WebTablesPage.HEADERS.toArray(new String[0]));
        }

        @Test
        @DisplayName("Three first names by default order")
        void defaultRows(){
            assertEquals(List.of("Cierra","Alden","Kierra"), table.column("First Name"));
            assertEquals(List.of("cierra@example.com","alden@example.com","kierra@example.com"),table.column("Email"));
        }

        @Test
        @DisplayName("Data of exact row")
        void dataOfExactRow(){
            var alden = table.rowWith("alden@example.com");
            assertThat(table.cell(alden,"Last Name")).hasText("Cantrell");
            assertThat(table.cell(alden,"Age")).hasText("45");
            assertThat(table.cell(alden,"Salary")).hasText("12000");
            assertThat(table.cell(alden,"Department")).hasText("Compliance");
        }

        @Test
        @DisplayName("Each row has Edit and Delete Button")
        void eachRowHasEditAndDelete(){
            assertThat(table.rows().locator("span[title='Edit']")).hasCount(3);
            assertThat(table.rows().locator("span[title='Delete']")).hasCount(3);
        }

        @Nested
        @DisplayName("Add New Record")
        class AddRecord{
            @Test
            @DisplayName("Empty form when Add Button clicked")
            void addOpensEmptyForm(){
                table.openForm();
                assertThat(table.modal()).isVisible();
                assertThat(table.modalTitle()).hasText("Registration Form");
                assertThat(table.firstNameField()).isEmpty();
                assertThat(table.emailField()).isEmpty();
            }

            @Test
            @DisplayName("New Records added to the end of table")
            void addRecord(){
                Employee employee = Employee.builder().firstName("Nurzat")
                        .lastName("Akimzhan kyzy").email("nurar@gmail.com").age(36)
                        .salary(BigDecimal.valueOf(1500000)).department("QA").build();

                table.addRecord(employee);
                assertThat(table.modal()).not().isVisible();
                assertThat(table.rows()).hasCount(4);
                var row = table.rowWith(employee.getEmail());

                assertThat(table.cell(row,"First Name")).hasText(employee.getFirstName());
                assertThat(table.cell(row,"Department")).hasText(employee.getDepartment());

            }
        }
    }
}
