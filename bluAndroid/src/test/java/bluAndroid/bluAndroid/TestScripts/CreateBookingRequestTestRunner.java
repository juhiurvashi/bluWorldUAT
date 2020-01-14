package bluAndroid.bluAndroid.TestScripts;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
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
import bluAndroid.bluAndroid.pageObjects.LoginScreen;
import bluAndroid.bluAndroid.pageObjects.MapViewScreen;
import bluAndroid.bluAndroid.pageObjects.MenuScreen;
import bluAndroid.bluAndroid.pageObjects.MyDetailsScreen;
import bluAndroid.bluAndroid.pageObjects.PopUp;
import bluAndroid.bluAndroid.pageObjects.SavedLocationsScreen;
import bluAndroid.bluAndroid.pageObjects.SignUpScreen;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;


public class CreateBookingRequestTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password,bluId,name,bluPort;
	MapViewScreen mvs;
	SavedLocationsScreen sls;
	MenuScreen ms;
	BookingScreen bs;
	PopUp pu;
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
		ms= new MenuScreen(driver);
		bs=new BookingScreen(driver);
		pu=new PopUp(driver);
		ls = new LoginScreen(driver);
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
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.enterRemarks().sendKeys("remarks");
		driver.hideKeyboard();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,3000)","");
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
		sls.selectActionBtn();
		try {
			bs.clickOnContinue();
		}
		catch(Exception e){
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}
		//bs.clickOnMakePaymentBtn();
		bs.clickOnCloseBtn();
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
