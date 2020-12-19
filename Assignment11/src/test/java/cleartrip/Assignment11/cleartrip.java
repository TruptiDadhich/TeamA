package cleartrip.Assignment11;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class cleartrip extends extentreport{
	WebDriver driver;


	@Test(priority = 1)
	public void initializeBrowser() {
		report.createTest("initializeBrowser");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.cleartrip.com/");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());

	}

	@Test(priority = 2)
	public void searchFlight() throws InterruptedException {
		report.createTest("searchflight");
		driver.findElement(By.xpath("//a[contains (@title,'international destinations')]")).click();
		String authenticateFlight = driver.findElement(By.xpath("//form[@id='SearchForm']/h1")).getText();
		Assert.assertEquals(authenticateFlight, "Search flights");

		driver.findElement(By.cssSelector("#OneWay")).click();

		driver.findElement(By.id("FromTag")).sendKeys("del");
		Thread.sleep(5000);

		driver.findElement(By.id("FromTag")).sendKeys(Keys.ENTER);

		driver.findElement(By.id("ToTag")).sendKeys("mum");
		Thread.sleep(5000);

		driver.findElement(By.id("ToTag")).sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("//*[@id='DepartDate']")).click();
		int total = driver.findElements(By.xpath("//td[contains(@data-handler,'selectDay')]")).size();
		for (int i = 0; i < total; i++) {
			if (i == 29) {
				driver.findElements(By.xpath("//td[contains(@data-handler,'selectDay')]")).get(i).click();
			}
		}

		Select adults = new Select(driver.findElement(By.id("Adults")));
		adults.selectByValue("1");

		Select Children = new Select(driver.findElement(By.name("childs")));
		Children.selectByValue("1");

		Select Infants = new Select(driver.findElement(By.id("Infants")));
		Infants.selectByValue("1");

		driver.findElement(By.cssSelector("#SearchBtn")).click();

	}

	@Test(priority = 3)
	public void bookFlight() throws InterruptedException {
		report.createTest("bookflight");
		driver.findElement(By.xpath("//p[text()='Non-stop']")).click();

		Actions a = new Actions(driver);
		WebElement slid = driver.findElement(By.className("input-range__slider"));
		a.dragAndDropBy(slid, -199, 0).build().perform();

		driver.findElement(By.xpath("//p[text()='Show multi-airline itineraries']")).click();
        Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(@class,'primary-500')]")).click();

	}

	@Test(priority = 4)
	public void contactDetail() throws InterruptedException {
		report.createTest("contactDetail");
		
		Set<String> window = driver.getWindowHandles();
		Iterator<String> Iterator = window.iterator();
		String parentWin = Iterator.next();
		String childWin = Iterator.next();
		driver.switchTo().window(childWin);

		driver.findElement(By.id("itineraryBtn")).click();

		driver.findElement(By.id("username")).sendKeys("tntteamA@moolya.com");

		driver.findElement(By.id("LoginContinueBtn_1")).click();
		Thread.sleep(5000);
	}

	@Test(priority = 5)
	public void travellerDetail() {
		report.createTest("travellerDetail");
		Select title = new Select(driver.findElement(By.id("AdultTitle1")));
		title.selectByIndex(2);

		driver.findElement(By.id("AdultFname1")).sendKeys("Trupti");

		driver.findElement(By.id("AdultLname1")).sendKeys("Dadhich");

		Select title1 = new Select(driver.findElement(By.id("ChildTitle1")));
		title1.selectByIndex(2);

		driver.findElement(By.id("ChildFname1")).sendKeys("Thiru");

		driver.findElement(By.id("ChildLname1")).sendKeys("Sundar");

		Select day = new Select(driver.findElement(By.id("ChildDobDay1")));
		day.selectByIndex(1);

		Select month = new Select(driver.findElement(By.id("ChildDobMonth1")));
		month.selectByIndex(2);

		Select year = new Select(driver.findElement(By.id("ChildDobYear1")));
		year.selectByIndex(4);

		Select title2 = new Select(driver.findElement(By.id("InfantTitle1")));
		title2.selectByIndex(2);

		driver.findElement(By.id("InfantFname1")).sendKeys("Moolya");

		driver.findElement(By.id("InfantLname1")).sendKeys("Assignment");

		Select day1 = new Select(driver.findElement(By.id("InfantDobDay1")));
		day1.selectByIndex(1);

		Select month1 = new Select(driver.findElement(By.id("InfantDobMonth1")));
		month1.selectByIndex(2);

		Select year1 = new Select(driver.findElement(By.id("InfantDobYear1")));
		year1.selectByIndex(2);

		driver.findElement(By.id("mobileNumber")).sendKeys("8283929322");

		driver.findElement(By.id("travellerBtn")).click();
		report.flush();
		driver.quit();
	}
}
