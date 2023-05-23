package com.google;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class gogleOption {

	public static void main(String[] args) throws IOException {
		String projectPath = System.getProperty("user.dir");
		System.out.println("projectPath->"+projectPath);
		File fileLoc = new File(projectPath+"\\ExcelFiles\\OutputExcel.xlsx");
		System.out.println("fileLoc->"+fileLoc);
		FileInputStream stream = new FileInputStream(fileLoc);
		XSSFWorkbook book = new XSSFWorkbook(stream);
		XSSFSheet sheet = book.createSheet("AppName8");
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebElement clickbtn = driver.findElement(By.xpath("//a[@aria-label='Google apps']"));
		clickbtn.click();
		WebElement iframe = driver.findElement(By.name("app"));
		driver.switchTo().frame(iframe);
		List<WebElement> maps = driver.findElements(By.xpath("//ul[@class='ngVsM u4RcUd']//li"));
		System.out.println("maps.size()->"+maps.size());
		System.out.println("");
		for (int i = 1; i <= maps.size(); i++) {
			WebElement appEle = maps.get(i-1);
			String appName = appEle.getText();
			System.out.println(i+") "+appName);
			XSSFRow createRow = sheet.createRow(i-1);
			XSSFCell createCell = createRow.createCell(0);
			createCell.setCellValue(appName);
		}
		FileOutputStream fileOutputStream = new FileOutputStream(fileLoc);
		book.write(fileOutputStream);
		System.out.println("done");
		driver.close();
	}
}
