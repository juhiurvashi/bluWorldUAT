package bluAndroid.bluAndroid.TestScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import bluAndroid.bluAndroid.pageObjects.BookingScreen;
import bluAndroid.bluAndroid.pageObjects.CommonFunctions;
import bluAndroid.bluAndroid.pageObjects.LoginScreen;
import bluAndroid.bluAndroid.pageObjects.MapViewScreen;
import bluAndroid.bluAndroid.pageObjects.MenuScreen;
import bluAndroid.bluAndroid.pageObjects.MyDetailsScreen;
import bluAndroid.bluAndroid.pageObjects.ParcelsDetailsScreen;
import bluAndroid.bluAndroid.pageObjects.PopUp;
import bluAndroid.bluAndroid.pageObjects.PublicProfileScreen;
import bluAndroid.bluAndroid.pageObjects.RequestBookingScreen;
import bluAndroid.bluAndroid.pageObjects.SavedLocationsScreen;
import bluAndroid.bluAndroid.pageObjects.SignUpScreen;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class CreateRequestTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password, bluId, name, bluPort, extectedText,ownBluId,ownName,mobilefromPublicProfilePropertiesValue;
	MapViewScreen mvs;
	SavedLocationsScreen sls;
	SignUpScreen sus;
	BookingScreen bs;
	CommonFunctions cf;
	PopUp pu;
	MenuScreen ms;
	ParcelsDetailsScreen pds;
	PublicProfileScreen pp;
	MyDetailsScreen mds;
	RequestBookingScreen rbs;

	@BeforeMethod
	public void preCondition() throws IOException

	{
		System.out.println("Set Up....");
		CreateRequestTestRunner.driver = BaseClass.getAppCapabilities();
		mobileNo = CommonUtil.getPropertyValue("login", "mobileNo");
		password = CommonUtil.getPropertyValue("login", "password");
		bluId = CommonUtil.getPropertyValue("bookingRequest", "bluId");
		ownBluId = CommonUtil.getPropertyValue("bookingRequest", "ownBluId");
		name = CommonUtil.getPropertyValue("bookingRequest", "name");
		ownName = CommonUtil.getPropertyValue("bookingRequest", "ownName");
		bluPort = CommonUtil.getPropertyValue("bluPortDetails", "bluPort");
		mobilefromPublicProfilePropertiesValue=CommonUtil.getPropertyValue("publicProfile", "mobile");
		ls = new LoginScreen(driver);
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		mvs = new MapViewScreen(driver);
		sls = new SavedLocationsScreen(driver);
		sus = new SignUpScreen(driver);
		bs = new BookingScreen(driver);
		pu = new PopUp(driver);
		ls = new LoginScreen(driver);
		cf = new CommonFunctions(driver);
		ms = new MenuScreen(driver);
		pds = new ParcelsDetailsScreen(driver);
		pp=new PublicProfileScreen(driver);
		mds=new MyDetailsScreen(driver);
		rbs=new RequestBookingScreen(driver);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void B_S13_TC01_CreateRequestViaBluPortWithBluId() {
		System.out.println("B_S13_TC01_CreateRequestViaBluPortWithBluId");
		extentTest = extentReports.createTest("B_S13_TC01_CreateRequestViaBluPortWithBluId");
		rbs.clickOnRequestForParcel();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), name);
		Assert.assertEquals(bs.bluIdDisplayed().getText(), bluId);
		bs.clickOnNextBtn();
		bs.clickOnBluPortDeliveryMethod();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.actionButton();
		rbs.viewRequestDetailsBtn().click();
		Assert.assertEquals(rbs.statusTitle().getText(), "Request sent");
		bs.actionButton();
		Assert.assertEquals(rbs.sentReminderMsg1().getText(), "Sent");
		Assert.assertEquals(rbs.sentReminderMsg2().getText(), "We have sent a reminder to");
		Assert.assertEquals(rbs.sentReminderName().getText(), "Hari");
		Assert.assertEquals(rbs.sentReminderNumber().getText(), "+65 98582028");
		rbs.clickOnCloseBtn();
		rbs.clickOnCancelRequest();
		Assert.assertEquals(pu.alertTitle().getText(), "Cancel request");
		Assert.assertEquals(pu.alertMsg().getText(), "You are about to cancel the booking request.");
		pu.clickBtn1();
		
		/*
		 * WebDriverWait wait=new WebDriverWait(driver, 20);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated((By.id(
		 * "sg.com.blu.android.uat:id/bookingTitleTextView"))));
		 */
		//System.out.println(rbs.statusTitle().getText());
	//	Assert.assertEquals(rbs.statusTitle().getText(), "Request cancelled");
		
       

	}
	@Test
	public void B_S13_TC02_CreateRequestViaBluPortWithMobileNo() {
		System.out.println("B_S13_TC02_CreateRequestViaBluPortWithMobileNo");
		extentTest = extentReports.createTest("B_S13_TC02_CreateRequestViaBluPortWithMobileNo");
		rbs.clickOnRequestForParcel();
		pp.clickOnMobile();
		if(pp.permissionAllowBtn().isDisplayed())
		{
			pp.permissionAllowBtn().click();
		}
		pp.textField().sendKeys("96969696");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

		bs.recipientNametextField().sendKeys("test");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		bs.clickOnBluPortDeliveryMethod();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.actionButton();
		rbs.viewRequestDetailsBtn().click();
		Assert.assertEquals(rbs.statusTitle().getText(), "Request sent");
		bs.actionButton();
		//Assert.assertEquals(rbs.sentReminderMsg1().getText(), "Sent");
		//Assert.assertEquals(rbs.sentReminderMsg2().getText(), "We have sent a reminder to");
		Assert.assertEquals(rbs.sentReminderName().getText(), "test");
		Assert.assertEquals(rbs.sentReminderNumber().getText(), "+65 96969696");
		rbs.clickOnCloseBtn();
		rbs.clickOnCancelRequest();
		Assert.assertEquals(pu.alertTitle().getText(), "Cancel request");
		Assert.assertEquals(pu.alertMsg().getText(), "You are about to cancel the booking request.");
		pu.clickBtn1();
		//Assert.assertEquals(rbs.statusTitle().getText(), "Request cancelled");
	}
	//@Test
	public void B_S13_TC03_CreateRequestViaBluHomeWithBluId() {
		System.out.println("B_S13_TC03_CreateRequestViaBluHomeWithBluId");
		extentTest = extentReports.createTest("B_S13_TC03_CreateRequestViaBluHomeWithBluId");
		rbs.clickOnRequestForParcel();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), name);
		Assert.assertEquals(bs.bluIdDisplayed().getText(), bluId);
		bs.clickOnNextBtn();
		bs.clickOnBluHomeDeliveryMethod();
		sls.bluHomeAddress1().sendKeys("357 Admiralty drive");
		sls.bluHomeAddress2().sendKeys("#7 174");
		sls.bluHomePostalCode().sendKeys("750357");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.actionButton();
		//Assert.assertEquals(rbs.AddressIsDisplayed(), "357 Admiralty drive");
		bs.actionButton();
		bs.actionButton();
		rbs.viewRequestDetailsBtn().click();
		Assert.assertEquals(rbs.statusTitle().getText(), "Request sent");
		bs.actionButton();
		Assert.assertEquals(rbs.sentReminderMsg1().getText(), "Sent");
		Assert.assertEquals(rbs.sentReminderMsg2().getText(), "We have sent a reminder to");
		Assert.assertEquals(rbs.sentReminderName().getText(), "Hari");
		Assert.assertEquals(rbs.sentReminderNumber().getText(), "+65 98582028");
		rbs.clickOnCloseBtn();
		rbs.clickOnCancelRequest();
		Assert.assertEquals(pu.alertTitle().getText(), "Cancel request");
		Assert.assertEquals(pu.alertMsg().getText(), "You are about to cancel the booking request.");
		pu.clickBtn1();
		//Assert.assertEquals(rbs.statusTitle().getText(), "Request cancelled");
	}
	//@Test
	public void B_S13_TC04_CreateRequestViaBluHomeWithMobileNo() {
		System.out.println("B_S13_TC04_CreateRequestViaBluHomeWithMobileNo");
		extentTest = extentReports.createTest("B_S13_TC04_CreateRequestViaBluHomeWithMobileNo");
		rbs.clickOnRequestForParcel();
		pp.clickOnMobile();
		if(pp.permissionAllowBtn().isDisplayed())
		{
			pp.permissionAllowBtn().click();
		}
		pp.textField().sendKeys("96969696");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();

		bs.recipientNametextField().sendKeys("test");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		bs.clickOnBluHomeDeliveryMethod();
		sls.bluHomeAddress1().sendKeys("357 Admiralty drive");
		sls.bluHomeAddress2().sendKeys("#7 174");
		sls.bluHomePostalCode().sendKeys("750357");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.actionButton();
		//Assert.assertEquals(rbs.AddressIsDisplayed(), "357 Admiralty drive");
		bs.actionButton();
		bs.actionButton();
		rbs.viewRequestDetailsBtn().click();
		Assert.assertEquals(rbs.statusTitle().getText(), "Request sent");
		bs.actionButton();
		//Assert.assertEquals(rbs.sentReminderMsg1().getText(), "Sent");
		//Assert.assertEquals(rbs.sentReminderMsg2().getText(), "We have sent a reminder to");
		Assert.assertEquals(rbs.sentReminderName().getText(), "test");
		Assert.assertEquals(rbs.sentReminderNumber().getText(), "+65 96969696");
		rbs.clickOnCloseBtn();
		rbs.clickOnCancelRequest();
		Assert.assertEquals(pu.alertTitle().getText(), "Cancel request");
		Assert.assertEquals(pu.alertMsg().getText(), "You are about to cancel the booking request.");
		pu.clickBtn1();
		//Assert.assertEquals(rbs.statusTitle().getText(), "Request cancelled");
	}
	//@Test
	public void B_S14_TC01_cancelRequest() {
		System.out.println("B_S14_TC01_cancelRequest");
		extentTest = extentReports.createTest("B_S14_TC01_cancelRequest");
		rbs.clickOnRequestForParcel();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), name);
		Assert.assertEquals(bs.bluIdDisplayed().getText(), bluId);
		bs.clickOnNextBtn();
		bs.clickOnBluPortDeliveryMethod();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.actionButton();
		rbs.viewRequestDetailsBtn().click();
		rbs.clickOnCancelRequest();
		Assert.assertEquals(pu.alertTitle().getText(), "Cancel request");
		Assert.assertEquals(pu.alertMsg().getText(), "You are about to cancel the booking request.");
		pu.clickBtn1();
		/*
		 * WebElement ele=driver.findElement(By.id(
		 * "sg.com.blu.android.uat:id/bookingRequestDetailsContainer")); WebElement
		 * f=ele.findElement(By.id("sg.com.blu.android.uat:id/bookingTitleTextView"));
		 * //System.out.println(ele.getText()); System.out.println(f.getText());
		 */
		//Assert.assertEquals(rbs.requestStatus().get(0).getText(), "Request cancelled");
	}
	@AfterMethod
	public void getResult(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.SKIP) {
			System.out.println("Skipped extent report");
			extentTest.skip(MarkupHelper.createLabel(testResult.getName() + " Test Case SKIPPED", ExtentColor.BLUE));
			extentTest.skip(testResult.getThrowable());
			// extentTest.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName() + "
			// - Test Case Skipped", ExtentColor.ORANGE));
		} else if (testResult.getStatus() == ITestResult.FAILURE) {
			extentTest.fail(MarkupHelper.createLabel(testResult.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			extentTest.fail(testResult.getThrowable());
			String screenshotPath = CommonUtil.takesScreenShotFailed(driver, testResult.getName());
			extentTest.addScreenCaptureFromPath(screenshotPath);
		} else if (testResult.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(MarkupHelper.createLabel(testResult.getName() + " Test Case PASSED", ExtentColor.GREEN));
			String screenshotPath = CommonUtil.takesScreenShot(driver, testResult.getName());

			extentTest.addScreenCaptureFromPath(screenshotPath);
		}

	}

}
