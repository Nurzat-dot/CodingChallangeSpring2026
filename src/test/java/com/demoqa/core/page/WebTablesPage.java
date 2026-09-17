package com.demoqa.core.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.Employee;

import java.util.List;

public class WebTablesPage extends BasePage <WebTablesPage>{

    public static final List<String> HEADERS =
            List.of("First Name","Last Name","Age","Email","Department","Action");
    // Tables
   private final Locator  table = page.locator("table");
   private final Locator headers = page.locator("thead th");
   private final Locator rows = page.locator("tbody tr");

   // Panel Above The Table
    private final Locator addButton = page.locator("#addNewRecordButton");
    private final Locator searchBox = page.locator("#searchBox");
    private final Locator searchButton = page.locator("#basic-addon2");

    // Modal Form
    private final Locator modal = page.locator(".modal-content");
    private final Locator modalTitle = modal.locator(".modal-title");
    private final Locator firstName = modal.locator("#firstName");
    private final Locator lastName = modal.locator("#lastName");
    private final Locator userEmail = modal.locator("#userEmail");
    private final Locator age = modal.locator("#age");
    private final Locator salary = modal.locator("#salary");
    private final Locator department=modal.locator("#department");
    private final Locator submit = modal.locator("#submit");
    private final Locator closeIcon = modal.getByRole(AriaRole.BUTTON,new Locator.GetByRoleOptions().setName("Close"));

    // Pagination Block
    private final Locator firstPageButton = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("First"));
    private final Locator previousPageButton = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Previos"));
    private final Locator nextPageButton  = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Next"));
    private final Locator lastPageButton = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Last"));
    private final Locator pageInfo = page.locator(".pagination").getByText("Page");
    private final Locator pageSizeSelect = page.locator(".pagination select");



    public WebTablesPage(Page page) {
        super(page);
    }

    @Override
    protected String path() {
        return "/webtables";
    }

    public Locator rowWith(String text){
        return rows.filter(new Locator.FilterOptions().setHasText(text));

    }

    public Locator cell (Locator row,String column){
        return row.locator("td").nth(HEADERS.indexOf(column));
    }

    public List<String> column(String column){
        int index = HEADERS.indexOf(column);
        return rows.all().stream()
                .map(row ->row.locator("td").nth(index).innerText())
                .toList();
    }

    public WebTablesPage openForm(){
        addButton.click();
        return this;
    }

    public WebTablesPage fillForm(Employee employee){
        firstName.fill(employee.getFirstName());
        lastName.fill(employee.getLastName());
        age.fill(String.valueOf(employee.getAge()));
        userEmail.fill(employee.getEmail());
        salary.fill(employee.getSalary().toString());
        department.fill(employee.getDepartment());
        return this;
    }



    public WebTablesPage submitForm(){
        submit.click();
        return this;
    }

    public WebTablesPage closeForm(){
        closeIcon.click();
        return this;
    }

    public WebTablesPage addRecord(Employee employee){
        return openForm()
                .fillForm(employee)
                .submitForm();
    }

    public WebTablesPage openEditFor(String text){
        rowWith(text).locator("span[title='Edit']").click();
        return this;
    }

    public WebTablesPage deleteRowWith(String text){
        rowWith(text).locator("span[title='Delete']").click();
        return this;
    }

    public WebTablesPage search (String text){
        searchBox.fill(text);
        return this;
    }

    public WebTablesPage clearSearch(){
        searchBox.clear();
        return this;
    }

    public WebTablesPage nextPage(){
        nextPageButton.click();
        return this;
    }

    public WebTablesPage previousPage(){
        previousPageButton.click();
        return this;
    }

    public WebTablesPage firstPage(){
        firstPageButton.click();
        return this;
    }

    public WebTablesPage lastPage(){
        lastPageButton.click();
        return this;
    }

    public WebTablesPage showRows(int size){
        pageSizeSelect.selectOption(String.valueOf(size));
        return this;
    }

    public Locator rows()     {return rows;}
    public Locator headers()  {return headers;}
    public Locator searchBox() {return searchBox;}
    public Locator modal()     {return modal;}
    public Locator modalTitle() {return modalTitle;}
    public Locator firstNameField() {return firstName;}
    public Locator emailField()     {return userEmail;}
    public Locator salaryField()    {return salary;}
    public Locator pageInfo()        {return pageInfo;}
    public Locator pageSizeSelect()   {return pageSizeSelect;}
    public Locator nextPageButton()   {return nextPageButton;}
    public Locator previousPageButton() {return previousPageButton;}
    public Locator firstPageButton()    {return firstPageButton;}
    public Locator lastPageButton()      {return lastPageButton;}


}
