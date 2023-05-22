package com.Hello;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetApps {

	static WebDriver driver;

	public static void main(String[] args) {

		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		WebElement clickbtn = driver.findElement(By.xpath("//a[@aria-label='Google apps']"));
		clickbtn.click();

		WebElement iframe = driver.findElement(By.name("app"));
		driver.switchTo().frame(iframe);

		List<WebElement> maps = driver.findElements(By.xpath("//ul[@class='ngVsM u4RcUd']"));

		for (WebElement ele : maps) {
			String text = ele.getText();
			System.out.println(text);
		}
	}
}
