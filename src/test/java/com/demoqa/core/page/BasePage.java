package com.demoqa.core.page;

import com.microsoft.playwright.Page;

public abstract class BasePage <T extends BasePage<T>> {

    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected abstract String path();

    public T open(){
        page.navigate(path());
        page.evaluate("() => {document.querySelector('#banner')?.remove(); document.querySelector" +
                "('footer')?.remove();" + "}");
        return (T) this;

    }
}
