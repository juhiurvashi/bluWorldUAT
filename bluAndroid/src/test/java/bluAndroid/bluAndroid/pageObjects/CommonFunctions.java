package bluAndroid.bluAndroid.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class CommonFunctions {
private static AppiumDriver<WebElement> driver;
public CommonFunctions()
{
	
}
public CommonFunctions(AppiumDriver<WebElement> driver)
{
	this.driver=driver;
}
public static void swipe(WebElement startPoint,WebElement endPoint)
{
	TouchAction action = new TouchAction(driver);
	PointOption p1 = new PointOption();
	org.openqa.selenium.Point centerOfFirstElement = ((MobileElement) startPoint).getCenter();
	org.openqa.selenium.Point centerOfLastElement = ((MobileElement) endPoint).getCenter();
	//System.out.println("center of first element: "+centerOfFirstElement+ "  center of last element: "+centerOfLastElement);
	new TouchAction(driver).longPress(p1.point(centerOfFirstElement.x, centerOfFirstElement.y+350))
			.moveTo(p1.point(centerOfLastElement.x, centerOfLastElement.y)).release().perform();
}

	/*
	 * public static void swipeInListTillExpectedTextAndTap1(List<WebElement> list,
	 * String expectedText, int time) { int i = 1; while
	 * (!driver.getPageSource().contains(expectedText)) { swipeList(list); i++; if
	 * (i == time) break; } driver.findElement(MobileBy.
	 * AndroidUIAutomator("new UiSelector().textContains(\"" +expectedText +
	 * "\")")).click();; }
	 */
public static void swipeList(List<WebElement> list) {
	int items = list.size();
	TouchAction action = new TouchAction(driver);
	PointOption p1 = new PointOption();
	org.openqa.selenium.Point centerOfFirstElement = ((MobileElement) list.get(0)).getCenter();
	org.openqa.selenium.Point centerOfLastElement = ((MobileElement) list.get(items - 1)).getCenter();
	//System.out.println("center of first element: "+centerOfFirstElement+ "  center of last element: "+centerOfLastElement);
	new TouchAction(driver).longPress(p1.point(centerOfFirstElement.x, centerOfFirstElement.y+300))
			.moveTo(p1.point(centerOfLastElement.x, centerOfLastElement.y+80)).release().perform();
	
}
public  void screenSwipe() {
	Dimension dimensions = driver.manage().window().getSize();
    System.out.println("Size of Window= " +dimensions);
    int scrollStart = (int) (dimensions.getHeight() * 0.5);
    System.out.println("Size of scrollStart= " +scrollStart);
    int scrollEnd = (int) (dimensions.getHeight() * 0.2);
    System.out.println("Size of cscrollEnd= " + scrollEnd);             
 //   driver.swipe(0,scrollStart,0,scrollEnd,1000);           
    System.out.println("Screen Swiped " );
	//int items = list.size();
	TouchAction action = new TouchAction(driver);
	PointOption p1 = new PointOption();
	//org.openqa.selenium.Point centerOfFirstElement = ((MobileElement) list.get(0)).getCenter();
	//org.openqa.selenium.Point centerOfLastElement = ((MobileElement) list.get(items - 1)).getCenter();
	//System.out.println("center of first element: "+centerOfFirstElement+ "  center of last element: "+centerOfLastElement);
	new TouchAction(driver).longPress(p1.point(scrollStart, scrollEnd))
			.moveTo(p1.point(scrollStart+100, scrollEnd+80)).release().perform();
	
	
}
public static void swipeUp(List<WebElement> list) {
	int items = list.size();
	//System.out.println("List Size is: "+ items);
	TouchAction action = new TouchAction(driver);
	PointOption p1 = new PointOption();
	org.openqa.selenium.Point centerOfFirstElement = ((MobileElement) list.get(0)).getCenter();
	org.openqa.selenium.Point centerOfLastElement = ((MobileElement) list.get(items - 1)).getCenter();
	//System.out.println("center of first element: "+centerOfFirstElement+ "  center of last element: "+centerOfLastElement);
	new TouchAction(driver).longPress(p1.point(centerOfFirstElement.x, centerOfFirstElement.y+800))
			.moveTo(p1.point(centerOfLastElement.x, centerOfLastElement.y-1000)).release().perform();
	
}
public static void swipeInListFromFirstToLast(List<WebElement> list) {
	int items = list.size();
	//System.out.println("List Size is: "+ items);
	TouchAction action = new TouchAction(driver);
	PointOption p1 = new PointOption();
	org.openqa.selenium.Point centerOfFirstElement = ((MobileElement) list.get(0)).getCenter();
	org.openqa.selenium.Point centerOfLastElement = ((MobileElement) list.get(items - 1)).getCenter();
	//System.out.println("center of first element: "+centerOfFirstElement+ "  center of last element: "+centerOfLastElement);
	new TouchAction(driver).longPress(p1.point(centerOfLastElement.x, centerOfLastElement.y+100))
			.moveTo(p1.point(centerOfFirstElement.x, centerOfFirstElement.y+80)).release().perform();
	
}
public static void swipeInListTillExpectedTextAndTap1(List<WebElement> list, String expectedText, int time) {
	int i = 1;
	while (!driver.getPageSource().contains(expectedText)) {
		swipeInListFromFirstToLast(list);
		i++;
		if (i == time)
			break;
	}
	driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" +expectedText + "\")")).click();;
}
public static void swipeInListTillExpectedTextAndTap(List<WebElement> list, String expectedText, int time) {
	int i = 1;
	while (!driver.getPageSource().contains(expectedText)) {
		swipeUp(list);
		i++;
		if (i == time)
			break;
	}
	driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" +expectedText + "\")")).click();;
}

public void clickOnBackButton()
{
	driver.findElement(By.id("sg.com.blu.android.uat:id/back_btn")).click();
}
}
