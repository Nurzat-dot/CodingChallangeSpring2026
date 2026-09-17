package com.demoqa.core.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TextBoxPage extends BasePage<TextBoxPage> {

    private final Locator userName = page.getByPlaceholder("Full Name");
    private final Locator userEmail = page.getByPlaceholder("name@example.com");
    private final Locator currentAddress = page.locator("#currentAddress");
    private final Locator permanentAddress = page.locator("#permanentAddress");
    private final Locator submitButton = page.locator("#submit");


    public TextBoxPage(Page page) {
        super(page);
    }

    @Override
    protected String path() {
        return "/text-box";
    }

    public TextBoxPage fillForm(String name, String email, String currentAddr, String permanentAddr) {
        userName.fill(name);
        userEmail.fill(email);
        currentAddress.fill(currentAddr);
        permanentAddress.fill(permanentAddr);
        return this;
    }

    public TextBoxPage submitForm() {
        submitButton.click();
        return this;
    }
}
