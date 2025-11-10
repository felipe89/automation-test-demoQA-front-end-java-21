package page;

import hooks.TestHooks;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class PracticeFormPage {

    private final WebDriver driver;
    private final String URL = "https://demoqa.com/automation-practice-form";

    public PracticeFormPage(){
        this.driver = TestHooks.getDriver();
    }

    public void open(){
        driver.get(URL);
    }

    public void setFirstName(String firstName){
        driver.findElement(By.id("firstName")).sendKeys(firstName);
    }

    public void setLastName(String lastName){
        driver.findElement(By.id("lastName")).sendKeys(lastName);
    }

    public void setEmail(String email){
        driver.findElement(By.id("userEmail")).sendKeys(email);
    }

    public void setGender(String gender) {
        String xpath = String.format("//label[text()='%s']", gender);
        WebElement genderOption = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", genderOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderOption);
    }

    public void setMobile(String mobile){
        driver.findElement(By.id("userNumber")).sendKeys(mobile);
    }

    public void setDateofBirth(String dateText){
        WebElement dateInput = driver.findElement(By.id("dateOfBirthInput"));
        dateInput.click();

        dateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), dateText, Keys.ENTER);
    }

    public void setSubject(String subject){
        WebElement sub = driver.findElement(By.id("subjectsInput"));
        sub.sendKeys(subject);
        sub.sendKeys(Keys.ENTER);
    }

    public void selectHobby(String hobby) {
        String xpath = String.format("//label[text()='%s']", hobby);
        WebElement hobbyOption = driver.findElement(By.xpath(xpath));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hobbyOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbyOption);
    }

    public void submit(){
        WebElement BtnSubmit = driver.findElement(By.id("submit"));
        new Actions(driver).moveToElement(BtnSubmit).click().perform();
    }

    public String getModalText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modalTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-content"))
        );
            return modalTitle.getText();
    }
}
