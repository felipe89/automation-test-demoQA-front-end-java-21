package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestHooks {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        WebDriver wd = new ChromeDriver();
        wd.manage().window().maximize();
        driver.set(wd);
    }

    @After
    public void tearDown(){
        if (driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }

    public static WebDriver getDriver(){
        return driver.get();
    }
}
