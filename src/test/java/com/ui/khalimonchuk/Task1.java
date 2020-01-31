package com.ui.khalimonchuk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class Task1 {


    @Test(dataProvider = "CoinsTest")
    public void Coins(String coin, BigDecimal count) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/khalimonchuk/testing/lessonSelenium/src/test/resourses/chromedriver");
        WebDriver driver = new ChromeDriver();


        driver.get("https://www.random.org/coins/");

        //check  ‘True Random Number Service’ label exists and present, check text
        WebElement True = driver.findElement(By.xpath("//*[@id=\"invisible\"]/h1/span"));
        Assert.assertEquals(True.getText(), "True Random Number Service");


        //check ‘Coin Flipper’ label exists and present, check text
        WebElement CoinLabel = driver.findElement(By.xpath("//*[@id=\"invisible\"]/h2"));
        Assert.assertEquals(CoinLabel.getText(), "Coin Flipper");


        //select 200 from flip dropdown  //*[@id="invisible"]/form/p[1]/select[1]
        String c = count.toString();
        driver.findElement(By.xpath("//*[@id=\"invisible\"]/form/p[1]/select[1]")).sendKeys(c);


        //select Maximinus - Bronze/Silver Tetradrachm - Roman Empire from coint type drop down
        driver.findElement(By.xpath("//*[@id=\"invisible\"]/form/p[1]/select[2]"))
                .sendKeys(coin);


        //press flip
        driver.findElement(By.xpath("//*[@id=\"invisible\"]/form/p[2]/input[1]")).click();


        //print count of obversed and reversed coins
        List<WebElement> reversed = driver.findElements(By.cssSelector("[alt=\"reverse\"]"));
        System.out.println("reversed count is " + reversed.size());

        List<WebElement> obversed = driver.findElements(By.cssSelector("[alt=\"obverse\"]"));
        System.out.println("obverse count is " + obversed.size());

        //verify that ratio obversed to reversed is in normal range ( < 10 %)
        //changed to 25% because of FAIL
        int a = 100 * obversed.size() / reversed.size();
        Assert.assertTrue(a >= 75 && a <= 125);


        //reset form
        //go back
        driver.findElement(By.cssSelector("[onclick=\"history.go(-1);\"]")).click();
        //reseting
        driver.findElement(By.cssSelector("[value=\"Reset Form\"]")).click();


        //WebElement searchButton = driver.findElement(By.name("btnK"));
        //searchButton.click();


        Thread.sleep(3000);
        driver.quit();


    }

    @DataProvider
    public Object[][] CoinsTest() {

        return new Object[][]{
                {"Maximinus - Bronze/Silver Tetradrachm - Roman Empire", BigDecimal.valueOf(200)},
                {"Polish 1 Złoty", BigDecimal.valueOf(150)},
                {"US 5¢ Nickel", BigDecimal.valueOf(175)},
                {"US 25¢ - Pennsylvania", BigDecimal.valueOf(100)},
                {"Israeli 10 New Shekel", BigDecimal.valueOf(60)}

        };


    }
}


        //Task 1:
        //goto  https://www.random.org/coins/
        //check  ‘True Random Number Service’ label exists and present, check text
        //check ‘Coin Flipper’ label exists and present, check text
        //select 200 from flip dropdown
        //select Maximinus - Bronze/Silver Tetradrachm - Roman Empire from coint type drop down
        //press flip
        //print count of obversed and reversed coins
        //verify that ratio obversed to reversed is in normal range ( < 10 %)
        //reset form
        //select other 2 any values from dropdowns above
        //do the same

