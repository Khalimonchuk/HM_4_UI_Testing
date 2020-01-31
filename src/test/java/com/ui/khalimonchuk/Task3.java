package com.ui.khalimonchuk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class Task3 {

    @Test
    public void LoginTest() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/khalimonchuk/testing/lessonSelenium/src/test/resourses/chromedriver");
        WebDriver driver = new ChromeDriver();


        driver.get("https://sites.google.com/view/library-automation-lits/home");
        String winHandleBefore = driver.getWindowHandle();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("[aria-label=\"Login\"]")).click();
        Thread.sleep(6000);

        //switch to new window
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        driver.getWindowHandles();

        //data for login
        String email = "i.khalimonchuk@gmail.com";
        String password = "1kh23456";

        //set login and pass, than login
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("btn-login")).click();
        Thread.sleep(10000);

        //any objects on new page after login
        List<WebElement> suggestions = driver.findElements(By.cssSelector("[class=\"media text-muted pt-3 ng-star-inserted\"]"));
        Assert.assertTrue(suggestions.size()>0);
        System.out.println(suggestions.size());

        driver.close();
        driver.quit();


    }
}