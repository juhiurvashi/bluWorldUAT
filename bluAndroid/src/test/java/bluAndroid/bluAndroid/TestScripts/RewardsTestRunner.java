package bluAndroid.bluAndroid.TestScripts;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

import bluAndroid.bluAndroid.pageObjects.BookingScreen;
import bluAndroid.bluAndroid.pageObjects.CommonFunctions;
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
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class RewardsTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password, bluId, bluPort, extectedText, ownBluId, ownName, creditRewardName, pointsRewardName,
			pointsAndCreditsRewardName, creditRewardDes, pointsRewardDes, pointsAndCreditsRewardDes,physicalRewardName,physicalRewardDes,promocodeRewardName,promocodeRewardDes;
			
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
	PointsAndRewardsScreen prs;

	@BeforeMethod
	public void preCondition() throws IOException

	{
		System.out.println("Set Up....");
		RewardsTestRunner.driver = BaseClass.getAppCapabilities();
		mobileNo = CommonUtil.getPropertyValue("login", "mobileNo");
		password = CommonUtil.getPropertyValue("login", "password");
		bluId = CommonUtil.getPropertyValue("bookingRequest", "bluId");
		ownBluId = CommonUtil.getPropertyValue("bookingRequest", "ownBluId");

		ownName = CommonUtil.getPropertyValue("bookingRequest", "ownName");
		bluPort = CommonUtil.getPropertyValue("bluPortDetails", "bluPort");
		creditRewardName = CommonUtil.getPropertyValue("reward", "creditRewardName");
		pointsRewardName = CommonUtil.getPropertyValue("reward", "pointsRewardName");
		pointsAndCreditsRewardName = CommonUtil.getPropertyValue("reward", "pointsAndCreditsRewardName");
		creditRewardDes = CommonUtil.getPropertyValue("reward", "creditRewardDes");
		pointsRewardDes = CommonUtil.getPropertyValue("reward", "pointsRewardDes");
		pointsAndCreditsRewardDes = CommonUtil.getPropertyValue("reward", "pointsAndCreditsRewardDes");
		physicalRewardName= CommonUtil.getPropertyValue("reward", "physicalRewardName");
		physicalRewardDes= CommonUtil.getPropertyValue("reward", "physicalRewardDes");
		promocodeRewardName=CommonUtil.getPropertyValue("reward", "promocodeRewardName");
		promocodeRewardDes=CommonUtil.getPropertyValue("reward", "promocodeRewardDes");
		ls = new LoginScreen(driver);
		ls.clickLoginLink();
		
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
		prs = new PointsAndRewardsScreen(driver);
	}
	/*
	 * @Test(dataProvider = "Rewards") public void B_S01_TC01_redeemRewards(String
	 * rewardName, String rewardDes) {
	 * System.out.println("B_S01_TC01_redeemRewards"); extentTest =
	 * extentReports.createTest("B_S01_TC01_redeemRewards"); ms.clickOnMenu();
	 * ms.clickOnPointsAndRewards();
	 * cf.swipeInListTillExpectedTextAndTap(prs.rewardNameList(), rewardName, 20);
	 * Assert.assertEquals(prs.rewardName().getText(), rewardName);
	 * Assert.assertEquals(prs.rewardDescription().getText(), rewardDes);
	 * 
	 * if (prs.pointsCreditsList().size() == 2) {
	 * Assert.assertEquals(prs.requiredText1().getText(), "Points required");
	 * Assert.assertEquals(prs.requiredText2().getText(), "Credit required"); }
	 * 
	 * if (rewardName.equalsIgnoreCase(creditRewardName))
	 * 
	 * cf.clickOnCloseBtn(); }
	 * 
	 * @DataProvider(name = "Rewards") public String[][] getDataFromDataprovider() {
	 * return new String[][] { { creditRewardName, creditRewardDes }, {
	 * pointsRewardName, pointsRewardDes }, { pointsAndCreditsRewardName,
	 * pointsAndCreditsRewardName } }; }
	 */

	@Test
	public void G_S06_TC01_redeemRewardWithCredits() throws IOException {
		System.out.println("G_S06_TC01_redeemRewardWithCredits");
		extentTest = extentReports.createTest("G_S06_TC01_redeemRewardWithCredits");
		ls.bluLogin(mobileNo, password);
		ms.clickOnMenu();
		ms.clickOnPointsAndRewards();
		cf.swipeInListTillExpectedTextAndTap(prs.rewardNameList(), creditRewardName, 20);
		Assert.assertEquals(prs.rewardName().getText(), creditRewardName);
		Assert.assertEquals(prs.rewardDescription().getText(), creditRewardDes);
		Assert.assertEquals(prs.requiredText1().getText(), "Credits required");
		Assert.assertEquals(prs.availableText1().getText(), "Available credits");
		Assert.assertEquals(prs.redeemBtn().getText(), "REDEEM WITH CREDITS");
		prs.redeemBtn().click();
		Assert.assertEquals(prs.alertTitle().getText(), "Redeem reward?");
		pu.clickBtn1();	
		cf.clickOnCloseBtn();
		//assertTrue(ls.sendParcel().isDisplayed());	
		prs.myRewards().click();
		System.out.println(prs.latestRedeemedOrUtilizedReward().getText());
		Assert.assertEquals(prs.latestRedeemedOrUtilizedReward().getText(), creditRewardName);
	}
	@Test
	public void G_S06_TC02_redeemRewardWithPoints() throws IOException {
		System.out.println("G_S06_TC02_redeemRewardWithPoints");
		extentTest = extentReports.createTest("G_S06_TC02_redeemRewardWithPoints");
		ls.bluLogin(mobileNo, password);
		ms.clickOnMenu();
		ms.clickOnPointsAndRewards();
		cf.swipeInListTillExpectedTextAndTap(prs.rewardNameList(), pointsRewardName, 20);
		Assert.assertEquals(prs.rewardName().getText(), pointsRewardName);
		//System.out.println(prs.rewardName().getText());
		Assert.assertEquals(prs.rewardDescription().getText(), pointsRewardDes);
		//System.out.println(prs.rewardDescription().getText());
		Assert.assertEquals(prs.requiredText2().getText(), "Points required");
		//System.out.println(prs.requiredText1().getText());
		Assert.assertEquals(prs.availableText2().getText(), "Available points");
		//System.out.println(prs.availableText1().getText());
		Assert.assertEquals(prs.redeemBtn().getText(), "REDEEM WITH POINTS");
		prs.redeemBtn().click();
		Assert.assertEquals(prs.alertTitle().getText(), "Redeem reward?");
		pu.clickBtn1();	
		cf.clickOnCloseBtn();
		//assertTrue(ls.sendParcel().isDisplayed());
		prs.myRewards().click();
		System.out.println(prs.latestRedeemedOrUtilizedReward().getText());
		Assert.assertEquals(prs.latestRedeemedOrUtilizedReward().getText(), pointsRewardName);
	}
	@Test
	public void G_S06_TC03_redeemRewardWithPointsAndCredits() throws IOException {
		System.out.println("G_S06_TC03_redeemRewardWithPointsAndCredits");
		extentTest = extentReports.createTest("G_S06_TC03_redeemRewardWithPointsAndCredits");
		ls.bluLogin(mobileNo, password);
		ms.clickOnMenu();
		ms.clickOnPointsAndRewards();
		cf.swipeInListTillExpectedTextAndTap(prs.rewardNameList(), pointsAndCreditsRewardName, 20);
		Assert.assertEquals(prs.rewardName().getText(), pointsAndCreditsRewardName);
		Assert.assertEquals(prs.rewardDescription().getText(), pointsAndCreditsRewardDes);
		Assert.assertEquals(prs.requiredText2().getText(), "Points required");
		Assert.assertEquals(prs.availableText2().getText(), "Available points");
		Assert.assertEquals(prs.requiredText1().getText(), "Credits required");
		Assert.assertEquals(prs.availableText1().getText(), "Available credits");
		Assert.assertEquals(prs.redeemBtn().getText(), "REDEEM WITH POINTS AND CREDITS");
		prs.redeemBtn().click();
		Assert.assertEquals(prs.alertTitle().getText(), "Redeem reward?");
		prs.clickBtn1();
		Assert.assertEquals(prs.redeemedRewardTextMsg().getText(),"You can apply this reward to your booking.");
		//System.out.println(driver.findElement(By.xpath("sg.com.blu.android.uat:id/closeButton/following-sibling::android.widget.TextView[1]")));
		cf.clickOnCloseBtn();
		//assertTrue(ls.sendParcel().isDisplayed());
		prs.myRewards().click();
		System.out.println(prs.latestRedeemedOrUtilizedReward().getText());
		Assert.assertEquals(prs.latestRedeemedOrUtilizedReward().getText(), pointsAndCreditsRewardName);
		
	}
	@Test
	public void G_S06_TC04_redeemPhysicalReward() throws IOException {
		System.out.println("G_S06_TC04_redeemPhysicalReward");
		extentTest = extentReports.createTest("G_S06_TC04_redeemPhysicalReward");
		ls.bluLogin(mobileNo, password);
		ms.clickOnMenu();
		ms.clickOnPointsAndRewards();
		cf.swipeInListTillExpectedTextAndTap(prs.rewardNameList(), physicalRewardName, 20);
		Assert.assertEquals(prs.rewardName().getText(), physicalRewardName);
		Assert.assertEquals(prs.rewardDescription().getText(), physicalRewardDes);
		prs.redeemBtn().click();
		mvs.clickonSearchBox();
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName = driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		//Assert.assertEquals(prs.redeemedRewardTextMsg(),"You can apply this reward to your booking.");
		cf.clickOnCloseBtn();
		//assertTrue(ls.sendParcel().isDisplayed());
		
	}
	@Test
	public void G_S06_TC05_promoCode() throws IOException {
		System.out.println("G_S06_TC05_promoCode");
		extentTest = extentReports.createTest("G_S06_TC05_promoCode");
		ls.bluLogin(mobileNo, password);
		ms.clickOnMenu();
		ms.clickOnPointsAndRewards();
		prs.promoCodeTab().click();
		prs.promoCodeTextField().sendKeys("AMS123456789");
		prs.rewardDiscoverableViaPromoCode().click();
		Assert.assertEquals(prs.rewardName().getText(), promocodeRewardName);
		Assert.assertEquals(prs.rewardDescription().getText(), promocodeRewardDes);
		prs.redeemBtn().click();
		Assert.assertEquals(prs.alertTitle().getText(), "Redeem reward?");
		prs.clickBtn1();
		Assert.assertEquals(prs.redeemedRewardTextMsg().getText(),"You can apply this reward to your booking.");
		cf.clickOnCloseBtn();
		prs.myRewards().click();
		System.out.println(prs.latestRedeemedOrUtilizedReward().getText());
		Assert.assertEquals(prs.latestRedeemedOrUtilizedReward().getText(), promocodeRewardName);
	}
	@Test
	public void G_S06_TC06_InsufficientCredits() throws IOException
	{
		System.out.println("G_S06_TC06_InsufficientCredits");
		extentTest = extentReports.createTest("G_S06_TC06_InsufficientCredits");
		ls.bluLogin("81437794", "Abcd1234");
		ms.clickOnMenu();
		ms.clickOnPointsAndRewards();
		cf.swipeInListTillExpectedTextAndTap(prs.rewardNameList(), creditRewardName, 20);
		Assert.assertEquals(prs.rewardName().getText(), creditRewardName);
		Assert.assertEquals(prs.rewardDescription().getText(), creditRewardDes);
		Assert.assertEquals(prs.requiredText1().getText(), "Credits required");
		Assert.assertEquals(prs.availableText1().getText(), "Available credits");
		Assert.assertEquals(prs.redeemBtn().getText(), "REDEEM WITH CREDITS");
		prs.redeemBtn().click();
		Assert.assertEquals(pu.alertMsg().getText(), "Insufficient credits to redeem this reward. Top up your wallet to redeem.");
		pu.clickBtn1();
	}
	
	@Test
	public void G_S06_TC07_InsufficientPoints() throws IOException
	{
		System.out.println("G_S06_TC07_InsufficientPoints");
		extentTest = extentReports.createTest("G_S06_TC07_InsufficientPoints");
		ls.bluLogin("81437794", "Abcd1234");
		ms.clickOnMenu();
		ms.clickOnPointsAndRewards();
		cf.swipeInListTillExpectedTextAndTap(prs.rewardNameList(), pointsRewardName, 20);
		Assert.assertEquals(prs.rewardName().getText(), pointsRewardName);
		Assert.assertEquals(prs.rewardDescription().getText(), pointsRewardDes);
		Assert.assertEquals(prs.requiredText2().getText(), "Points required");
		Assert.assertEquals(prs.availableText2().getText(), "Available points");
		Assert.assertEquals(prs.redeemBtn().getText(), "REDEEM WITH POINTS");
		prs.redeemBtn().click();
		Assert.assertEquals(pu.alertMsg().getText(), "Insufficient points to redeem this reward. Earn more points to redeem.");
		pu.clickBtn1();
	}
	@Test
	public void G_S06_TC08_InsufficientPointsOrCredits() throws IOException
	{
		System.out.println("G_S06_TC08_InsufficientPointsOrCredits");
		extentTest = extentReports.createTest("G_S06_TC08_InsufficientPointsOrCredits");
		ls.bluLogin("81437794", "Abcd1234");
		ms.clickOnMenu();
		ms.clickOnPointsAndRewards();
		cf.swipeInListTillExpectedTextAndTap(prs.rewardNameList(), pointsAndCreditsRewardName, 20);
		Assert.assertEquals(prs.rewardName().getText(), pointsAndCreditsRewardName);
		Assert.assertEquals(prs.rewardDescription().getText(), pointsAndCreditsRewardDes);
		Assert.assertEquals(prs.requiredText2().getText(), "Points required");
		Assert.assertEquals(prs.availableText2().getText(), "Available points");
		Assert.assertEquals(prs.requiredText1().getText(), "Credits required");
		Assert.assertEquals(prs.availableText1().getText(), "Available credits");
		Assert.assertEquals(prs.redeemBtn().getText(), "REDEEM WITH POINTS AND CREDITS");
		prs.redeemBtn().click();
		Assert.assertEquals(pu.alertMsg().getText(), "Insufficient points and/or credits to redeem this reward. Top up your wallet or earn more points to redeem.");
		pu.clickBtn1();
	}
	}
	

