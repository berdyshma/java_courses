package by.berdysh.java_course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CorrectPage {

	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void start() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);

		driver.get("http://localhost/litecart/admin/login.php");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("login")).click();
	}

	@Test
	public void task_10() {
		driver.get("http://localhost/litecart/en/");

		WebElement duck = driver.findElement(By.cssSelector("div#box-campaigns li.product"));

		//Is Regular price crossed on the Main page?
		String styleRegularPriceOnMainPage = duck.findElement(By.cssSelector
						("s[class=regular-price]")).getCssValue("text-decoration-line");


		//Is Regular price grey on the Main page?
		String colorRegularPriceOnMainPage = duck.findElement(By.cssSelector
						("s[class=regular-price]")).getCssValue("color");
		List<Integer> rgb = parseColor(colorRegularPriceOnMainPage);


		//Is Campaign price bold on the Main page?
		String styleCampaignPriceOnMainPage = duck.findElement(By.cssSelector
						("strong[class=campaign-price]")).getCssValue("font-weight");

		//Is Campaign price red on the Main page?
		String colorCampaignPriceOnMainPage = duck.findElement(By.cssSelector
						("strong[class=campaign-price]")).getCssValue("color");
		rgb = parseColor(colorCampaignPriceOnMainPage);


		//Is Campaign price bigger than Regular price on the Main page?
		Dimension sizeRegularPriceString = duck.findElement(By.cssSelector
						("s[class=regular-price]")).getSize();
		Dimension sizeCampaignPriceString = duck.findElement(By.cssSelector
						("strong[class=campaign-price]")).getSize();


		//Save some info from the Main page for future checking
		String nameOnMainPage = duck.findElement(By.cssSelector("div[class=name]")).getAttribute("textContent");
		String regularPriceOnMainPage = duck.findElement(By.cssSelector
						("s[class=regular-price]")).getAttribute("textContent");
		String campaignPriceOnMainPage = duck.findElement(By.cssSelector
						("strong[class=campaign-price]")).getAttribute("textContent");

		//Go to the product own page
		duck.click();

		//Are product tittles on Main and Own pages are not equal?
		String nameOnOwnPage = driver.findElement(By.cssSelector(
						"h1[class=title]")).getAttribute("textContent");


		//Are Regular prices on Main and Own pages are not equal?
		String regularPriceOnOwnPage = driver.findElement(By.cssSelector
						("s[class=regular-price]")).getAttribute("textContent");


		//Are Campaign prices on Main and Own pages are not equal?
		String campaignPriceOnOwnPage = driver.findElement(By.cssSelector
						("strong[class=campaign-price]")).getAttribute("textContent");


		//Is Regular price crossed on the Own page?
		String styleRegularPrice = driver.findElement(By.cssSelector
						("s[class=regular-price]")).getCssValue("text-decoration-line");


		//Is Regular price grey on the Own page?
		String colorRegularPrice = driver.findElement(By.cssSelector
						("s[class=regular-price]")).getCssValue("color");
		rgb = parseColor(colorRegularPrice);


		//Is Campaign price bold on the Own page?
		String styleCampaignPrice = driver.findElement(By.cssSelector
						("strong[class=campaign-price]")).getCssValue("font-weight");


		//Is Campaign price red on the Own page?
		String colorCampaignPrice = driver.findElement(By.cssSelector
						("strong[class=campaign-price]")).getCssValue("color");
		rgb = parseColor(colorCampaignPrice);


		//Is Campaign price bigger than Regular price on the Own page?
		Dimension sizeRegularPrice = driver.findElement(By.cssSelector
						("s[class=regular-price]")).getSize();
		Dimension sizeCampaignPrice = driver.findElement(By.cssSelector
						("strong[class=campaign-price]")).getSize();


	}

	private List<Integer> parseColor(String color) {
		List<Integer> rgb = new ArrayList<>();
		String[] numbers = color.replace("rgba(", "").replace(
						"rgb(", "").replace(")", "").split(",");
		rgb.add(Integer.parseInt(numbers[0].trim()));
		rgb.add(Integer.parseInt(numbers[1].trim()));
		rgb.add(Integer.parseInt(numbers[2].trim()));

		return rgb;
	}


	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}
