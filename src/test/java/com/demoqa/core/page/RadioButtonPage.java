package com.demoqa.core.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RadioButtonPage extends BasePage <RadioButtonPage>{
    private final Locator yesRadio = page.locator("#yesRadio");
    private final Locator impressiveRadio = page.locator("#impressiveRadio");
    private final Locator noRadio = page.locator("#noRadio");
    private final Locator yesLabel = page.locator("label[for='yesRadio']");
    private final Locator impressiveLabel = page.locator("label[for='impressiveRadio']");
    private final Locator noLabel = page.locator("label[for='noRadio']");
    private final Locator selected = page.locator(".text-success");

    public RadioButtonPage selectYes(){
        yesLabel.click();
        return this;
    }

    public RadioButtonPage selectImpressive(){
        impressiveLabel.click();
        return this;
    }

    public RadioButtonPage select(String text){
        page.locator("label.form-check-label",new Page.LocatorOptions().setHasText(text)).click();
        return this;
    }

    public Locator yesRadio(){
        return yesRadio;
    }

    public Locator impressiveRadio(){
        return impressiveRadio;
    }

    public Locator noRadio(){
        return noRadio;

    }

    public Locator noLabel(){
        return noLabel;
    }

    public Locator selectedText(){
        return selected;
    }


    public RadioButtonPage(Page page) {
        super(page);
    }

    @Override
    protected String path() {
        return "/radio-button";
    }
}
