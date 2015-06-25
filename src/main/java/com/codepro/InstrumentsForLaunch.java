package com.codepro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InstrumentsForLaunch {

    private WebDriver driver = new FirefoxDriver();
    private final String url = "http://www.google.ru/";
    private String encoding = System.getProperty("console.encoding", "utf-8");
    private Scanner scanner = new Scanner(System.in, encoding);
    private final int timeOut = 20;

    public InstrumentsForLaunch() {

    }

    public void run() {

        infoAboutGoogleSearch();

        driver.get(url);

        startGooglePage();

        createNewWindow();

    }

    public void startGooglePage() {

        GoogleHomePageObjects pageObjects = new GoogleHomePageObjects(driver);

        System.out.print("Enter your request: ");

        String txt = scanner.nextLine();

        pageObjects.searchGoogle(txt);

        (new WebDriverWait(driver, timeOut))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

    }

    public void createNewWindow() {

        while (driver != null) {

            try {
                int numberOfPages = scanner.nextInt();

                if (numberOfPages == 999) {
                    showAllLinks();
                    continue;
                }

                if (numberOfPages == 123) {
                    driver.close();
                    driver.quit();
                }

                goToTheWebsite(numberOfPages);


            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "Invalid Parameter. Please Restart Program",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                closeAllWindows();
                break;

            } catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Invalid Parameter", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                System.out.print("Enter the Next Page Number From (0 - 15): ");

            } catch (IndexOutOfBoundsException e) {
                closeAllWindows();
                System.out.print("Enter the Next Page Number From (0 - 15): ");

            } catch (NoSuchElementException e) {
                e.getMessage();

            } catch (SessionNotFoundException e) {
                e.getMessage();
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

        System.out.println("Title Page: " + findElement.get(number).getText());
        System.out.print("Enter the Next Page Number From (0 - 15): ");

    }

    public void closeAllWindows() {
        String originalHandle = driver.getWindowHandle();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
    }

    public void showAllLinks() {

        System.out.println();
        System.out.println("----------------SEARCH RESULTS--------------------");
        List<WebElement> findElement = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
        int i = 0;
        for (WebElement webElement : findElement) {
            i++;
            System.out.println("Title Page: " + webElement.getText());
            System.out.println("Link On Page: " + webElement.getAttribute("href"));
            System.out.println("-------------------------------");
        }

        System.out.print("Enter the Next Page Number From (0 - 15): ");
    }

    public void infoAboutGoogleSearch() {
        System.out.println("---Navigate On The Program---");
        System.out.println("Use The Next Number: ");
        System.out.println("** Close All Windows Except Main. Any Number > 50");
        System.out.println("** Show All Links. Number Should Be 999");
        System.out.println("** Exit. Number Should be 123");
        System.out.println();
    }


}