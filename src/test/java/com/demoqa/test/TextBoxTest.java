package com.demoqa.test;

import com.demoqa.core.BaseTest;
import com.demoqa.core.page.TextBoxPage;
import org.junit.jupiter.api.Test;


public class TextBoxTest extends BaseTest {

    @Test
    public void fillFormTest(){
     TextBoxPage textBoxPage = new TextBoxPage(page)
        .open()
                .fillForm("John","john@gmail.com","Bishkek","Kyrgyzstan");


    }
}
