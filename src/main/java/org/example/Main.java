package org.example;

import com.github.javafaker.Faker;
import com.google.common.util.concurrent.FakeTimeLimiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/*
Automatizovati navodjenje na sajtu https://automationexercise.com. Otici na pocetnu stranu, kliknuti na "Signup / Login",
 unesite u polja za "New User Signup!" pomocu faker-a - ime i email. Kliknuti dugme Signup.
Pored neophodnih polja na Signup ekranu uneti i date of birth, cekirati Title i "Receive special offers from our partners!". Country staviti na "Canada".
Docekace vas ekran Account created, kliknuti "Continue". Vratice vas na pocetnu stranicu.
Za kraj kliknuti Delete Account, opet kliknuti Continue.

Waitere po potrebi dodavati.


*/

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Podesavanje browsera i drivera i instanciranje dependencija.
        Faker faker = new Faker();
        System.setProperty("webdriver.chrome.driver", "E:chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

 /*    instanciramo Objekat koliko da ceka, i na toj instanci pozivamo njegovu objektnu metodu sta da ceka.
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement nekiElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("NekiID")));
 */
        //SELEKTOVANJE ELEMENTA za prvi klik
        WebElement loginSignUp = driver.findElement(By.cssSelector("#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(4) > a"));

        //Egzekucija komande

        loginSignUp.click();

        //Selektovanje elemenata za drugi unos

        WebElement name = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]"));
        WebElement email = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]"));
        WebElement signupButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button"));

        email.sendKeys(faker.internet().emailAddress());
        name.sendKeys(faker.name().fullName());
        signupButton.submit();

        //Selektovanje elemenata za treci unos
        WebElement passwordF = driver.findElement(By.id("password"));
        passwordF.sendKeys("NajjacaSifraIkad");
        WebElement selectDay = driver.findElement(By.id("days"));
        Select selectingDay = new Select(selectDay);
        selectingDay.selectByVisibleText("15");
        WebElement selectMonth = driver.findElement(By.id("months"));
        Select selectingMonth = new Select(selectMonth);
        selectingMonth.selectByVisibleText("May");
        WebElement selectYear = driver.findElement(By.id("years"));
        Select selectingYear = new Select(selectYear);
        selectingYear.selectByVisibleText("1987");







        System.out.println("Hello world!");
        Thread.sleep(10000);
        driver.close();
        driver.quit();
    }
}