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

    public void submit() {
        WebElement btnSubmit = driver.findElement(By.id("submit"));

        // Scroll até o elemento para garantir que esteja visível na tela
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnSubmit);

        try {
            // Tentativa principal utilizando Actions (melhor para elementos clicáveis)
            new Actions(driver).moveToElement(btnSubmit).pause(Duration.ofMillis(300)).click().perform();
        } catch (Exception e) {
            System.out.println("⚠️ Falha ao clicar com Actions, tentando via JavaScript...");
            // Fallback - clique forçado via JS (funciona mesmo com overlay invisível)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSubmit);
        }

        // Pequena espera para o modal iniciar abertura (importante no headless)
        try {
            Thread.sleep(800);
        } catch (InterruptedException ignored) {}
    }

    public String getModalText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement modalTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-content"))
        );
            return modalTitle.getText();
    }
}
