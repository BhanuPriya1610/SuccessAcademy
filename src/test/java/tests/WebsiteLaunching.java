package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.MalformedURLException;

public class BaseTest {

    public WebDriver driver;


    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        System.out.println(path);
        System.setProperty("webdriver.chrome.driver", path + "/src/Drivers/chromedriver 10");
        WebDriver driver = new ChromeDriver();
        driver.get(" https://www.successacademies.org");

    }
}
