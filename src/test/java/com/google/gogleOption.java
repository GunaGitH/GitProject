package com.google;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class gogleOption {

	public static void main(String[] args) {
		//*[@class='tX9u1b']
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver(); 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.google.com/");
		
		WebElement clickButton = driver.findElement(By.xpath("//a[@role='button']"));
		clickButton.click();
		WebElement iframe = driver.findElement(By.id("app"));
		driver.switchTo().frame(iframe);
		
		List<WebElement> account = driver.findElements(By.xpath("//span[text()='Account']"));
		for (WebElement ele:account ) {
			String text = ele.getText();
			System.out.println(text);
		}
	
	}
}
