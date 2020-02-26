package bluAndroid.bluAndroid.pageObjects;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class MyDetailsScreen {
	private static AppiumDriver<WebElement> driver;
	public MyDetailsScreen()
	{
		
	}
	public MyDetailsScreen(AppiumDriver<WebElement> driver2) throws IOException {
		this.driver = driver2;
	}
	public void clickOnMenu()
	{
		WebElement menu=driver.findElement(By.id("sg.com.blu.android.uat:id/menuButton"));
		menu.click();
	}
	public void clickOnMyDetails()
	{
		WebElement menu=driver.findElement(By.id("sg.com.blu.android.uat:id/menuButton"));
		menu.click();
		WebElement myDetails=driver.findElement(By.id("sg.com.blu.android.uat:id/myDetailsTextView"));
		myDetails.click();
	}
	
	public void clickOneditIconFirstName()
	{
		WebElement firstNameEditIcon=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/first_name_profileDisplayField']//android.widget.ImageButton[@resource-id='sg.com.blu.android.uat:id/edit_btn']"));
		firstNameEditIcon.click();
	}
	public void clickOneditIconLastName()
	{
		WebElement lastNameEditIcon=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/last_name_profileDisplayField']//android.widget.ImageButton[@resource-id='sg.com.blu.android.uat:id/edit_btn']"));
		lastNameEditIcon.click();
	}
	public void clickOneditIconMobile()
	{
		WebElement MobileEditIcon=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/mobile_number_profileDisplayField']//android.widget.ImageButton[@resource-id='sg.com.blu.android.uat:id/edit_btn']"));
		MobileEditIcon.click();
	}
	public void clickOneditIconEmail()
	{
		WebElement emailEditIcon=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/email_profileDisplayField']//android.widget.ImageButton[@resource-id='sg.com.blu.android.uat:id/edit_btn']"));
		emailEditIcon.click();
	}
	public void clickOneditIconDOB()
	{
		WebElement dobEditIcon=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/dob_profileDisplayField']//android.widget.ImageButton[@resource-id='sg.com.blu.android.uat:id/edit_btn']"));
		dobEditIcon.click();
	}
	public void clickOneditIconGender()
	{
		WebElement genderEditIcon=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/gender_profileDisplayField']//android.widget.ImageButton[@resource-id='sg.com.blu.android.uat:id/edit_btn']"));
		genderEditIcon.click();
	}
	public WebElement selectDOBbtn() {
		WebElement dob = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/dob_inputField']"));
		return dob;
	}

	public static void swipeInListFromLastToFirst(List<WebElement> list) {
		int items = list.size();
		//System.out.println("List Size is: "+ items);
		TouchAction action = new TouchAction(driver);
		PointOption p1 = new PointOption();
		org.openqa.selenium.Point centerOfFirstElement = ((MobileElement) list.get(0)).getCenter();
		org.openqa.selenium.Point centerOfLastElement = ((MobileElement) list.get(items - 1)).getCenter();
		//System.out.println("center of first element: "+centerOfFirstElement+ "  center of last element: "+centerOfLastElement);
		new TouchAction(driver).longPress(p1.point(centerOfFirstElement.x, centerOfFirstElement.y+300))
				.moveTo(p1.point(centerOfLastElement.x, centerOfLastElement.y+80)).release().perform();
		
	}
	public static void swipeInListFromFirstToLast(List<WebElement> list) {
		int items = list.size();
		//System.out.println("List Size is: "+ items);
		TouchAction action = new TouchAction(driver);
		PointOption p1 = new PointOption();
		org.openqa.selenium.Point centerOfFirstElement = ((MobileElement) list.get(0)).getCenter();
		org.openqa.selenium.Point centerOfLastElement = ((MobileElement) list.get(items - 1)).getCenter();
		//System.out.println("center of first element: "+centerOfFirstElement+ "  center of last element: "+centerOfLastElement);
		new TouchAction(driver).longPress(p1.point(centerOfFirstElement.x, centerOfFirstElement.y-300))
				.moveTo(p1.point(centerOfLastElement.x, centerOfLastElement.y+80)).release().perform();
		
	}
	public static void swipeInListTillExpectedTextAndTap(List<WebElement> list, String expectedText, int time) {
		int i = 1;
		while (!driver.getPageSource().contains(expectedText)) {
			swipeInListFromLastToFirst(list);
			i++;
			if (i == time)
				break;
		}
		while (!driver.getPageSource().contains(expectedText)) {
			swipeInListFromFirstToLast(list);
			i++;
			if (i == time)
				break;
		}
		driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" +expectedText + "\")")).click();;
	}
	public void selectDOB() throws IOException
	{
		//selectDOBbtn().click();
		driver.findElement(By.id("android:id/date_picker_header_year")).click();
		List<WebElement> list=driver.findElements(By.className("android.widget.TextView"));
		String year=CommonUtil.getPropertyValue("myDetails", "year");
		swipeInListTillExpectedTextAndTap(list, year, 20);
  		int month=Calendar.getInstance().get(Calendar.MONTH)+1;
   		System.out.println(" Current Month is :"+ month);
  		String monthTarget=CommonUtil.getPropertyValue("myDetails", "month");
  		int resultMonth = Integer.parseInt(monthTarget);
  		System.out.println(resultMonth);
  		if(resultMonth>=month)
  		{
  			for(int i=0;i<resultMonth-month;i++)
  			driver.findElement(By.id("android:id/prev")).click();	
  		}
  		else if(resultMonth<=month)
  		{
  			for(int i=0;i<month-resultMonth;i++)
  			driver.findElement(By.id("android:id/next")).click();	
  		}
		//for(int i=0;i<6;i++)
		//driver.findElement(By.id("android:id/prev")).click();
		//driver.findElement(By.id("android:id/next")).click();
		//String dob=CommonUtil.getPropertyValue("signup", "dob");
  		String dob = CommonUtil.getPropertyValue("myDetails", "dob");
		System.out.println(dob);
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"" +dob + "\"]")).click();
		//driver.findElement(By.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View[contains(text(),'7')]")).click();
		driver.findElement(By.id("android:id/button1")).click();//button 2 for cancel	
	}

	public WebElement referAFriend() {
		WebElement refer = driver.findElement(
				By.id("sg.com.blu.android.uat:id/refer_a_friend_tv"));
		return refer;
	}
}
