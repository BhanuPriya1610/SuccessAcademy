package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.AppLogger;
import utils.SendingEmails;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BaseTest {

	public WebDriver driver;
	public static final int DEFAULT_WAIT_UNTIL_DURATION = 10;
	public static final long DEFAULT_WAIT_TIME = 120;
	SendingEmails send = new SendingEmails();

	public void waitForPageloadComplete() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_UNTIL_DURATION);
		wait.until(pageLoadCondition);
	}

	public void waitEvent(int time) throws Exception {
		AppLogger.log("Waiting for " + time + " seconds.");
		Thread.sleep(time * 1000);
	}

	public void waitEvent() throws Exception {
		Thread.sleep(DEFAULT_WAIT_TIME);
	}

	public WebDriver homePageLanding() throws Exception {
		String path = System.getProperty("user.dir");
		System.out.println(path);
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		driver.get("https://www.successacademies.org");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--disable-notifications");
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		System.out.println("DIRVER  :: " + driver);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		// driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		return driver;

	}

	@Test(priority = 1)
	public void enrollmentInEnglishTest() throws Exception {

		homePageLanding();
		// clicking on enroll button in homepage
		driver.findElement(By.xpath("//button[text()='Enroll']")).click();
		waitEvent(5);
		// clicking on apply for next year button
		driver.findElement(By.xpath("//*[@id=\"link-sauxjewkmym\"]")).click();
		waitForPageloadComplete();
		// giving input for email id
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//a[normalize-space()='Continue']")).click();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	@Test(priority = 2)
	public void enrollmentInEnglishTestInSpanishLanguage() throws Exception {

		homePageLanding();
		WebElement selectLanguage = driver.findElement(By.xpath("//select[@aria-label='Language Translate Widget']"));
		selectLanguage.click();
		waitEvent();
		// WebElement spanish =
		// driver.findElement(By.xpath("//*[@id=\\\":0.targetLanguage\\\"]/select/option[110]"));
		Select options = new Select(driver.findElement(By.className("goog-te-combo")));
		options.selectByValue("es");
		driver.findElement(By.xpath("/html/body/header/div[3]/div/div[1]")).click();
		waitEvent();
		// spanish.click();
		driver.findElement(By.xpath(
				"//button[@class='btn bg-orange text-white mt-100']//font//font[contains(text(),'Inscribirse')]"))
				.click();
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"link-fxsgwv7hrjh\"]")).click();
		// driver.findElement(By.xpath("//button[@text= 'Presente la solicitud para el
		// próximo año']")).click();
		waitForPageloadComplete();
		driver.findElement(By.xpath("//span[normalize-space()='Select Language']")).click();
		waitEvent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[text()='Spanish']")).click();
		waitEvent();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:j_id6\"]/div[7]/a")).click();
		waitEvent();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	@Test(priority = 3)
	public void enrollmentInEnglishTestInChineseLanguage() throws Exception {

		homePageLanding();
		WebElement selectLanguage = driver.findElement(By.xpath("//select[@aria-label='Language Translate Widget']"));
		selectLanguage.click();
		waitEvent();
		// WebElement spanish =
		// driver.findElement(By.xpath("//*[@id=\\\":0.targetLanguage\\\"]/select/option[110]"));
		Select options = new Select(driver.findElement(By.className("goog-te-combo")));
		options.selectByValue("es");
		driver.findElement(By.xpath("/html/body/header/div[3]/div/div[1]")).click();
		waitEvent();
		// spanish.click();
		driver.findElement(By.xpath(
				"//button[@class='btn bg-orange text-white mt-100']//font//font[contains(text(),'Inscribirse')]"))
				.click();
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"link-fxsgwv7hrjh\"]")).click();
		// driver.findElement(By.xpath("//button[@text= 'Presente la solicitud para el
		// próximo año']")).click();
		waitForPageloadComplete();
		driver.findElement(By.xpath("//span[normalize-space()='Select Language']")).click();
		waitEvent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[text()='Chinese (Simplified)']")).click();
		waitEvent();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:j_id6\"]/div[7]/a")).click();
		waitEvent();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	@Test(priority = 4)
	public void enrollmentInEnglishTestInFrenchLanguage() throws Exception {

		homePageLanding();
		WebElement selectLanguage = driver.findElement(By.xpath("//select[@aria-label='Language Translate Widget']"));
		selectLanguage.click();
		waitEvent();
		// WebElement spanish =
		// driver.findElement(By.xpath("//*[@id=\\\":0.targetLanguage\\\"]/select/option[110]"));
		Select options = new Select(driver.findElement(By.className("goog-te-combo")));
		options.selectByValue("es");
		driver.findElement(By.xpath("/html/body/header/div[3]/div/div[1]")).click();
		waitEvent();
		// spanish.click();
		driver.findElement(By.xpath(
				"//button[@class='btn bg-orange text-white mt-100']//font//font[contains(text(),'Inscribirse')]"))
				.click();
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"link-fxsgwv7hrjh\"]")).click();
		// driver.findElement(By.xpath("//button[@text= 'Presente la solicitud para el
		// próximo año']")).click();
		waitForPageloadComplete();
		driver.findElement(By.xpath("//span[normalize-space()='Select Language']")).click();
		waitEvent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[text()='French']")).click();
		waitEvent();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:j_id6\"]/div[7]/a")).click();
		waitEvent();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	@Test(priority = 5)
	public void enrollmentInEnglishTestInHaitianCreoleLanguage() throws Exception {

		homePageLanding();
		WebElement selectLanguage = driver.findElement(By.xpath("//select[@aria-label='Language Translate Widget']"));
		selectLanguage.click();
		waitEvent();
		// WebElement spanish =
		// driver.findElement(By.xpath("//*[@id=\\\":0.targetLanguage\\\"]/select/option[110]"));
		Select options = new Select(driver.findElement(By.className("goog-te-combo")));
		options.selectByValue("es");
		driver.findElement(By.xpath("/html/body/header/div[3]/div/div[1]")).click();
		waitEvent();
		// spanish.click();
		driver.findElement(By.xpath(
				"//button[@class='btn bg-orange text-white mt-100']//font//font[contains(text(),'Inscribirse')]"))
				.click();
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"link-fxsgwv7hrjh\"]")).click();
		// driver.findElement(By.xpath("//button[@text= 'Presente la solicitud para el
		// próximo año']")).click();
		waitForPageloadComplete();
		driver.findElement(By.xpath("//span[normalize-space()='Select Language']")).click();
		waitEvent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[text()='Haitian Creole']")).click();
		waitEvent();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:j_id6\"]/div[7]/a")).click();
		waitEvent();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	@Test(priority = 6)
	public void enrollmentInEnglishTestInRussianLanguage() throws Exception {
		homePageLanding();
		WebElement selectLanguage = driver.findElement(By.xpath("//select[@aria-label='Language Translate Widget']"));
		selectLanguage.click();
		waitEvent();
		// WebElement spanish =
		// driver.findElement(By.xpath("//*[@id=\\\":0.targetLanguage\\\"]/select/option[110]"));
		Select options = new Select(driver.findElement(By.className("goog-te-combo")));
		options.selectByValue("es");
		driver.findElement(By.xpath("/html/body/header/div[3]/div/div[1]")).click();
		waitEvent();
		// spanish.click();
		driver.findElement(By.xpath(
				"//button[@class='btn bg-orange text-white mt-100']//font//font[contains(text(),'Inscribirse')]"))
				.click();
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"link-fxsgwv7hrjh\"]")).click();
		// driver.findElement(By.xpath("//button[@text= 'Presente la solicitud para el
		// próximo año']")).click();
		waitForPageloadComplete();
		driver.findElement(By.xpath("//span[normalize-space()='Select Language']")).click();
		waitEvent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[text()='Russian']")).click();
		waitEvent();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:j_id6\"]/div[7]/a")).click();
		waitEvent();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	@Test(priority = 7)
	public void enrollmentInSpanishTestInSpanishLanguage() throws Exception {

		homePageLanding();
		WebElement selectLanguage = driver.findElement(By.xpath("//select[@aria-label='Language Translate Widget']"));
		selectLanguage.click();
		waitEvent();
		// WebElement spanish =
		// driver.findElement(By.xpath("//*[@id=\\\":0.targetLanguage\\\"]/select/option[110]"));
		Select options = new Select(driver.findElement(By.className("goog-te-combo")));
		options.selectByValue("es");
		driver.findElement(By.xpath("/html/body/header/div[3]/div/div[1]")).click();
		waitEvent();
		// spanish.click();
		driver.findElement(By.xpath(
				"//button[@class='btn bg-orange text-white mt-100']//font//font[contains(text(),'Inscribirse')]"))
				.click();
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"link-fxsgwv7hrjh\"]")).click();
		// driver.findElement(By.xpath("//button[@text= 'Presente la solicitud para el
		// próximo año']")).click();
		waitForPageloadComplete();
		driver.findElement(By.xpath("//span[normalize-space()='Select Language']")).click();
		waitEvent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[text()='Spanish']")).click();
		waitEvent();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:j_id6\"]/div[7]/a")).click();
		waitEvent();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	@Test(priority = 8)
	public void enrollmentInSpanishTestInChineseLanguage() throws Exception {

		homePageLanding();
		WebElement selectLanguage = driver.findElement(By.xpath("//select[@aria-label='Language Translate Widget']"));
		selectLanguage.click();
		waitEvent();
		// WebElement spanish =
		// driver.findElement(By.xpath("//*[@id=\\\":0.targetLanguage\\\"]/select/option[110]"));
		Select options = new Select(driver.findElement(By.className("goog-te-combo")));
		options.selectByValue("es");
		driver.findElement(By.xpath("/html/body/header/div[3]/div/div[1]")).click();
		waitEvent();
		// spanish.click();
		driver.findElement(By.xpath(
				"//button[@class='btn bg-orange text-white mt-100']//font//font[contains(text(),'Inscribirse')]"))
				.click();
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"link-fxsgwv7hrjh\"]")).click();
		// driver.findElement(By.xpath("//button[@text= 'Presente la solicitud para el
		// próximo año']")).click();
		waitForPageloadComplete();
		driver.findElement(By.xpath("//span[normalize-space()='Select Language']")).click();
		waitEvent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[text()='Chinese (Simplified)']")).click();
		waitEvent();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:j_id6\"]/div[7]/a")).click();
		waitEvent();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	@Test(priority = 9)
	public void enrollmentInSpanishTestInFrenchLanguage() throws Exception {

		homePageLanding();
		WebElement selectLanguage = driver.findElement(By.xpath("//select[@aria-label='Language Translate Widget']"));
		selectLanguage.click();
		waitEvent();
		// WebElement spanish =
		// driver.findElement(By.xpath("//*[@id=\\\":0.targetLanguage\\\"]/select/option[110]"));
		Select options = new Select(driver.findElement(By.className("goog-te-combo")));
		options.selectByValue("es");
		driver.findElement(By.xpath("/html/body/header/div[3]/div/div[1]")).click();
		waitEvent();
		// spanish.click();
		driver.findElement(By.xpath(
				"//button[@class='btn bg-orange text-white mt-100']//font//font[contains(text(),'Inscribirse')]"))
				.click();
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"link-fxsgwv7hrjh\"]")).click();
		// driver.findElement(By.xpath("//button[@text= 'Presente la solicitud para el
		// próximo año']")).click();
		waitForPageloadComplete();
		driver.findElement(By.xpath("//span[normalize-space()='Select Language']")).click();
		waitEvent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[text()='French']")).click();
		waitEvent();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:j_id6\"]/div[7]/a")).click();
		waitEvent();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	@Test(priority = 10)
	public void enrollmentInSpanishTestInHaitianCreoleLanguage() throws Exception {

		homePageLanding();
		WebElement selectLanguage = driver.findElement(By.xpath("//select[@aria-label='Language Translate Widget']"));
		selectLanguage.click();
		waitEvent();
		// WebElement spanish =
		// driver.findElement(By.xpath("//*[@id=\\\":0.targetLanguage\\\"]/select/option[110]"));
		Select options = new Select(driver.findElement(By.className("goog-te-combo")));
		options.selectByValue("es");
		driver.findElement(By.xpath("/html/body/header/div[3]/div/div[1]")).click();
		waitEvent();
		// spanish.click();
		driver.findElement(By.xpath(
				"//button[@class='btn bg-orange text-white mt-100']//font//font[contains(text(),'Inscribirse')]"))
				.click();
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"link-fxsgwv7hrjh\"]")).click();
		// driver.findElement(By.xpath("//button[@text= 'Presente la solicitud para el
		// próximo año']")).click();
		waitForPageloadComplete();
		driver.findElement(By.xpath("//span[normalize-space()='Select Language']")).click();
		waitEvent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[text()='Haitian Creole']")).click();
		waitEvent();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:j_id6\"]/div[7]/a")).click();
		waitEvent();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	@Test(priority = 11)
	public void enrollmentInSpanishTestInRussianLanguage() throws Exception {

		homePageLanding();
		WebElement selectLanguage = driver.findElement(By.xpath("//select[@aria-label='Language Translate Widget']"));
		selectLanguage.click();
		waitEvent();
		// WebElement spanish =
		// driver.findElement(By.xpath("//*[@id=\\\":0.targetLanguage\\\"]/select/option[110]"));
		Select options = new Select(driver.findElement(By.className("goog-te-combo")));
		options.selectByValue("es");
		driver.findElement(By.xpath("/html/body/header/div[3]/div/div[1]")).click();
		waitEvent();
		// spanish.click();
		driver.findElement(By.xpath(
				"//button[@class='btn bg-orange text-white mt-100']//font//font[contains(text(),'Inscribirse')]"))
				.click();
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"link-fxsgwv7hrjh\"]")).click();
		// driver.findElement(By.xpath("//button[@text= 'Presente la solicitud para el
		// próximo año']")).click();
		waitForPageloadComplete();
		driver.findElement(By.xpath("//span[normalize-space()='Select Language']")).click();
		waitEvent();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//span[text()='Russian']")).click();
		waitEvent();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:username\"]")).sendKeys("bpvsu1234@gmail.com");
		waitEvent();
		driver.findElement(By.xpath("//*[@id=\"landingPage:landingForm:j_id6\"]/div[7]/a")).click();
		waitEvent();
		waitForPageloadComplete();
		try {
			Assert.assertTrue(
					driver.findElement(By.xpath("//form[contains(@name,'create-account-form')]")).isDisplayed());
			emailSend("Passed!");
		} catch (Exception e) {
			emailSend("Failed");
		}

	}

	public void emailSend(String msg) throws Exception {
//    send.sendEmail("senderEmail", "senderPassword", "bhanupriya@grenerobotics.com", "Automation Report", msg);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.close();
	}

	/*
	 * public void mouseOverElement(WebElement element) { Actions actions = new
	 * Actions(driver); actions.moveToElement(element).perform(); }
	 * 
	 * public void waitForElementToBeVisibile(By xpath) { WebDriverWait wait = new
	 * WebDriverWait(driver, 45);
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(xpath)); }
	 * 
	 * public void waitUntilElementVisibilility(WebElement element) { WebDriverWait
	 * wait = new WebDriverWait(driver, 45);
	 * wait.until(ExpectedConditions.invisibilityOf(element)); }
	 */

}
