package org.example;

import com.github.javafaker.Faker;
import com.google.common.util.concurrent.FakeTimeLimiter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        System.setProperty("webdriver.chrome.driver", "F:chromedriver.exe");
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

        final String fakeEmail = faker.internet().emailAddress();
        email.sendKeys(fakeEmail);
        final String fakeFirstName = faker.name().firstName();
        final String fakeLastName = faker.name().lastName();
        name.sendKeys(fakeFirstName + " " + fakeLastName);
        signupButton.submit();

        //Selektovanje elemenata za treci unos
        WebElement passwordF = driver.findElement(By.id("password"));
        //Unos Sifre
        passwordF.sendKeys("NajjacaSifraIkad");
        // Selektovanje Elemenata za Selektovanje Datuma rodjenja i selektovanje Teksta u njima
        WebElement selectDay = driver.findElement(By.id("days"));
        Select selectingDay = new Select(selectDay);
        selectingDay.selectByVisibleText("15");
        WebElement selectMonth = driver.findElement(By.id("months"));
        Select selectingMonth = new Select(selectMonth);
        selectingMonth.selectByVisibleText("May");
        WebElement selectYear = driver.findElement(By.id("years"));
        Select selectingYear = new Select(selectYear);
        selectingYear.selectByVisibleText("1987");
        //Selektovanje Chekboxsova za promocije
        WebElement newsLetter = driver.findElement(By.id("newsletter"));
        newsLetter.click();
        WebElement specialOffers = driver.findElement(By.id("optin"));
        specialOffers.click();
        //Selektovanje polja za unos imena i unos imena
        WebElement nameField = driver.findElement(By.id("first_name"));
        nameField.sendKeys(fakeFirstName);
        //Selektovanje polja za unos prezimena i unos prezimena
        WebElement lastNameField = driver.findElement(By.id("last_name"));
        lastNameField.sendKeys(fakeLastName);
        //Selektovanje polja za unos adrese i unos adrese
        WebElement address1 = driver.findElement(By.id("address1"));
        address1.sendKeys(faker.address().fullAddress());
        //Selektovanje polja za izbor drzave i izbor drzave
        WebElement country = driver.findElement(By.id("country"));
        Select selectingCountry = new Select(country);
        selectingCountry.selectByVisibleText("Canada");
        //Selektovanje polja za unos adrese i unos adrese
        WebElement state = driver.findElement(By.id("state"));
        state.sendKeys("Alberta");
        //Selektovanje polja za unos grada i unos grada
        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("Cold Lake");
        //Selektovanje polja za unos koda za postu i unos  koda za postu
        WebElement zipcode = driver.findElement(By.id("zipcode"));
        zipcode.sendKeys("T0A - T9X");
        //Selektovanje polja za unos koda za mobile_number i unos  mobile_number-a
        WebElement mobile_number = driver.findElement(By.id("mobile_number"));
        mobile_number.sendKeys("+12505550199");
        //Selektovanje dugmeta "Create Account" i klik na njega putem submit stanja
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/form/button"));
        submitButton.submit();
        //Selektovanje dugmeta "Continue" i klik na njega putem klika
        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a"));
        continueButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("location.reload()");
        //Selektovanje linka za brisanje naloga
        WebElement deleteAccount = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a"));
        System.out.println(deleteAccount.getText());

        do {
            try {
                final WebElement deleteAccount1 = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a"));
                System.out.println(deleteAccount1.getText());
                if (deleteAccount1.getText().equals("Delete Account")){
                    deleteAccount1.click();
                    break;
                }

            }catch (Exception e){
                System.out.println("Exception prvi okinuo");
            }
            js.executeScript("location.reload()");
            Thread.sleep(250);
            try {
                WebElement continueButton1 = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a"));

                continueButton1.click();
            }catch (Exception e){
                System.out.println("Okinuo expection drugi");
            }
        }while (true);
        js.executeScript("location.reload()");





        System.out.println("Hello world!");
        Thread.sleep(10000);
        driver.close();
        driver.quit();
    }


}