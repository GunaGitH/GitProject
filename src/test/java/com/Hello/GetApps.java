package com.Hello;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetApps {

	static WebDriver driver;
	static List<String> appName = new ArrayList<String>();

	public void getAppList() {
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

			// System.out.println(text);
			appName.add(text);

		}
		System.out.println(appName);
	}

	public static void main(String[] args) throws IOException {
		GetApps ga = new GetApps();
		ga.getAppList();
		File fileLoc = new File("D:\\GitRepo\\GitProject\\ExcelFiles\\OutputExcel.xlsx");
		FileInputStream stream = new FileInputStream(fileLoc);
		XSSFWorkbook book = new XSSFWorkbook(stream);
		XSSFSheet sheet = book.createSheet("AppName1");
		int appListSize = appName.size();

		for (int i = 0; i < appListSize; i++) {
			XSSFRow createRow = sheet.createRow(i);
			XSSFCell createCell = createRow.createCell(0);
			// createCell.setCellType(CellType.STRING);
			createCell.setCellValue(appName.get(i));
		}
		FileOutputStream fileOutputStream = new FileOutputStream(fileLoc);
		book.write(fileOutputStream);
		System.out.println("done");
	}
}
