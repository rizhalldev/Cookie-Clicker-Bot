package dev.mahmed.app;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class App {
	
	static WebDriver driver;
	static WebElement cookie;
	static WebElement cookies;
	static Actions action;

	public static void main(String[] args) throws InterruptedException {
		
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		driver.get("https://orteil.dashnet.org/cookieclicker/");
		Thread.sleep(5000);
		action = new Actions(driver);
		
		////////////// Elements in page ////////////////
		cookie = driver.findElement(By.id("bigCookie"));
		cookies = driver.findElement(By.id("cookies"));
		
		/////////////////// Logic //////////////////////
		
		getCookies(20);
		WebElement item = driver.findElements(By.xpath("//*[contains(@class, 'product') and contains(@class, 'unlocked')]")).get(0);
		item.click();
//		Thread.sleep(100);
		WebElement upgrade = driver.findElements(By.xpath("//*[contains(@class, 'crate') and contains(@class, 'upgrade')]")).get(0);
		action.moveToElement(upgrade).perform();
//		Thread.sleep(100);
		WebElement upgradePrice = driver.findElement(By.xpath("//*[@id='tooltip']//*[contains(@class,'price')]"));
		System.out.println(upgradePrice.getText());
		
		////////////////////Close //////////////////////
//		driver.quit();
	}
	
	
	
	private static void getCookies(int amountToReach) {
		System.out.println(cookies.getText().split(" ")[0]);
		System.out.println(cookies.getText());
		while (Integer.parseInt(cookies.getText().split(" ")[0]) < amountToReach) {
			cookie.click();
		}
	}
	
	private static List<WebElement> getEnabledElements() {
		return driver.findElements(By.className("enabled"));
	}

}
