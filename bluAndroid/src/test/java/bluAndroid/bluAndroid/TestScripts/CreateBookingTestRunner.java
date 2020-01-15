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
import bluAndroid.bluAndroid.pageObjects.PublicProfileScreen;
import bluAndroid.bluAndroid.pageObjects.RequestBookingScreen;
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

public class CreateBookingTestRunner extends BaseClass {
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
		CreateBookingTestRunner.driver = BaseClass.getAppCapabilities();
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
		
	}

	@Test
	public void B_S01_TC01_CreateBookingViaBluPortWithBluId() {
		System.out.println("B_S01_TC01_CreateBookingViaBluPortWithBluId");
		extentTest = extentReports.createTest("B_S01_TC01_CreateBookingViaBluPortWithBluId");
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
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectProductCatagory();
		bs.enterRemarks().sendKeys("remarks");
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSize();
		bs.clickOnNextBtn();
		bs.actionButton();
		try {
			bs.clickOnContinue();
		} catch (Exception e) {
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}
		bs.clickOnMakePaymentBtn();
		bs.clickOnViewBookingDetails();
		for(int i=0;i<pds.viewAlertContainer().size();i++)
		{
			if(i==0)
			Assert.assertEquals(pds.viewAlertContainer().get(0).getText(), "Drop-off by");
			if(i==1)
				Assert.assertTrue(pds.viewAlertContainer().get(1).isDisplayed());
			if(i==2)
				Assert.assertEquals(pds.viewAlertContainer().get(2).getText(), "Extend");
		}
		Assert.assertTrue(pds.viewDropCodeBtn().isDisplayed());
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Awaiting drop-off");
		cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(), "Total", 20);
		for (int i = 0; i < pds.footerOptions().size(); i++) {
			System.out.println(pds.footerOptions().get(i).getText());
			if(i==0)
			{
			pds.footerOptions().get(0).click();
			break;
			}

		}
		pds.clickOnCancellationBtn();
		pds.cancelReason();
		pds.cancelBookingButton();
		pds.makeNewBookingClickOnNo();
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Cancelled");
	}
	@Test
	public void B_S01_TC02_CreateBookingViaBluPortWithMobileNo() {
		System.out.println("B_S02_TC01_CreateBookingViaBluPortWithMobileNo");
		extentTest = extentReports.createTest("B_S01_TC02_CreateBookingViaBluPortWithMobileNo");
		ls.sendParcel().click();
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
		bs.clickOnBluPortDeliveryMethod();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectProductCatagory();
		bs.enterRemarks().sendKeys("remarks");
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSize();
		bs.clickOnNextBtn();
		bs.actionButton();
		try {
			bs.clickOnContinue();
		} catch (Exception e) {
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}
		bs.clickOnMakePaymentBtn();
		bs.clickOnViewBookingDetails();
		for(int i=0;i<pds.viewAlertContainer().size();i++)
		{
			if(i==0)
			Assert.assertEquals(pds.viewAlertContainer().get(0).getText(), "Drop-off by");
			if(i==1)
				Assert.assertTrue(pds.viewAlertContainer().get(1).isDisplayed());
			if(i==2)
				Assert.assertEquals(pds.viewAlertContainer().get(2).getText(), "Extend");
		}
		Assert.assertTrue(pds.viewDropCodeBtn().isDisplayed());
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Awaiting drop-off");
		cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(), "Total", 20);
		for (int i = 0; i < pds.footerOptions().size(); i++) {
			System.out.println(pds.footerOptions().get(i).getText());
			if(i==0)
			{
			pds.footerOptions().get(0).click();
			break;
			}

		}
		pds.clickOnCancellationBtn();
		pds.cancelReason();
		pds.cancelBookingButton();
		pds.makeNewBookingClickOnNo();
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Cancelled");
	}
	@Test
	public void B_S01_TC03_CreateBookingViaBluPortWithBluIdToOwnself() {
		System.out.println("B_S01_TC03_CreateBookingViaBluPortWithBluIdToOwnself");
		extentTest = extentReports.createTest("B_S01_TC03_CreateBookingViaBluPortWithBluIdToOwnself");
		ls.sendParcel().click();
		bs.textBox().sendKeys(ownBluId);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), ownName);
		Assert.assertEquals(bs.bluIdDisplayed().getText(), ownBluId);
		bs.clickOnNextBtn();
		bs.clickOnBluPortDeliveryMethod();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectProductCatagory();
		bs.enterRemarks().sendKeys("remarks");
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSize();
		bs.clickOnNextBtn();
		bs.actionButton();
		try {
			bs.clickOnContinue();
		} catch (Exception e) {
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}
		bs.clickOnMakePaymentBtn();
		bs.clickOnViewBookingDetails();
		for(int i=0;i<pds.viewAlertContainer().size();i++)
		{
			if(i==0)
			Assert.assertEquals(pds.viewAlertContainer().get(0).getText(), "Drop-off by");
			if(i==1)
				Assert.assertTrue(pds.viewAlertContainer().get(1).isDisplayed());
			if(i==2)
				Assert.assertEquals(pds.viewAlertContainer().get(2).getText(), "Extend");
		}
		Assert.assertTrue(pds.viewDropCodeBtn().isDisplayed());
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Awaiting drop-off");
		cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(), "Total", 20);
		for (int i = 0; i < pds.footerOptions().size(); i++) {
			System.out.println(pds.footerOptions().get(i).getText());
			if(i==0)
			{
			pds.footerOptions().get(0).click();
			break;
			}

		}
		pds.clickOnCancellationBtn();
		pds.cancelReason();
		pds.cancelBookingButton();
		pds.makeNewBookingClickOnNo();
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Cancelled");
	}

	@Test
	public void B_S01_TC04_CreateBookingViaBluHomeWithBluId() {
		System.out.println("B_S01_TC04_CreateBookingViaBluHomeWithBluId");
		extentTest = extentReports.createTest("B_S01_TC04_CreateBookingViaBluHomeWithBluId");
		ls.sendParcel().click();
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
		WebElement address=driver.findElement(By.id("sg.com.blu.android.uat:id/address1TextView"));
		Assert.assertEquals(address.getText(), "357 Admiralty drive");
		bs.actionButton();
		bs.selectProductCatagory();
		bs.enterRemarks().sendKeys("remarks");
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSize();
		bs.clickOnNextBtn();
		bs.actionButton();
		try {
			bs.clickOnContinue();
		} catch (Exception e) {
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}
		bs.clickOnMakePaymentBtn();
		bs.clickOnViewBookingDetails();
		for(int i=0;i<pds.viewAlertContainer().size();i++)
		{
			if(i==0)
			Assert.assertEquals(pds.viewAlertContainer().get(0).getText(), "Drop-off by");
			if(i==1)
				Assert.assertTrue(pds.viewAlertContainer().get(1).isDisplayed());
			if(i==2)
				Assert.assertEquals(pds.viewAlertContainer().get(2).getText(), "Extend");
		}
		Assert.assertTrue(pds.viewDropCodeBtn().isDisplayed());
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Awaiting drop-off");
		cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(), "Total", 20);
		for (int i = 0; i < pds.footerOptions().size(); i++) {
			System.out.println(pds.footerOptions().get(i).getText());
			if(i==0)
			{
			pds.footerOptions().get(0).click();
			break;
			}

		}
		pds.clickOnCancellationBtn();
		pds.cancelReason();
		pds.cancelBookingButton();
		pds.makeNewBookingClickOnNo();
		//Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Cancelled");
	}
	@Test
	public void B_S01_TC05_CreateBookingViaBluHomeWithMobileNo() {
		System.out.println("B_S01_TC05_CreateBookingViaBluHomeWithMobileNo");
		extentTest = extentReports.createTest("B_S01_TC05_CreateBookingViaBluHomeWithMobileNo");
		ls.sendParcel().click();
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
		bs.actionButton();
		bs.selectProductCatagory();
		bs.enterRemarks().sendKeys("remarks");
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSize();
		bs.clickOnNextBtn();
		bs.actionButton();
		try {
			bs.clickOnContinue();
		} catch (Exception e) {
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}
		bs.clickOnMakePaymentBtn();
		bs.clickOnViewBookingDetails();
		for(int i=0;i<pds.viewAlertContainer().size();i++)
		{
			if(i==0)
			Assert.assertEquals(pds.viewAlertContainer().get(0).getText(), "Drop-off by");
			if(i==1)
				Assert.assertTrue(pds.viewAlertContainer().get(1).isDisplayed());
			if(i==2)
				Assert.assertEquals(pds.viewAlertContainer().get(2).getText(), "Extend");
		}
		Assert.assertTrue(pds.viewDropCodeBtn().isDisplayed());
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Awaiting drop-off");
		cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(), "Total", 20);
		for (int i = 0; i < pds.footerOptions().size(); i++) {
			System.out.println(pds.footerOptions().get(i).getText());
			if(i==0)
			{
			pds.footerOptions().get(0).click();
			break;
			}

		}
		pds.clickOnCancellationBtn();
		pds.cancelReason();
		pds.cancelBookingButton();
		pds.makeNewBookingClickOnNo();
		//Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Cancelled");
	}
	@Test
	public void B_S01_TC06_CreateBookingViaBluHomeWithBluIdToOwnself() {
		System.out.println("B_S01_TC06_CreateBookingViaBluHomeWithBluIdToOwnself");
		extentTest = extentReports.createTest("B_S01_TC06_CreateBookingViaBluHomeWithBluIdToOwnself");
		ls.sendParcel().click();
		bs.textBox().sendKeys(ownBluId);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), ownName);
		Assert.assertEquals(bs.bluIdDisplayed().getText(), ownBluId);
		bs.clickOnNextBtn();
		bs.clickOnBluHomeDeliveryMethod();
		sls.bluHomeAddress1().sendKeys("357 Admiralty drive");
		sls.bluHomeAddress2().sendKeys("#7 174");
		sls.bluHomePostalCode().sendKeys("750357");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.actionButton();
		bs.actionButton();
		bs.selectProductCatagory();
		bs.enterRemarks().sendKeys("remarks");
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSize();
		bs.clickOnNextBtn();
		bs.actionButton();
		try {
			bs.clickOnContinue();
		} catch (Exception e) {
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}
		bs.clickOnMakePaymentBtn();
		bs.clickOnViewBookingDetails();
		for(int i=0;i<pds.viewAlertContainer().size();i++)
		{
			if(i==0)
			Assert.assertEquals(pds.viewAlertContainer().get(0).getText(), "Drop-off by");
			if(i==1)
				Assert.assertTrue(pds.viewAlertContainer().get(1).isDisplayed());
			if(i==2)
				Assert.assertEquals(pds.viewAlertContainer().get(2).getText(), "Extend");
		}
		Assert.assertTrue(pds.viewDropCodeBtn().isDisplayed());
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Awaiting drop-off");
		cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(), "Total", 20);
		for (int i = 0; i < pds.footerOptions().size(); i++) {
			System.out.println(pds.footerOptions().get(i).getText());
			if(i==0)
			{
			pds.footerOptions().get(0).click();
			break;
			}

		}
		pds.clickOnCancellationBtn();
		pds.cancelReason();
		pds.cancelBookingButton();
		pds.makeNewBookingClickOnNo();
		//Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Cancelled");
	}

	@Test
	public void clickOnParcels() {
		extentTest = extentReports.createTest("clickOnParcels");
		ms.clickOnMenu();
		ms.clickOnMyParcels();
		WebElement status = driver.findElement(By.xpath(
				"//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
		status.click();
		for(int i=0;i<pds.viewAlertContainer().size();i++)
		{
			if(i==0)
			{Assert.assertEquals(pds.viewAlertContainer().get(0).getText(), "Drop-off by");
			System.out.println("i==0 Pass");}
			if(i==1)
				{Assert.assertTrue(pds.viewAlertContainer().get(1).isDisplayed());
			System.out.println("i==1 Pass");}
			if(i==2)
			{
				Assert.assertEquals(pds.viewAlertContainer().get(2).getText(), "Extend");
			System.out.println("i==2 Pass");
			}
		}
		Assert.assertTrue(pds.viewDropCodeBtn().isDisplayed());
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(),"Awaiting drop-off");
		cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(), "Total", 40);
		for (int i = 0; i < pds.footerOptions().size(); i++) {
			System.out.println(pds.footerOptions().get(i).getText());
			pds.footerOptions().get(i).click();
		}
		
		pds.clickOnCancellationBtn();
		pds.cancelReason();
		pds.cancelBookingButton();
		pds.makeNewBooking();
		// System.out.println(pds.parcelDetailsViewContainer().size());
		/*
		 * for(int i=0;i<pds.parcelDetailsViewContainer().size();i++) {
		 * System.out.println(pds.parcelDetailsViewContainer().get(i).getText()); for
		 * (int j=0;j<5;j++) { cf.swipe(pds.parcelDetailsViewContainer().get(i),pds.
		 * parcelDetailsViewContainer().get(i)); } System.out.println("swiped"); }
		 */
		// cf.screenSwipe();
		// cf.swipeInListTillExpectedTextAndTap(bs.parcelDetailsViewContainer(),
		// bs.clickOnCancel().getText(), 20);
		// pds.clickOnCancel();
	}
	@Test
	public void B_S02_TC01_InvaldBluId()
	{
		System.out.println("B_S02_TC01_InvaldBluId");
		extentTest = extentReports.createTest("B_S02_TC01_InvaldBluId()");
		ls.sendParcel().click();
		pp.textField().sendKeys("BLU123456");
		WebElement error=driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		Assert.assertEquals(error.getText(), "Member not found.");
	}
	@Test
	public void B_S02_TC02_NotRegisteredMobile()
	{
		System.out.println("B_S02_TC02_NotRegisteredMobile");
		extentTest = extentReports.createTest("B_S02_TC02_InvaldMobile()");
		ls.sendParcel().click();
		pp.clickOnMobile();
		WebElement allow=driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		if(allow.isDisplayed())
		{
			allow.click();
		}
		pp.textField().sendKeys("91235555");
		Assert.assertEquals(bs.recipientDetailsNotRegisteredMobileMsg().getText(), "This number is not connected to any blu account. Please confirm the details before proceeding.");
	}
	@Test
	public void B_S02_TC05_InvalidMobileAndRecipientName()
	{
		System.out.println("B_S02_TC05_InvalidMobileAndRecipientName");
		extentTest = extentReports.createTest("B_S02_TC05_InvalidMobileAndRecipientName()");
		ls.sendParcel().click();
		pp.clickOnMobile();
		WebElement allow=driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		if(allow.isDisplayed())
		{
			allow.click();
		}
		//WebElement mobileTextField=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		pp.textField().sendKeys("91235555");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		bs.recipientNametextField().sendKeys("@test");
		
		bs.clickOnNextBtn();
		//Assert.assertEquals(bs.recipientNametextFieldError().getText(), "Only alphabets and spaces allowed.");
		bs.mobileNumberTextField().clear();
		bs.mobileNumberTextField().sendKeys("77123456");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.mobileNotextFieldError().getText(), "Enter a valid mobile number.");
		
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
