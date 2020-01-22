package bluAndroid.bluAndroid.TestScripts;

import java.io.IOException;

import org.openqa.selenium.By;
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
import bluAndroid.bluAndroid.pageObjects.PublicProfileScreen;
import bluAndroid.bluAndroid.pageObjects.RequestBookingScreen;
import bluAndroid.bluAndroid.pageObjects.SavedLocationsScreen;
import bluAndroid.bluAndroid.pageObjects.SignUpScreen;
import bluAndroid.bluAndroid.pageObjects.Wallet;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class CreateBookingViaRequestTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password, bluId, name, bluPort, extectedText, ownBluId, ownName,
			mobilefromPublicProfilePropertiesValue;
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
	Wallet w;

	@BeforeMethod
	public void preCondition() throws IOException

	{
		System.out.println("Set Up....");
		CreateBookingViaRequestTestRunner.driver = BaseClass.getAppCapabilities();
		mobileNo = CommonUtil.getPropertyValue("login", "mobileNo");
		password = CommonUtil.getPropertyValue("login", "password");
		bluId = CommonUtil.getPropertyValue("bookingRequest", "bluId");
		ownBluId = CommonUtil.getPropertyValue("bookingRequest", "ownBluId");
		name = CommonUtil.getPropertyValue("bookingRequest", "name");
		ownName = CommonUtil.getPropertyValue("bookingRequest", "ownName");
		bluPort = CommonUtil.getPropertyValue("bluPortDetails", "bluPort");
		mobilefromPublicProfilePropertiesValue = CommonUtil.getPropertyValue("publicProfile", "mobile");
		ls = new LoginScreen(driver);
		ls.clickLoginLink();
		ls.bluLogin(mobilefromPublicProfilePropertiesValue, password);
		mvs = new MapViewScreen(driver);
		sls = new SavedLocationsScreen(driver);
		sus = new SignUpScreen(driver);
		bs = new BookingScreen(driver);
		pu = new PopUp(driver);
		ls = new LoginScreen(driver);
		cf = new CommonFunctions(driver);
		ms = new MenuScreen(driver);
		pds = new ParcelsDetailsScreen(driver);
		pp = new PublicProfileScreen(driver);
		mds = new MyDetailsScreen(driver);
		rbs = new RequestBookingScreen(driver);
		w=new Wallet(driver);

	}

	@Test
	public void B_S15_TC01_MakeBookingViaRequest() throws IOException {
		System.out.println("B_S01_TC01_CreateBookingViaBluPortWithBluId");
		extentTest = extentReports.createTest("B_S01_TC01_CreateBookingViaBluPortWithBluId");
		rbs.clickOnRequestForParcel();
		bs.textBox().sendKeys("BLU147735");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), "Urvashi");
		Assert.assertEquals(bs.bluIdDisplayed().getText(), "BLU147735");
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
		cf.clickOnBackButton();
		cf.clickOnBackButton();

		ms.logout();
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		ms.clickOnMenu();
		ms.clickOnMyParcels();
		WebElement status = driver.findElement(By.xpath(
				"//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
		status.click();

		bs.actionButton();// click on Make Booking

		bs.selectProductCatagory();
		bs.enterRemarks().sendKeys("remarks");
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName1 = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName1.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSizeXS();
		bs.clickOnNextBtn();
		bs.actionButton();
		try {
			bs.clickOnContinue();
		} catch (Exception e) {
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}

		System.out.println(bs.topUpBtnOnBookingScreen().getText());
		if (bs.topUpBtnOnBookingScreen().getText().equalsIgnoreCase("TOP UP")) {
			
			System.out.println("Entered in 1st stage");
			bs.topUpBtnOnBookingScreen().click();
			
			if((w.topUpSibling().getText()).equalsIgnoreCase("4242 (expires 04/20)")||(w.topUpSibling().getText()).equalsIgnoreCase("4242 (expires 04/22)"))
			{
				System.out.println("Payment method is added");
				
			}
			else if (w.topUpSibling().getText().equalsIgnoreCase("ADD PAYMENT METHOD")) 
			{
				System.out.println("Add Payment method");
				w.clickOnAddPaymentMethod();
				w.addCardDetails();
				w.clickOnConfirm();
				
			}
			w.selectTopUpValue();
			w.selectPreferredMethod();
			bs.clickOnMakePaymentBtn();
			cf.clickOnCloseBtn();
			bs.clickOnMakePaymentBtn();
			
			
		}
		else if (bs.topUpBtnOnBookingScreen().getText().equalsIgnoreCase("MAKE PAYMENT")) {
		
			System.out.println("Wallet have sufficinent balance for booking");
			bs.clickOnMakePaymentBtn();
			
		}
		bs.clickOnViewBookingDetails();
		for (int i = 0; i < pds.viewAlertContainer().size(); i++) {
			if (i == 0)
				Assert.assertEquals(pds.viewAlertContainer().get(0).getText(), "Drop-off by");
			if (i == 1)
				Assert.assertTrue(pds.viewAlertContainer().get(1).isDisplayed());
			if (i == 2)
				Assert.assertEquals(pds.viewAlertContainer().get(2).getText(), "Extend");
		}
		Assert.assertTrue(pds.viewDropCodeBtn().isDisplayed());
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(), "Awaiting drop-off");
		cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(), "Total", 20);
		for (int i = 0; i < pds.footerOptions().size(); i++) {
			System.out.println(pds.footerOptions().get(i).getText());
			if (i == 0) {
				pds.footerOptions().get(0).click();
				break;
			}

		}
		pds.clickOnCancellationBtn();
		pds.cancelReason();
		pds.cancelBookingButton();
		pds.makeNewBookingClickOnNo();
		// Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(), "Cancelled");
	}

	@Test
	public void B_S15_TC02_RejectRequest() throws IOException {
		System.out.println("B_S15_TC02_RejectRequest");
		extentTest = extentReports.createTest("B_S15_TC02_RejectRequest");
		rbs.clickOnRequestForParcel();
		bs.textBox().sendKeys("BLU147735");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), "Urvashi");
		Assert.assertEquals(bs.bluIdDisplayed().getText(), "BLU147735");
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
		cf.clickOnBackButton();
		cf.clickOnBackButton();
		ms.logout();
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		ms.clickOnMenu();
		ms.clickOnMyParcels();
		WebElement status = driver.findElement(By.xpath(
				"//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
		status.click();

		rbs.clickOnRejectRequest();
		Assert.assertEquals(pu.alertTitle().getText(), "Reject request");
		Assert.assertEquals(pu.alertMsg().getText(), "You are about to reject the booking request.");
		pu.clickBtn1();

	}
	/*
	 * @Test public void B_S15_TC03_EditDestination() throws IOException {
	 * System.out.println("B_S01_TC01_CreateBookingViaBluPortWithBluId"); extentTest
	 * = extentReports.createTest("B_S01_TC01_CreateBookingViaBluPortWithBluId");
	 * 
	 * rbs.clickOnRequestForParcel(); bs.textBox().sendKeys("BLU147735");
	 * ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
	 * bs.clickOnNextBtn(); Assert.assertEquals(bs.firstNameDisplayed().getText(),
	 * "Urvashi"); Assert.assertEquals(bs.bluIdDisplayed().getText(), "BLU147735");
	 * bs.clickOnNextBtn(); bs.clickOnBluPortDeliveryMethod();
	 * mvs.clickonSearchBox(); mvs.enterInSearchBox().sendKeys(bluPort); WebElement
	 * bluPortName =
	 * driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
	 * bluPortName.click(); Assert.assertTrue(mvs.bluPortPin().isDisplayed());
	 * Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
	 * sls.selectActionBtn(); bs.actionButton();
	 * rbs.viewRequestDetailsBtn().click(); cf.clickOnBackButton();
	 * cf.clickOnBackButton();
	 * 
	 * ms.logout(); ls.clickLoginLink(); ls.bluLogin(mobileNo, password);
	 * ms.clickOnMenu(); ms.clickOnMyParcels(); WebElement status =
	 * driver.findElement(By.xpath(
	 * "//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout[1]//android.widget.TextView[1]"
	 * )); status.click();
	 * 
	 * 
	 * bs.actionButton();//click on Make Booking
	 * 
	 * bs.selectProductCatagory(); // bs.enterRemarks().sendKeys("remarks"); //
	 * cf.swipe(bs.enterRemarks(), bs.enterRemarks()); bs.clickOnNextBtn();
	 * mvs.clickonSearchBox(); mvs.enterInSearchBox().sendKeys(bluPort); WebElement
	 * bluPortName1 =
	 * driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
	 * bluPortName1.click(); Assert.assertTrue(mvs.bluPortPin().isDisplayed());
	 * Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
	 * sls.selectActionBtn(); bs.selectBoxSizeXS(); bs.clickOnNextBtn();
	 * //Assert.assertFalse(bs.editIconOfDestination().isDisplayed()); }
	 */

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
