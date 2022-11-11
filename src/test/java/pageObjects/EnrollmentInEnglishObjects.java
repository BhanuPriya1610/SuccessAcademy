package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.WebsiteLaunching;

public class EnrollmentInEnglishObjects extends WebsiteLaunching {

    @FindBy(xpath = "//*[@text = \"Enroll\"]")
    public WebElement enrollButton;

//html/body/header/div[3]/div/div[1]/div/a[1]/button
}
