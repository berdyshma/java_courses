package by.berdysh.java_course;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
		List<String> failedItems = new ArrayList<>();

		WebElement duck = driver.findElement(By.cssSelector("div#box-campaigns li.product"));

		//Is Regular price crossed on the Main page?
		String styleRegularPriceOnMainPage = duck.findElement(By.cssSelector
						("s[class=regular-price]")).getCssValue("text-decoration-line");
		if (!styleRegularPriceOnMainPage.equals("line-through")) {
			failedItems.add("Regular price is not crossed out on the Main page");
		}

		//Is Regular price grey on the Main page?
		String colorRegularPriceOnMainPage = duck.findElement(By.cssSelector
						("s[class=regular-price]")).getCssValue("color");
		List<Integer> rgb = parseColor(colorRegularPriceOnMainPage);
		if ((rgb.get(0) != rgb.get(1)) || (rgb.get(0) != rgb.get(2))) {
			failedItems.add("Regular price is not grey on the Main page");
		}

		//Is Campaign price bold on the Main page?
		String styleCampaignPriceOnMainPage = duck.findElement(By.cssSelector
						("strong[class=campaign-price]")).getCssValue("font-weight");
		if (!styleCampaignPriceOnMainPage.equals("bold") && !styleCampaignPriceOnMainPage.equals
						("bolder") && !(Integer.parseInt(styleCampaignPriceOnMainPage) >= 700)) {
			failedItems.add("Campaign price is not bold on the Main page");
		}

		//Is Campaign price red on the Main page?
		String colorCampaignPriceOnMainPage = duck.findElement(By.cssSelector
						("strong[class=campaign-price]")).getCssValue("color");
		rgb = parseColor(colorCampaignPriceOnMainPage);
		if ((rgb.get(1) != 0) || (rgb.get(2) != 0)) {
			failedItems.add("Campaign price is not red on the Main page");
		}

		//Is Campaign price bigger than Regular price on the Main page?
		String sizeRegularPriceString = duck.findElement(By.cssSelector
						("s[class=regular-price]")).getCssValue("fontSize");
		String sizeCampaignPriceString = duck.findElement(By.cssSelector
						("strong[class=campaign-price]")).getCssValue("fontSize");
		String a = sizeRegularPriceString.replace("px", "");
		String b = sizeCampaignPriceString.replace("px", "");
		float regularPrice = Float.parseFloat(a);
		float campaignPrice = Float.parseFloat(b);
				if (regularPrice >= campaignPrice) {
			failedItems.add("Campaign price is not bigger than Regular price on the Main page");
		}

		//Save some info from the Main page for future checking
		String nameOnMainPage = duck.findElement(By.cssSelector("div[class=name]")).getText();
		String regularPriceOnMainPage = duck.findElement(By.cssSelector
						("s[class=regular-price]")).getText();
		String campaignPriceOnMainPage = duck.findElement(By.cssSelector
						("strong[class=campaign-price]")).getText();

		//Go to the product own page
		duck.click();

		//Are product titles on Main and Own pages not equal?
		String nameOnOwnPage = driver.findElement(By.cssSelector(
						"h1[class=title]")).getText();
		if (!nameOnMainPage.equals(nameOnOwnPage)) {
			failedItems.add("Product tittles on Main and Own pages are not equal");
		}

		//Are Regular prices on Main and Own pages not equal?
		String regularPriceOnOwnPage = driver.findElement(By.cssSelector
						("s[class=regular-price]")).getText();
		if (!regularPriceOnMainPage.equals(regularPriceOnOwnPage)) {
			failedItems.add("Regular prices on Main and Own pages are not equal");
		}

		//Are Campaign prices on Main and Own pages not equal?
		String campaignPriceOnOwnPage = driver.findElement(By.cssSelector
						("strong[class=campaign-price]")).getText();
		if (!campaignPriceOnMainPage.equals(campaignPriceOnOwnPage)) {
			failedItems.add("Campaign prices on Main and Own pages are not equal");
		}

		//Is Regular price crossed on the Own page?
		String styleRegularPrice = driver.findElement(By.cssSelector
						("s[class=regular-price]")).getCssValue("text-decoration-line");
		if (!styleRegularPrice.equals("line-through")) {
			failedItems.add("Regular price is not crossed out on the Main page");
		}

		//Is Regular price grey on the Own page?
		String colorRegularPrice = driver.findElement(By.cssSelector
						("s[class=regular-price]")).getCssValue("color");
		rgb = parseColor(colorRegularPrice);
		if ((rgb.get(0) != rgb.get(1)) || (rgb.get(0) != rgb.get(2))) {
			failedItems.add("Regular price is not grey on the Own page");
		}

		//Is Campaign price bold on the Own page?
		String styleCampaignPrice = driver.findElement(By.cssSelector
						("strong[class=campaign-price]")).getCssValue("font-weight");
		if (!styleCampaignPriceOnMainPage.equals("bold") && !styleCampaignPriceOnMainPage.equals
						("bolder") && !(Integer.parseInt(styleCampaignPriceOnMainPage) >= 700)) {
			failedItems.add("Campaign price is not bold on the Own page");
		}

		//Is Campaign price red on the Own page?
		String colorCampaignPrice = driver.findElement(By.cssSelector
						("strong[class=campaign-price]")).getCssValue("color");
		rgb = parseColor(colorCampaignPrice);
		if ((rgb.get(1) != 0) || (rgb.get(2) != 0)) {
			failedItems.add("Campaign price is not red on the Own page");
		}

		//Is Campaign price bigger than Regular price on the Own page?
		String sizeRegularPrice = driver.findElement(By.cssSelector
						("s[class=regular-price]")).getCssValue("fontSize");
		String sizeCampaignPrice = driver.findElement(By.cssSelector
						("strong[class=campaign-price]")).getCssValue("fontSize");

		String c = sizeRegularPriceString.replace("px", "");
		String d = sizeCampaignPriceString.replace("px", "");
		float regularPrice1 = Float.parseFloat(c);
		float campaignPrice2 = Float.parseFloat(d);
		if (regularPrice1 >= campaignPrice2) {
			failedItems.add("Campaign price is not bigger than Regular price on the Main page");
		}

		Assert.assertTrue("Found issue: "
						+ failedItems.toString(), failedItems.size() == 0);

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