package bluAndroid.bluAndroid.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.AppiumDriver;

public class BookingScreen {
	private AppiumDriver<WebElement> driver;

	public BookingScreen() {

	}

	public BookingScreen(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}
	
	public WebElement textBox()
	{
		WebElement textBox=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		return textBox;
	}

	public WebElement clickOnMobileNo()
	{
		WebElement textBox=driver.findElement(By.xpath("//android.widget.HorizontalScrollView//androidx.appcompat.app.ActionBar.Tab"));
		return textBox;
	}
	public WebElement bluIdDisplayed()
	{
		WebElement bluId=driver.findElement(By.id("sg.com.blu.android.uat:id/recipientBluIdTextView"));
		return bluId;
	}
	public WebElement firstNameDisplayed()
	{
		WebElement fn=driver.findElement(By.id("sg.com.blu.android.uat:id/recipientNameTextView"));
		return fn;
	}
	public void clickOnNextBtn()
	{
		WebElement next=driver.findElement(By.id("sg.com.blu.android.uat:id/nextButton"));
		next.click();
	}
	public void clickOnBluPortDeliveryMethod()
	{
		WebElement bluPort=driver.findElement(By.id("sg.com.blu.android.uat:id/bluPortButton"));
		bluPort.click();
	}
	public void clickOnBluHomeDeliveryMethod()
	{
		WebElement bluHome=driver.findElement(By.id("sg.com.blu.android.uat:id/bluHomeButton"));
		bluHome.click();
	}
	public void selectProductCatagory()
	{
		WebElement productCatagory=driver.findElement(By.id("sg.com.blu.android.uat:id/productCategorySpinner"));
		productCatagory.click();
		WebElement dropdown=driver.findElement(By.xpath("//android.widget.ListView//android.widget.CheckedTextView[2]"));
		dropdown.click();
		/*
		 * Select sel = new Select(dropdown); sel.selectByValue("Baby products");
		 */
		//driver.findElement(By.xpath("//android.widget.FrameLayout//android.widget.FrameLayout//android.widget.ListView//android.widget.CheckedTextView[contains(text(),'Baby products')])")).click();
		/*
		 * List<WebElement> productList= driver.findElements(By.xpath(
		 * "android.widget.FrameLayout//android.widget.FrameLayout//android.widget.ListView//android.widget.CheckedTextView"
		 * )); for(int i=0;i<productList.size();i++) { if(i==2)
		 * productList.get(2).click(); }
		 */
	}

	public WebElement enterRemarks()
	{
		WebElement remarks=driver.findElement(By.id("sg.com.blu.android.uat:id/remarksEditText"));
		return remarks;
	}
	public void selectBoxSize()
	{
		WebElement xs=driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.FrameLayout[1]"));
		xs.click();
	}
	public void clickOnContinue()
	{
		WebElement xs=driver.findElement(By.id("sg.com.blu.android.uat:id/continueButton"));
		xs.click();
	}
	public void clickOnMakePaymentBtn()
	{
		WebElement xs=driver.findElement(By.id("sg.com.blu.android.uat:id/submitButton"));
		xs.click();
	}
	public void clickOnCloseBtn()
	{
		WebElement x=driver.findElement(By.id("sg.com.blu.android.uat:id/close_btn"));
		x.click();
	}
	public void proceedToPaymentBtn()
	{
		WebElement actionBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/actionButton"));
		actionBtn.click();
	}
	public void clickOnViewBookingDetails()
	{
		WebElement x=driver.findElement(By.id("sg.com.blu.android.uat:id/viewBookingDetailsButton"));
		x.click();
	}
	
	
	
}
