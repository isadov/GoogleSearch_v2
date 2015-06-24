package com.codepro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class InstrumentsForLaunch {

    private WebDriver driver = new FirefoxDriver();
    private final String url = "http://www.google.ru/";
    private Scanner scanner = new Scanner(System.in);
    private final int timeOut = 20;

    public InstrumentsForLaunch() {

    }

    public void run() {

        driver.get(url);

        startGooglePage();

        createNewTab();

        showAllUrl();

    }

    public void startGooglePage() {

        GoogleHomePageObjects pageObjects = new GoogleHomePageObjects(driver);

        System.out.print("Your Text For Search: ");

        String txt = scanner.nextLine();

        pageObjects.searchGoogle(txt);

        (new WebDriverWait(driver, timeOut))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

    }

    public void createNewTab() {

        int i = 10;

        while (driver != null) {

            int numberOfPages = scanner.nextInt();
            goToTheWebsite(numberOfPages);
            i--;

            if (i <= 0) {
                closeWindow();
                JOptionPane.showMessageDialog(null, "Please Restart The Program. Open More Than 10 Window's",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
    }

    public void goToTheWebsite(int number) {
        List<WebElement> findElement = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

        for (WebElement webElement : findElement) {
            webElement.getText();
            webElement.getAttribute("href");
        }
        findElement.addAll(findElement);
        findElement.get(number).click();

        System.out.println("Page Named: " + findElement.get(number).getText());
        System.out.print("Input Next Number Page. From (0 - 10): ");
    }

    public void closeWindow() {
        String originalHandle = driver.getWindowHandle();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
    }

    public void showAllUrl() {

        System.out.println();
        System.err.println("----------------SEARCH RESULT--------------------");
        List<WebElement> findElement = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

        for (WebElement webElement : findElement) {
            System.out.println("Name Of Window: " + webElement.getText().toUpperCase());
            System.out.println("Link On Page: " + webElement.getAttribute("href"));
            System.out.println("--------------------------");
        }
    }


}