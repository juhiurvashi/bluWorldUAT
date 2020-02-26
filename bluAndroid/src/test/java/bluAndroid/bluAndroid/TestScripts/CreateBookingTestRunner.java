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
import bluAndroid.bluAndroid.pageObjects.HomeScreen;
import bluAndroid.bluAndroid.pageObjects.LoginScreen;
import bluAndroid.bluAndroid.pageObjects.MapViewScreen;
import bluAndroid.bluAndroid.pageObjects.MenuScreen;
import bluAndroid.bluAndroid.pageObjects.MyDetailsScreen;
import bluAndroid.bluAndroid.pageObjects.ParcelsDetailsScreen;
import bluAndroid.bluAndroid.pageObjects.PointsAndRewardsScreen;
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

public class CreateBookingTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password, bluId, name, bluPort, extectedText, ownBluId, ownName,
			mobilefromPublicProfilePropertiesValue, pointsAndCreditsRewardName;
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
	HomeScreen hs;
	PointsAndRewardsScreen prs;
	Wallet w;

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
		mobilefromPublicProfilePropertiesValue = CommonUtil.getPropertyValue("publicProfile", "mobile");
		pointsAndCreditsRewardName = CommonUtil.getPropertyValue("reward", "pointsAndCreditsRewardName");
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
		pp = new PublicProfileScreen(driver);
		mds = new MyDetailsScreen(driver);
		rbs = new RequestBookingScreen(driver);
		hs = new HomeScreen(driver);
		prs = new PointsAndRewardsScreen(driver);
		w = new Wallet(driver);

	}

	@Test
	public void B_S01_TC01_CreateBookingViaBluPortWithBluId() {
		System.out.println("B_S01_TC01_CreateBookingViaBluPortWithBluId");
		extentTest = extentReports.createTest("B_S01_TC01_CreateBookingViaBluPortWithBluId");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
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
		cf.swiptToTop();
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(), "Cancelled");
	}

	@Test
	public void B_S01_TC02_CreateBookingViaBluPortWithMobileNo() {
		System.out.println("B_S01_TC02_CreateBookingViaBluPortWithMobileNo");
		extentTest = extentReports.createTest("B_S01_TC02_CreateBookingViaBluPortWithMobileNo");
		ls.sendParcel().click();
		pp.clickOnMobile();
		if (pp.permissionAllowBtn().isDisplayed()) {
			pp.permissionAllowBtn().click();
		}
		pp.textField().sendKeys("96969696");
		cf.swiptToBottom();
		bs.clickOnNextBtn();

		bs.recipientNametextField().sendKeys("test");
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
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
				System.out.println("Payment method is already added");
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
		cf.swiptToTop();
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(), "Cancelled");
	}
	@Test
	public void B_S01_TC03_CreateBookingViaBluPortWithBluIdToOwnself() {
		System.out.println("B_S01_TC03_CreateBookingViaBluPortWithBluIdToOwnself");
		extentTest = extentReports.createTest("B_S01_TC03_CreateBookingViaBluPortWithBluIdToOwnself");
		ls.sendParcel().click();
		bs.textBox().sendKeys(ownBluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
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
		cf.swiptToTop();
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(), "Cancelled");
	}
	//@Test
	public void B_S01_TC04_CreateBookingViaBluHomeWithBluId() {
		System.out.println("B_S01_TC04_CreateBookingViaBluHomeWithBluId");
		extentTest = extentReports.createTest("B_S01_TC04_CreateBookingViaBluHomeWithBluId");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), name);
		Assert.assertEquals(bs.bluIdDisplayed().getText(), bluId);
		bs.clickOnNextBtn();
		bs.clickOnBluHomeDeliveryMethod();
		sls.bluHomeAddress1().sendKeys("357 Admiralty drive");
		sls.bluHomeAddress2().sendKeys("#7 174");
		sls.bluHomePostalCode().sendKeys("750357");
		// ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		cf.swipe(sls.bluHomeAddress1(), sls.bluHomeAddress1());
		bs.actionButton();
		WebElement address = driver.findElement(By.id("sg.com.blu.android.uat:id/address1TextView"));
		Assert.assertEquals(address.getText(), "357 Admiralty drive");
		bs.actionButton();
		bs.selectProductCatagory();
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
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
				System.out.println("Payment method is already added");
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
		cf.swiptToTop();
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(), "Cancelled");
	}
	//@Test
	public void B_S01_TC05_CreateBookingViaBluHomeWithMobileNo() {
		System.out.println("B_S01_TC05_CreateBookingViaBluHomeWithMobileNo");
		extentTest = extentReports.createTest("B_S01_TC05_CreateBookingViaBluHomeWithMobileNo");
		ls.sendParcel().click();
		pp.clickOnMobile();
		if (pp.permissionAllowBtn().isDisplayed()) {
			pp.permissionAllowBtn().click();
		}
		pp.textField().sendKeys("96969696");
		cf.swiptToBottom();
		bs.clickOnNextBtn();

		bs.recipientNametextField().sendKeys("test");
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

		bs.clickOnNextBtn();
		bs.clickOnBluHomeDeliveryMethod();
		sls.bluHomeAddress1().sendKeys("357 Admiralty drive");
		sls.bluHomeAddress2().sendKeys("#7 174");
		sls.bluHomePostalCode().sendKeys("750357");
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.actionButton();
		bs.actionButton();
		bs.selectProductCatagory();
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
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
		cf.swiptToTop();
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(), "Cancelled");
	}
	//@Test
	public void B_S01_TC06_CreateBookingViaBluHomeWithBluIdToOwnself() {
		System.out.println("B_S01_TC06_CreateBookingViaBluHomeWithBluIdToOwnself");
		extentTest = extentReports.createTest("B_S01_TC06_CreateBookingViaBluHomeWithBluIdToOwnself");
		ls.sendParcel().click();
		bs.textBox().sendKeys(ownBluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), ownName);
		Assert.assertEquals(bs.bluIdDisplayed().getText(), ownBluId);
		bs.clickOnNextBtn();
		bs.clickOnBluHomeDeliveryMethod();
		sls.bluHomeAddress1().sendKeys("357 Admiralty drive");
		sls.bluHomeAddress2().sendKeys("#7 174");
		sls.bluHomePostalCode().sendKeys("750357");
		cf.swipe(sls.bluHomeAddress1(), sls.bluHomeAddress1());
		bs.actionButton();
		bs.actionButton();
		bs.selectProductCatagory();
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
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
		cf.swiptToTop();
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(), "Cancelled");
	}

	// @Test
	public void clickOnParcels() {
		extentTest = extentReports.createTest("clickOnParcels");
		ms.clickOnMenu();
		ms.clickOnMyParcels();
		WebElement status = driver.findElement(By.xpath(
				"//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
		status.click();
		cf.swiptToBottom();
		cf.swiptToTop();

		/*
		 * for(int i=0;i<pds.viewAlertContainer().size();i++) { if(i==0)
		 * {Assert.assertEquals(pds.viewAlertContainer().get(0).getText(),
		 * "Request Pending"); System.out.println("i==0 Pass");}
		 * 
		 * if(i==1) {Assert.assertTrue(pds.viewAlertContainer().get(1).isDisplayed());
		 * System.out.println("i==1 Pass");} if(i==2) {
		 * Assert.assertEquals(pds.viewAlertContainer().get(2).getText(), "Extend");
		 * System.out.println("i==2 Pass"); }
		 * 
		 * }
		 */
		/*
		 * Assert.assertTrue(pds.viewDropCodeBtn().isDisplayed());
		 * Assert.assertEquals(pds.parcelDetailsScreenStatus().getText()
		 * ,"Awaiting drop-off");
		 * cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(),
		 * "Total", 40); for (int i = 0; i < pds.footerOptions().size(); i++) {
		 * System.out.println(pds.footerOptions().get(i).getText());
		 * pds.footerOptions().get(i).click(); }
		 * 
		 * pds.clickOnCancellationBtn(); pds.cancelReason(); pds.cancelBookingButton();
		 * pds.makeNewBooking();
		 */
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
		rbs.clickOnCancelRequest();
		Assert.assertEquals(pu.alertTitle().getText(), "Cancel request");
		Assert.assertEquals(pu.alertMsg().getText(), "You are about to cancel the booking request.");
		pu.clickBtn1();
		System.out.println();
		WebElement ele = driver.findElement(By.id("sg.com.blu.android.uat:id/bookingRequestDetailsContainer"));
		WebElement f = ele.findElement(By.id("bookingTitleTextView"));
		System.out.println(ele.getText());
		System.out.println(f.getText());
		// Assert.assertEquals(f.getText(), "Request cancelled");
	}

	@Test
	public void B_S02_TC01_InvaldBluId() {
		System.out.println("B_S02_TC01_InvaldBluId");
		extentTest = extentReports.createTest("B_S02_TC01_InvaldBluId()");
		ls.sendParcel().click();
		pp.textField().sendKeys("BLU123456");
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		WebElement error = driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		Assert.assertEquals(error.getText(), "Member not found.");
	}

	@Test
	public void B_S02_TC02_NotRegisteredMobile() {
		System.out.println("B_S02_TC02_NotRegisteredMobile");
		extentTest = extentReports.createTest("B_S02_TC02_InvaldMobile()");
		ls.sendParcel().click();
		pp.clickOnMobile();
		WebElement allow = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		if (allow.isDisplayed()) {
			allow.click();
		}
		pp.textField().sendKeys("91235555");
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.recipientDetailsNotRegisteredMobileMsg().getText(),
				"This number is not connected to any blu account. Please confirm the details before proceeding.");
	}

	@Test
	public void B_S02_TC05_InvalidMobileAndRecipientName() {
		System.out.println("B_S02_TC05_InvalidMobileAndRecipientName");
		extentTest = extentReports.createTest("B_S02_TC05_InvalidMobileAndRecipientName()");
		ls.sendParcel().click();
		pp.clickOnMobile();
		WebElement allow = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		if (allow.isDisplayed()) {
			allow.click();
		}
		// WebElement
		// mobileTextField=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		pp.textField().sendKeys("91235555");
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		bs.recipientNametextField().sendKeys("@test");

		bs.clickOnNextBtn();
		Assert.assertEquals(bs.textFieldError().getText(), "Only alphabets and spaces allowed.");
		bs.mobileNumberTextField().clear();
		bs.mobileNumberTextField().sendKeys("77123456");
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.textFieldError().getText(), "Enter a valid mobile number.");

	}

	@Test
	public void B_S02_TC05_ChangeRecipientLink() {
		System.out.println("B_S02_TC05_ChangeRecipientLink");
		extentTest = extentReports.createTest("B_S02_TC05_ChangeRecipientLink");
		ls.sendParcel().click();
		pp.clickOnMobile();
		WebElement allow = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		if (allow.isDisplayed()) {
			allow.click();
		}
		// WebElement
		// mobileTextField=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		pp.textField().sendKeys("91235555");
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		bs.recipientNametextField().sendKeys("@test");
		bs.clickOnChangeMobileNumberLink();
		Assert.assertTrue(pp.textField().isDisplayed());
		System.out.println(pp.textField().getText());
		Assert.assertEquals(pp.textField().getText(), "91235555");

	}

	@Test
	public void B_S08_TC01_ApplicableRewardsForBooking() {
		System.out.println("B_S08_TC01_ApplicableRewardsForBooking");
		extentTest = extentReports.createTest("B_S08_TC01_ApplicableRewardsForBooking");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSizeXS();
		for (int i = 0; i < bs.redeemedRewardsList().size(); i++) {
			System.out.println(bs.redeemedRewardsList().get(i).getText());
			if (i == 0) {
				Assert.assertEquals(bs.redeemedRewardsList().get(0).getText(), bs.latestRedeemedReward().getText());
			}
		}

	}

	@Test
	public void B_S06_TC01_VerifyProductCatagoryScreen() {
		System.out.println("B_S01_TC01_CreateBookingViaBluPortWithBluId");
		extentTest = extentReports.createTest("B_S01_TC01_CreateBookingViaBluPortWithBluId");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.selectProductCatagory();
		// bs.enterRemarks().sendKeys("remarks");
		Assert.assertFalse(driver.findElement(By.id("sg.com.blu.android.uat:id/nextButton")).isEnabled());

	}

	@Test
	public void B_S06_TC02_VerifyProductCatagoryScreenRemarksField() {
		System.out.println("B_S01_TC01_CreateBookingViaBluPortWithBluId");
		extentTest = extentReports.createTest("B_S01_TC01_CreateBookingViaBluPortWithBluId");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		bs.enterRemarks().sendKeys(
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.remarksTextFieldError().getText(), "Remarks must not exceed 100 characters.");
	}

	@Test
	public void B_S09_TC01_EditOrigin() {
		System.out.println("B_S09_TC01_EditOrigin");
		extentTest = extentReports.createTest("B_S09_TC01_EditOrigin");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSizeXS();
		bs.clickOnNextBtn();
		bs.editIconOfOrigin().click();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys("bluPort - HQ2");
		// WebElement bluPortName =
		// driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		Assert.assertEquals(bs.originNameOnReviewBooking().getText(), "bluPort - HQ2");
	}

	@Test
	public void B_S09_TC02_EditDestination() {
		System.out.println("B_S09_TC02_EditDestination");
		extentTest = extentReports.createTest("B_S09_TC02_EditDestination");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSizeXS();
		bs.clickOnNextBtn();
		bs.editIconOfDestination().click();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys("bluPort - HQ2");
		// WebElement bluPortName =
		// driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		Assert.assertEquals(bs.destinationNameOnReviewBooking().getText(), "bluPort - HQ2");
	}

	//@Test
	public void B_S09_TC03_EditDestinationBluHome() {
		System.out.println("B_S01_TC04_CreateBookingViaBluHomeWithBluId");
		extentTest = extentReports.createTest("B_S01_TC04_CreateBookingViaBluHomeWithBluId");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.firstNameDisplayed().getText(), name);
		Assert.assertEquals(bs.bluIdDisplayed().getText(), bluId);
		bs.clickOnNextBtn();
		bs.clickOnBluHomeDeliveryMethod();
		sls.bluHomeAddress1().sendKeys("357 Admiralty drive");
		sls.bluHomeAddress2().sendKeys("#7 174");
		sls.bluHomePostalCode().sendKeys("750357");
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		bs.actionButton();
		WebElement address = driver.findElement(By.id("sg.com.blu.android.uat:id/address1TextView"));
		Assert.assertEquals(address.getText(), "357 Admiralty drive");
		bs.actionButton();
		bs.selectProductCatagory();
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSizeXS();
		bs.clickOnNextBtn();
		bs.editIconOfDestination().click();
		Assert.assertEquals(bs.destinationBluHomeTitleTextView().getText(), "Destination bluHome");
	}

	@Test
	public void B_S09_TC04_EditBoxSize() {
		System.out.println("B_S09_TC04_EditBoxSize");
		extentTest = extentReports.createTest("B_S09_TC04_EditBoxSize");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSizeXS();
		bs.clickOnNextBtn();
		bs.editIconOfBoxSize().click();
		Assert.assertEquals(bs.firstTextOfScreen().getText(), "Select box size");
		bs.selectBoxSizeS();
		Assert.assertEquals(bs.boxSizeOnReviewBooking().getText(), "S 44cm x 8cm x 60cm");
	}

	@Test
	public void B_S09_TC05_EditProductCatagory() {
		System.out.println("B_S09_TC05_EditProductCatagory");
		extentTest = extentReports.createTest("B_S09_TC05_EditProductCatagory");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSizeXS();
		bs.clickOnNextBtn();
		cf.swipeInListTillExpectedTextAndTap(bs.reviewBookingScreenContainer(), "Total", 20);
		bs.editIconOfProductCatagory().click();
		Assert.assertEquals(bs.firstTextOfScreen().getText(), "Product category");
		bs.selectProductCatagorySecondItem();
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.productCatagoryOnReviewBooking().getText(), "Sport");
	}

	@Test
	public void B_S09_TC06_EditRemarks() {
		System.out.println("B_S09_TC06_EditRemarks");
		extentTest = extentReports.createTest("B_S09_TC06_EditRemarks");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		bs.selectBoxSizeXS();
		bs.clickOnNextBtn();
		cf.swipeInListTillExpectedTextAndTap(bs.reviewBookingScreenContainer(), "Total", 20);
		bs.editIconOfRemarks().click();
		Assert.assertEquals(bs.enterRemarks().getText(), "remarks");
		bs.enterRemarks().clear();
		bs.enterRemarks().sendKeys("Edited remarks");
		cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		Assert.assertEquals(bs.remarksOnReviewBooking().getText(), "Edited remarks");
	}

	@Test
	public void B_S09_TC07_EditReward() {
		System.out.println("B_S09_TC07_EditReward");
		extentTest = extentReports.createTest("B_S09_TC07_EditReward");
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSizeXS();
		bs.clickOnNextBtn();
		cf.swipeInListTillExpectedTextAndTap(bs.reviewBookingScreenContainer(), "Total", 20);
		bs.editIconOfReward().click();
		Assert.assertEquals(bs.firstTextOfScreen().getText(), "Redeemed rewards");
		String expected = bs.secondlatestRedeemedReward().getText();
		bs.secondlatestRedeemedReward().click();
		System.out.println();
		Assert.assertEquals(bs.rewardNameOfSeletedRewardForBooking().getText(), expected);

	}

	@Test
	public void B_S01_TC07_RedeemedRewardCanBeUsedInBooking() {
		System.out.println("B_S01_TC07_RedeemedRewardCanBeUsedInBooking");
		extentTest = extentReports.createTest("B_S01_TC07_RedeemedRewardCanBeUsedInBooking");
		ms.clickOnMenu();
		ms.clickOnPointsAndRewards();
		cf.swipeInListTillExpectedTextAndTap(prs.rewardNameList(), pointsAndCreditsRewardName, 20);
		Assert.assertEquals(prs.rewardName().getText(), pointsAndCreditsRewardName);
		prs.redeemBtn().click();
		Assert.assertEquals(prs.alertTitle().getText(), "Redeem reward?");
		prs.clickBtn1();
		Assert.assertEquals(prs.redeemedRewardTextMsg().getText(), "You can apply this reward to your booking.");
		cf.clickOnCloseBtn();
		prs.myRewards().click();
		System.out.println(prs.latestRedeemedOrUtilizedReward().getText());
		Assert.assertEquals(prs.latestRedeemedOrUtilizedReward().getText(), pointsAndCreditsRewardName);
		Assert.assertTrue(driver.findElement(By.id("sg.com.blu.android.uat:id/closeButton")).isDisplayed());
		cf.clickOnCloseBtn();
		if (driver.findElement(By.id("sg.com.blu.android.uat:id/closeButton")).isDisplayed())
			cf.clickOnCloseBtn();
		ls.sendParcel().click();
		bs.textBox().sendKeys(bluId);
		((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
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
		// bs.enterRemarks().sendKeys("remarks");
		// cf.swipe(bs.enterRemarks(), bs.enterRemarks());
		bs.clickOnNextBtn();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		bs.selectBoxSizeXS();
		bs.latestRedeemedReward().click();
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
				/*
				 * if(pu.alertMsg().getText().
				 * equalsIgnoreCase("You have entered invalid card data.Please check.")) {
				 * pu.clickBtn1(); w.addCardDetails(); w.clickOnConfirm(); }
				 */
				
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
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(), "Awaiting drop-off");
		// cf.swipeInListTillExpectedTextAndTap(pds.parcelDetailsViewContainer(),
		// "Total", 20);
		cf.swiptToBottom();
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
		cf.swiptToTop();
		Assert.assertEquals(pds.parcelDetailsScreenStatus().getText(), "Cancelled");
		cf.clickOnBackButton();
		cf.clickOnBackButton();
		hs.clickOnMenu();
		ms.clickOnPointsAndRewards();
		prs.myRewards().click();
		prs.myRewardsUtilizedRewards().click();
		Assert.assertEquals(prs.latestRedeemedOrUtilizedReward().getText(), pointsAndCreditsRewardName);

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
