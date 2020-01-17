package bluAndroid.bluAndroid.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class RequestBookingScreen {
	private AppiumDriver<WebElement> driver;

	public RequestBookingScreen() {

	}

	public RequestBookingScreen(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}
	public void clickOnRequestForParcel()
	{
		driver.findElement(By.id("sg.com.blu.android.uat:id/requestParcelButton")).click();
	}
	public WebElement viewRequestDetailsBtn()
	{
		WebElement btn=driver.findElement(By.id("sg.com.blu.android.uat:id/viewRequestDetailsButton"));
		return btn;
	}
	public WebElement statusTitle()
	{
		WebElement title=driver.findElement(By.id("sg.com.blu.android.uat:id/bookingTitleTextView"));
		return title;
	}
	public WebElement sentReminderName()
	{
		WebElement name=driver.findElement(By.id("sg.com.blu.android.uat:id/nameTextView"));
		return name;
	}
	public WebElement sentReminderNumber()
	{
		WebElement no=driver.findElement(By.id("sg.com.blu.android.uat:id/phoneNumberTextView"));
		return no;
	}
	public void clickOnCloseBtn()
	{
		driver.findElement(By.id("sg.com.blu.android.uat:id/closeButton")).click();
	}
	public WebElement sentReminderMsg1()
	{
		WebElement no=driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id='sg.com.blu.android.uat:id/coordinatorLayout']//android.widget.LinearLayout//android.widget.TextView[1]"));
		return no;
	}
	public WebElement sentReminderMsg2()
	{
		WebElement no=driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id='sg.com.blu.android.uat:id/coordinatorLayout']//android.widget.LinearLayout//android.widget.TextView[2]"));
		return no;
	}
	public void clickOnCancelRequest()
	{
		driver.findElement(By.id("requestActionTextView")).click();
	}
	public WebElement AddressIsDisplayed()
	{
		WebElement address=driver.findElement(By.id("sg.com.blu.android.uat:id/address1TextView"));
		return address;
	}
	public List<WebElement> requestStatus()
	{
		List<WebElement> status=driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/bookingRequestDetailsContainer']//android.widget.TextView"));
		return status;
	}
	public WebElement sttus()
	{
		WebElement address=driver.findElement(By.id("sg.com.blu.android.uat:id/bookingTitleTextView"));
		return address;
	}
	public void clickOnRejectRequest()
	{
		WebElement rqst=driver.findElement(By.id("sg.com.blu.android.uat:id/requestActionTextView"));
		rqst.click();
	}
	
}
