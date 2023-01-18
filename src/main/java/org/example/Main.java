package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        System.setProperty("webdriver.chrome.driver", "E:chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

 /*    instanciramo Objekat koliko da ceka, i na toj instanci pozivamo njegovu objektnu metodu sta da ceka.
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement nekiElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("NekiID")));
 */

        WebElement loginSignUp = driver.findElement(By.cssSelector("#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(4) > a"));
        loginSignUp.click();


        System.out.println("Hello world!");
        Thread.sleep(10000);
        driver.close();
        driver.quit();
    }
}