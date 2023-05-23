package com.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	public static WebDriver driver;

	RequestSpecification reqSpec;
	public static Response response;

	// Browser Launch

	public static void getDriver(String browserName) {

		switch (browserName) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Invalid Browser Name");
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// implicity waits
	public static void implicitlyWaits(int value) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(value));
	}

	// Launch url

	public static void launchUrl(String url) {
		driver.get(url);
	}

	// get Title

	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	// get current url

	public String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
		return currentUrl;
	}

	// navigate url
	public void navigateUrl(String url) {
		driver.navigate().to(url);
	}

	// backward
	public void backword() {
		driver.navigate().back();
	}

	// forward
	public void forward() {
		driver.navigate().forward();
	}

	// refresh
	public void refresh() {
		driver.navigate().refresh();
	}

	// close all window

	public static void closeCurrentWindow() {
		driver.close();
	}

	// quit all window

	public void closeAllWindow() {
		driver.quit();
	}

	// for find by locators

	public static WebElement findElementByLocators(String locator, String value) {
		WebElement element = null;
		switch (locator) {
		case "id":
			element = driver.findElement(By.id(value));
			break;

		case "name":
			element = driver.findElement(By.name(value));
			break;
		case "xpath":
			element = driver.findElement(By.xpath(value));
			break;
		default:
			break;
		}

		return element;
	}

	// for find by id

	public WebElement findById(String id) {
		WebElement element = driver.findElement(By.id(id));
		return element;
	}
	// for find by name

	public WebElement findByName(String name) {
		WebElement element = driver.findElement(By.name(name));

		return element;
	}

	// for find by class

	public WebElement findByClass(String className) {
		WebElement element = driver.findElement(By.className(className));
		return element;
	}

	// for find by xpath

	public WebElement findByXpath(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element;
	}

	// find by tagname

	public List<WebElement> findByTagName(String tagName) {
		List<WebElement> element = driver.findElements(By.tagName(tagName));
		return element;
	}

	// for sendkeys()

	public void typeText(WebElement element, String data) {
		element.sendKeys(data);
	}

	// btnClick
	public static void btnClick(WebElement element) {
		element.click();
	}

	// clear

	public void clear(WebElement element) {
		element.clear();

	}

	// mousehoverAction
	public void mouseHover(WebElement element) {
		Actions ac = new Actions(driver);
		ac.moveToElement(element).perform();

	}

	// Right Click
	public void rightClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.contextClick(element).perform();

	}

	// doubleClick
	public void doubleClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.doubleClick(element).perform();

	}

	// draganddrop
	public void dragandDrop(WebElement srce, WebElement trgt) {
		Actions ac = new Actions(driver);
		ac.dragAndDrop(srce, trgt).perform();
	}

	// for capital letters

	public void capitalLetter(WebElement element, String Data) {
		Actions ac = new Actions(driver);
		ac.keyDown(Keys.SHIFT).sendKeys(element, Data).keyUp(Keys.SHIFT).perform();
	}

	// Robot action

	// send keys enter

	public static void typeTextEnter(WebElement element, String text) {

		// typeText(element, text,Keys.ENTER);

		element.sendKeys(text, Keys.ENTER);
	}

	// robot class enter action

	public void robotClassEnter() throws AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	// robot class tab
	public void robotClassTab() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	// robot class down

	public void robotClassDown() throws AWTException {
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);

	}

	// Alert
	// accept

	public void alertAccept() {
		driver.switchTo().alert().accept();
	}

	// dismiss

	public void alertDismiss() {
		driver.switchTo().alert().dismiss();

	}

	// alert sendkeys

	public void alertSendKeys(String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	// alert gettext

	public String alertBoxText() {
		String text = driver.switchTo().alert().getText();
		return text;
	}

	// gettext

	public String gettingText(WebElement element) {
		String text = element.getText();
		return text;

	}

	// getAttribute

	public String getAttri(WebElement element) {
		String attribute = element.getAttribute("value");
		return attribute;
	}

	// Javascript
	// sending txtvalue

	public Object sendingTextJs(String data, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object executeScript = js.executeScript("arguments[0].setAttribute('value','" + data + "')", element);
		return executeScript;
	}

	// gettingtext value

	public String gettingTextJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object executeScript = js.executeScript("return arguments[0].getAttribute('value')", element);
		String text = (String) executeScript;

		return text;
	}

	// buttonclick

	public Object buttonClickJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object executeScript = js.executeScript("arguments[0].click()", element);
		return executeScript;
	}

	// scrollup

	public Object scrollUpJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object executeScript = js.executeScript("arguments[0].scrollIntoView(false)", element);
		return executeScript;

	}

	// scrolldown

	public Object scrollDownJs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object executeScript = js.executeScript("arguments[0].scrollIntoView(true)", element);
		return executeScript;
	}

	// takesscreenshot

	public File screenShot(String Filename) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File screenshotAs = ts.getScreenshotAs(OutputType.FILE);

		File file = new File("C:\\Users\\HP\\Pictures\\Saved Pictures\\" + Filename + ".jpg");

		FileUtils.copyFile(screenshotAs, file);

		return file;
	}

	// takes screenshot -->outputtype.bytes
	public byte[] screenShotByte() {

		TakesScreenshot ts = (TakesScreenshot) driver;

		byte[] b = ts.getScreenshotAs(OutputType.BYTES);

		return b;
	}

	// dropdowns
	// select
	// selectdropdown

	public void selectDropDown(WebElement element, String data, String value) {
		Select se = new Select(element);

		switch (data) {
		case "value":
			se.selectByValue(value);
			break;
		case "text":
			se.selectByVisibleText(value);
			break;
		case "index":
			se.selectByIndex(Integer.parseInt(value));
			break;
		default:
			break;

		}
	}

	public void byVisibleText(WebElement element, String text) {

		Select se = new Select(element);

		se.selectByVisibleText(text);
	}

	public void byname(WebElement element, String value) {
		Select se = new Select(element);

		se.selectByValue(value);
	}

	public void byindex(WebElement element, int index) {
		Select se = new Select(element);
		se.selectByIndex(index);

	}

	public String firstSelectedOptionDropDown(WebElement element) {
		Select se = new Select(element);
		WebElement firstSelectedOption = se.getFirstSelectedOption();
		String attribute = firstSelectedOption.getAttribute("value");
		System.out.println(attribute);
		return attribute;

	}

	public void deselectall(WebElement element) {
		Select se = new Select(element);
		se.deselectAll();
	}

	public String getOptionsDropDown(WebElement element) {

		Select se = new Select(element);
		String text = null;
		List<WebElement> options = se.getOptions();

		for (int i = 0; i < options.size(); i++) {
			WebElement element1 = options.get(i);
			text = element1.getText();
			se.selectByVisibleText(text);
			System.out.println(text);
		}
		return text;

	}

	public String allSelectedOoptionsDropDown(WebElement element) {
		String text = null;
		Select se = new Select(element);
		List<WebElement> allSelectedOptions = se.getAllSelectedOptions();

		for (WebElement ele : allSelectedOptions) {
			text = ele.getText();
			System.out.println(text);
		}

		return text;

	}

	public boolean isMultiple(WebElement element) {
		Select se = new Select(element);
		boolean multiple = se.isMultiple();
		return multiple;

	}

	public void deselectbyindex(WebElement element, int index) {
		Select se = new Select(element);
		se.deselectByIndex(index);
	}

	public void deselectbyvalue(WebElement element, String value) {
		Select se = new Select(element);
		se.deselectByValue(value);

	}

	public void deselectbytext(WebElement element, String text) {
		Select se = new Select(element);
		se.deselectByVisibleText(text);

	}

	// Windowshandling

	// get parent window id

	public String prntWinId() {

		String prntWinId = driver.getWindowHandle();
		System.out.println(prntWinId);
		return prntWinId;
	}

	// get all windows id

	public Set<String> allWinId() {

		Set<String> allwinid = driver.getWindowHandles();
		System.out.println(allwinid);
		return allwinid;

	}

	// switch to current window

	public void switchToCurrentWindow() {

		String prntWinId = driver.getWindowHandle();
		System.out.println(prntWinId);

		Set<String> allwinid = driver.getWindowHandles();
		System.out.println(allwinid);

		for (String win : allwinid) {

			if (!prntWinId.equals(win)) {
				driver.switchTo().window(win);
			}

		}
	}

	// stch2CurrWinandClsPrntWin
	public void stch2CurrWinandClsPrntWin() {
		String prntWinId = driver.getWindowHandle();
		System.out.println(prntWinId);

		Set<String> allwinid = driver.getWindowHandles();
		System.out.println(allwinid);

		for (String win : allwinid) {

			if (prntWinId.equals(win)) {
				driver.close();

			} else if (!prntWinId.equals(win)) {
				driver.switchTo().window(win);
			} else {
				System.out.println("Invalid logic");

			}
		}
	}

	// switchParticularWindowHandles
	public void switchParticularWindowHandles(int index) {

		Set<String> allWindows = driver.getWindowHandles();

		String window = (String) allWindows.toArray()[index];
		driver.switchTo().window(window);
	}

	// sizeOfFrames
	public int sizeOfFrames(String tagname) {
		List<WebElement> f = driver.findElements(By.tagName(tagname));
		int size = f.size();
		System.out.println(size);
		return size;
	}

	// switchToFrameId
	public void switchToFrameId(String Id) {
		driver.switchTo().frame(Id);

	}

	// switchToFrameIndex
	public void switchToFrameIndex(int index) {
		driver.switchTo().frame(index);

	}

	// switchToFrameWebElement
	public void switchToFrameWebElement(WebElement element) {

		driver.switchTo().frame(element);

	}

	// switchToParentFrame
	public void switchToParentFrame() {

		driver.switchTo().parentFrame();
	}

	// 5switchToDefaultFrame
	public void switchToDefaultFrame() {

		driver.switchTo().defaultContent();
	}

	// getDataFromExcel
	public static String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws IOException {
		File file = new File("C:\\Users\\HP\\eclipse-workspace\\BaseclassMethods\\exceldata\\Adactinlogindetails.xlsx");
		FileInputStream stream = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		cell.setCellType(CellType.STRING);
		String stringCellValue = cell.getStringCellValue();
		return stringCellValue;
	}

	// get project path

	public static String getProjectPath() {

		String path = System.getProperty("user.dir");
		return path;

	}

	// get property file value

	public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {

		Properties properties = new Properties();

		properties.load(new FileInputStream(getProjectPath() + "\\Config\\config.properties"));

		String property = properties.getProperty(key);

		return property;
	}

	// API-- addheader

	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);
	}

	// API-- add path parameter
	public void addPathParam(String key, String value) {
		reqSpec = reqSpec.pathParam(key, value);
	}

	// API-- add query parameter
	public void addQueryParam(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);
	}

	// API-- addbody
	public void addBody(String body) {
		reqSpec = reqSpec.body(body);
	}

	// API--addbody-Object
	public void addBody(Object body) {
		reqSpec = reqSpec.body(body);
	}

	// API-- addreqtype
	public Response addReqType(String type, String endpoint) {
		switch (type) {
		case "GET":
			response = reqSpec.log().all().get(endpoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endpoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endpoint);
			break;
		case "PATCH":
			response = reqSpec.log().all().patch(endpoint);
			break;
		default:
			break;
		}
		return response;
	}

	// API-- get status code
	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	// API-- get asString
	public String getResBodyAsString(Response response) {
		String asString = response.asString();
		return asString;
	}

	// API-- get asprettystring
	public String getResBodyAsPrettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;
	}

	// API--for basic auth

	public void addBasicAuth(String username, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(username, password);
	}

	// API--for headers

	public void addHeaders(Headers headers) {
		reqSpec = RestAssured.given().headers(headers);
	}

}
