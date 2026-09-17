package com.demoqa.test;

import com.demoqa.core.BaseTest;
import com.demoqa.core.page.CheckboxPage;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckBoxTest extends BaseTest {

    @Test
    public void checkboxTest(){
        CheckboxPage checkboxPage = new CheckboxPage(page).open();
        assertThat(checkboxPage.titles()).hasCount(1);
        assertThat(checkboxPage.titles()).hasText("Home");
    }

    @Test
    public void expandHomeShowChildren(){
        CheckboxPage checkboxPage = new CheckboxPage(page).open().expand("Home");
        assertThat(checkboxPage.titles()).hasCount(4);
        assertThat(checkboxPage.titles()).hasText(new String[]{"Home","Desktop","Documents","Downloads"});
    }

    @Test
    public void checkingFolderSelectsChildren(){
        CheckboxPage checkboxPage = new CheckboxPage(page).open().expandAll().check("Desktop");
        assertThat(checkboxPage.checkbox("Desktop")).hasAttribute("aria-checked","true");
        assertThat(checkboxPage.checkbox("Notes")).hasAttribute("aria-checked","true");
        assertThat(checkboxPage.checkbox("Commands")).hasAttribute("aria-checked","true");
        assertThat(checkboxPage.checkbox("Documents")).hasAttribute("aria-checked","false");
        assertThat(checkboxPage.checkbox("Home")).hasAttribute("aria-checked","mixed");
    }

    @Test
    public void checkingRootSelectEverything(){
        CheckboxPage checkboxPage = new CheckboxPage(page).open().expandAll().check("Home");
        assertThat(checkboxPage.checkboxes()).hasCount(17);
        for (var box : checkboxPage.checkboxes().all()){
            assertThat(box).hasAttribute("aria-checked","true");
        }
    }

}
