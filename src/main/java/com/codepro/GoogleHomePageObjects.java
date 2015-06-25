package com.codepro;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleHomePageObjects {

    private Wait<WebDriver> wait;

    public GoogleHomePageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(name = "q")
    private WebElement txtSearch;

    @FindBy(name = "btnG")
    private WebElement btnSearch;


    public void searchGoogle(String searchText) {

        txtSearch.sendKeys(searchText);
        btnSearch.click();
        System.out.print("Enter Page Number From (0 - 10): ");

    }
}
