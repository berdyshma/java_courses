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
import java.util.Comparator;
import java.util.List;

public class TimeZones {

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
	public void test_1A_countriesAreSorted() {
		driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
		List<WebElement> nameCells = driver.findElements
						(By.cssSelector("form[name=countries_form] tr.row td:nth-child(5) a"));

		Assert.assertTrue(isColumnSorted(nameCells));

	}

	@Test
	public void test_1B_zonesAreSorted() {

		List<String> links = new ArrayList<>();
		driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
		List<WebElement> countryNameCells = driver.findElements(By.xpath
						("//form//tr[@class='row']/td[6][not(.='0')]/../td[5]/a"));
		for (WebElement countryNameCell : countryNameCells) {
			links.add(countryNameCell.getAttribute("href"));
		}

		for (String link : links) {
			List<String> zones = new ArrayList<>();
			driver.get(link);
			List<WebElement> zoneNameCells = driver.findElements(By.cssSelector
							("table#table-zones td:nth-child(3)"));
			zoneNameCells.remove(zoneNameCells.size() - 1);
			Assert.assertTrue(isColumnSorted(zoneNameCells));
		}

	}

	@Test
	public void test_2_geoZonesAreSorted() {
		List<String> links = new ArrayList<>();
		driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
		List<WebElement> countryNameCells = driver.findElements(By.cssSelector
						("form[name=geo_zones_form] tr.row td:nth-child(3) a"));
		for (WebElement countryNameCell : countryNameCells) {
			links.add(countryNameCell.getAttribute("href"));
		}

		for (String link : links) {
			List<String> zones = new ArrayList<>();
			driver.get(link);
			List<WebElement> zoneNameCells = driver.findElements(By.cssSelector
							("table#table-zones td:nth-child(3) option[selected]"));
			Assert.assertTrue(isColumnSorted(zoneNameCells));

		}

	}


	private boolean isColumnSorted(List<WebElement> column) {
		List<String> values = new ArrayList<>();
		for (WebElement cell : column) {
			values.add(cell.getText());
		}

		List<String> valuesSorted = new ArrayList<>(values);
		valuesSorted.sort(Comparator.naturalOrder());

		return values.equals(valuesSorted);
	}

	@After
	public void stop() {
		driver.quit();
		driver = null;
	}
}

