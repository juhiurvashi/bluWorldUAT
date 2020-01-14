package bluAndroid.bluAndroid.TestScripts;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
import bluAndroid.bluAndroid.pageObjects.SavedLocationsScreen;
import bluAndroid.bluAndroid.pageObjects.SignUpScreen;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;


public class CreateBookingRequestTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password,bluId,name,bluPort;
	MapViewScreen mvs;
	SavedLocationsScreen sls;
	SignUpScreen sus;
	BookingScreen bs;
	CommonFunctions cf;
	PopUp pu;
	MenuScreen ms;
	ParcelsDetailsScreen pds;
	@BeforeMethod
	public void preCondition() throws IOException

	{
		System.out.println("Set Up....");
		CreateBookingRequestTestRunner.driver = BaseClass.getAppCapabilities();
		mobileNo = CommonUtil.getPropertyValue("login", "mobileNo");
		password = CommonUtil.getPropertyValue("login", "password");
		bluId=CommonUtil.getPropertyValue("bookingRequest", "bluId");
		name=CommonUtil.getPropertyValue("bookingRequest", "name");
		bluPort=CommonUtil.getPropertyValue("bluPortDetails", "bluPort");
		ls = new LoginScreen(driver);
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		mvs=new MapViewScreen(driver);
		sls=new SavedLocationsScreen(driver);
		sus= new SignUpScreen(driver);
		bs=new BookingScreen(driver);
		pu=new PopUp(driver);
		ls = new LoginScreen(driver);
		cf=new CommonFunctions(driver);
		ms=new MenuScreen(driver);
		pds=new ParcelsDetailsScreen(driver);
	}
	
	@Test
	public void tc01_createBooking()
	{
		System.out.println("tc01_createBooking");
		extentTest = extentReports.createTest("tc01_createBooking");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), name);
		Assert.assertEquals(bs.bluIdDisplayed().getText(), bluId);
		bs.clickOnNextBtn();
		bs.clickOnBluPortDeliveryMethod();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName=driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectProductCatagory();
		//((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.enterRemarks().sendKeys("remarks");
		//driver.hideKeyboard();
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		//WebElement bluPortName=driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSize();
		bs.clickOnNextBtn();
		bs.proceedToPaymentBtn();
		
		try {
			bs.clickOnContinue();
		}
		catch(Exception e){
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}
		bs.clickOnMakePaymentBtn();
		bs.clickOnCloseBtn();
	}
	@Test
	public void tc02_createBooking()
	{
		System.out.println("tc02_createBooking");
		extentTest = extentReports.createTest("tc02_createBooking");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), name);
		Assert.assertEquals(bs.bluIdDisplayed().getText(), bluId);
		bs.clickOnNextBtn();
		bs.clickOnBluPortDeliveryMethod();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName=driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectProductCatagory();
		//((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.enterRemarks().sendKeys("remarks");
		//driver.hideKeyboard();
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		//WebElement bluPortName=driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSize();
		bs.clickOnNextBtn();
		bs.proceedToPaymentBtn();
		
		try {
			bs.clickOnContinue();
		}
		catch(Exception e){
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}
		bs.clickOnMakePaymentBtn();
		bs.clickOnViewBookingDetails();
		cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(), "Cancel parcel", 20);
		pds.clickOnCancel();
	}
	@Test
	public void clickOnParcels()
	{
		extentTest = extentReports.createTest("clickOnParcels");
		ms.clickOnMenu();
		ms.clickOnMyParcels();
		WebElement status=driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
		status.click();
		cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(), "Fees", 20);
		System.out.println(pds.parcelDetailsViewContainer().size());
		/*
		 * for(int i=0;i<pds.parcelDetailsViewContainer().size();i++) {
		 * System.out.println(pds.parcelDetailsViewContainer().get(i).getText()); for
		 * (int j=0;j<5;j++) { cf.swipe(pds.parcelDetailsViewContainer().get(i),pds.
		 * parcelDetailsViewContainer().get(i)); } System.out.println("swiped"); }
		 */
	//cf.screenSwipe();
		//cf.swipeInListTillExpectedTextAndTap(bs.parcelDetailsViewContainer(), bs.clickOnCancel().getText(), 20);
		//pds.clickOnCancel();
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
