package bluAndroid.bluAndroid.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class ParcelsDetailsScreen {
	private static AppiumDriver<WebElement> driver;

	public ParcelsDetailsScreen() {

	}

	public ParcelsDetailsScreen(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}

	public List<WebElement> viewAlertContainer() {
		List<WebElement> alert = driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/alertContainer']//android.widget.TextView"));
		return alert;
	}

	public WebElement clickOnCancel() {
		WebElement x = driver.findElement(By.id("sg.com.blu.android.uat:id/cancelActionTextView"));
		// x.click();
		return x;
	}

	public void clickOnHoldBluCode() {
		WebElement x = driver.findElement(By.id("sg.com.blu.android.uat:id/holdBluCodeActionTextView"));
		x.click();
	}

	public void clickOnViewHistory() {
		WebElement x = driver.findElement(By.id("sg.com.blu.android.uat:id/holdBluCodeActionTextView"));
		x.click();
	}

	public void clickOnExtend() {
		WebElement x = driver.findElement(By.id("sg.com.blu.android.uat:id/alertExtendTextViewButton"));
		x.click();
	}

	public WebElement viewDropCodeBtn() {
		WebElement x = driver.findElement(By.id("sg.com.blu.android.uat:id/alertViewBluCodeButton"));
		return x;
	}

	public List<WebElement> parcelDetailsViewContainer() {
		List<WebElement> list = driver.findElements(By.xpath(
				"//android.widget.ScrollView//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/parcelDetailsContainer']//android.widget.LinearLayout[3]//android.widget.LinearLayout//android.widget.TextView[1]"));
		return list;
	}

	public WebElement scanCode() {
		WebElement scanCode = driver.findElement(By.id("sg.com.blu.android.uat:id/qrCodeImageView"));
		return scanCode;
	}

	public WebElement scanCodeTextView() {
		WebElement text = driver.findElement(By.id("sg.com.blu.android.uat:id/codeTextView"));
		return text;
	}

	public WebElement headerTextView() {
		WebElement text = driver.findElement(By.id("sg.com.blu.android.uat:id/headerTextView"));
		return text;
	}

	public WebElement Title() {
		WebElement title = driver.findElement(By.id("sg.com.blu.android.uat:id/title_tv"));
		return title;
	}

	public void clickOnBackBtn() {
		WebElement backBtn = driver.findElement(By.id("sg.com.blu.android.uat:id/back_btn"));
		backBtn.click();
	}

	public void clickOnCancellationBtn() {
		WebElement x = driver.findElement(By.id("sg.com.blu.android.uat:id/cancelConfirmationButton"));
		x.click();
	}

	public WebElement cancelContainerTitle() {
		WebElement title = driver.findElement(By.xpath(
				"//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/cancelContainer']//android.widget.TextView[1]"));
		return title;
	}

	public WebElement cancelContainerMsg() {
		WebElement msg = driver.findElement(By.xpath(
				"//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/cancelContainer']//android.widget.TextView[2]"));
		return msg;
	}

	public void cancelReason() {
		WebElement reason = driver.findElement(By.id("sg.com.blu.android.uat:id/radioButtonWrongSize"));
		reason.click();
	}

	public void cancelBookingButton() {
		WebElement cancelBtn = driver.findElement(By.id("sg.com.blu.android.uat:id/cancelParcelButton"));
		cancelBtn.click();
	}

	public void changedMind() {
		WebElement btn = driver.findElement(By.id("sg.com.blu.android.uat:id/closeCancellationButton"));
		btn.click();
	}

	public void makeNewBooking() {
		WebElement btn = driver.findElement(By.id("sg.com.blu.android.uat:id/rebookConfirmationButton"));
		btn.click();
	}
	public void makeNewBookingClickOnNo() {
		WebElement btn = driver.findElement(By.id("sg.com.blu.android.uat:id/rebookCancelButton"));
		btn.click();
	}
	public List<WebElement> footerOptions()
	{
		List<WebElement> footerOptons=driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/parcelFooterOptions']//android.widget.TextView"));
		return footerOptons;
	}
	public WebElement firstStatusOfParcelOverviewScreen()
	{
		WebElement status=driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
		return status;
	}
	public WebElement parcelDetailsScreenStatus()
	{
		WebElement status=driver.findElement(By.id("sg.com.blu.android.uat:id/statusTextView"));
		return status;
	}
}
