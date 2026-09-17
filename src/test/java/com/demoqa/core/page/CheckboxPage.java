package com.demoqa.core.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;


public class CheckboxPage extends BasePage<CheckboxPage> {

    private final Locator closedSwitcher = page.locator(".rc-tree-switcher_close");
    private final Locator nodes = page.getByRole(AriaRole.TREEITEM);
    private final Locator titles = page.locator(".rc-tree-title");
    private final Locator checkboxes = page.getByRole(AriaRole.CHECKBOX);
    private final Locator resultItems = page.locator("#result .text-success");


    public CheckboxPage(Page page) {
        super(page);
    }

    @Override
    protected String path() {
        return "/checkbox";
    }

public CheckboxPage expand(String title){
        nodeByTitle(title).locator(".rc-tree-switcher_close").click();
        return this;
}

public CheckboxPage expandAll(){
        while (closedSwitcher.count()> 0){
            closedSwitcher.first().click();
        }
        return this;
}

private  Locator nodeByTitle(String title){
        return  nodes.filter(new Locator.FilterOptions()
                .setHas(page.locator(".rc-tree-title:text-is('" + title + "')")));
}

public Locator checkbox(String title){
        return page.getByRole(AriaRole.CHECKBOX,new Page.GetByRoleOptions().setName("Select " + title).setExact(true));
}

public CheckboxPage check (String title){
        checkbox(title).click();
        return this;
}

public Locator nodes(){
        return nodes;
}

public Locator titles(){
        return titles;
}

public Locator resultItems(){
        return resultItems;
}

public Locator checkboxes(){
        return checkboxes;
}

}
