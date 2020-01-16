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
	public void selectBoxSizeXS()
	{
		WebElement xs=driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.FrameLayout[1]"));
		xs.click();
	}
	public void selectBoxSizeS()
	{
		WebElement xs=driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.FrameLayout[2]"));
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
	public void actionButton()
	{
		WebElement actionBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/actionButton"));
		actionBtn.click();
	}
	public void clickOnViewBookingDetails()
	{
		WebElement x=driver.findElement(By.id("sg.com.blu.android.uat:id/viewBookingDetailsButton"));
		x.click();
	}
	
	public WebElement recipientDetailsNotRegisteredMobileMsg()
	{
		WebElement msg=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/mobileNumberRecipientContainer']//android.widget.TextView"));
		return msg;
	}
	public WebElement recipientNametextField()
	{
		WebElement msg=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		return msg;
	}
	public WebElement recipientNametextFieldError()
	{
		WebElement error=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='recipientNameInputField']//android.widget.TextView[1]"));
		return error;
	}
	public WebElement textFieldError()
	{
		WebElement error=driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		return error;
	}
	public WebElement mobileNumberTextField()
	{
		WebElement textField=driver.findElement(By.id("sg.com.blu.android.uat:id/input_mobile_number_et"));
		return textField;
	}
	public void clickOnChangeMobileNumberLink()
	{
		WebElement x=driver.findElement(By.id("sg.com.blu.android.uat:id/changeMobileNumberTextView"));
		x.click();
	}
	public WebElement remarksTextFieldError()
	{
		WebElement error=driver.findElement(By.id("sg.com.blu.android.uat:id/remarksErrorTextView"));
		return error;
	}
	public List<WebElement> redeemedRewardsList()
	{
		List<WebElement> error=driver.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.com.blu.android.uat:id/recyclerView']//android.widget.RelativeLayout"));
		return error;
	}
	public WebElement editIconOfOrigin()
	{
		WebElement icon=driver.findElement(By.xpath("//android.widget.RelativeLayout[@resource-id='sg.com.blu.android.uat:id/bookingLocationsView']//android.widget.LinearLayout[1]//android.widget.ImageButton"));
		return icon;
	}
	public WebElement editIconOfDestination()
	{
		WebElement icon=driver.findElement(By.xpath("//android.widget.RelativeLayout[@resource-id='sg.com.blu.android.uat:id/bookingLocationsView']//android.widget.LinearLayout[2]//android.widget.ImageButton"));
		return icon;
	}
	public WebElement editIconOfBoxSize()
	{
		WebElement icon=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/boxSizeBookingDisplayField']//android.widget.ImageButton"));
		return icon;
	}
	public WebElement editIconOfProductCatagory()
	{
		WebElement icon=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/productCategoryBookingDisplayField']//android.widget.ImageButton"));
		return icon;
	}
	public WebElement editIconOfRemarks()
	{
		WebElement icon=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/remarksBookingDisplayField']//android.widget.ImageButton"));
		return icon;
	}
	public WebElement editIconOfReward()
	{
		WebElement icon=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/rewardBookingDisplayField']//android.widget.ImageButton"));
		return icon;
	}
	public WebElement firstTextOfScreen()
	{
		WebElement text=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/booking_header']//android.widget.TextView"));
		return text;
	}
	public List<WebElement> displayedDots()
	{
		List<WebElement> dots=driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/bookingIndicator']//android.widget.ImageView"));
		return dots;
	}
	public List<WebElement> reviewBookingScreenContainer()
	{
		List<WebElement> dots=driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/bookingHeader']//android.widget.LinearLayout"));
		return dots;
	}
	public WebElement destinationBluHomeTitleTextView()
	{
		WebElement text=driver.findElement(By.id("sg.com.blu.android.uat:id/titleTextView"));
		return text;
	}
}
