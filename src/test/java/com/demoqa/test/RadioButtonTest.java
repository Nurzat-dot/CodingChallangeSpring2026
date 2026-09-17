package com.demoqa.test;

import com.demoqa.core.BaseTest;
import com.demoqa.core.page.RadioButtonPage;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RadioButtonTest extends BaseTest {

    @Test
    public void radioButtonTest(){
        RadioButtonPage radioButtonPage = new RadioButtonPage(page).open().selectYes();
        assertThat(radioButtonPage.yesRadio()).isChecked();
        assertThat(radioButtonPage.selectedText()).hasText("Yes");
    }

    @Test
    public void onlyOneRadioIsChecked(){
        RadioButtonPage radioButtonPage = new RadioButtonPage(page).open().selectYes().selectImpressive();
        assertThat(radioButtonPage.impressiveRadio()).isChecked();
        assertThat(radioButtonPage.yesRadio()).not().isChecked();
        assertThat(radioButtonPage.selectedText()).hasText("Impressive");
    }

    @Test
    public void noRadioIsDisabled(){
        RadioButtonPage radioButtonPage = new RadioButtonPage(page).open();
        assertThat(radioButtonPage.noRadio()).isDisabled();
    }
}
