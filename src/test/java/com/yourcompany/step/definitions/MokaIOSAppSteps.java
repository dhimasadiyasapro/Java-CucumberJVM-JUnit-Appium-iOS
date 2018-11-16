package com.yourcompany.step.definitions;

import com.yourcompany.utils.SauceUtils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.testobject.appium.junit.TestObjectAppiumSuite;
import org.testobject.appium.junit.TestObjectAppiumSuiteWatcher;
import org.testobject.appium.junit.TestObjectTestResultWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testobject.rest.api.appium.common.TestObject;

import java.net.URL;
import static org.junit.Assert.*;

import com.yourcompany.Pages.*;

// @TestObject(testLocally = false, testObjectApiKey = "C1B7072F857B4468B99320F5B7362E0F", testObjectSuiteId = 001)
// @RunWith(TestObjectAppiumSuite.class)
public class MokaIOSAppSteps {

  public static final String USERNAME = System.getenv("SAUCE_USERNAME");
  public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
  public static final String testobjectApiKey = System.getenv("TESTOBJECT_API_KEY");
  // public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
  public static final String URL = "https://us1.appium.testobject.com/wd/hub";
  // public static String app = "https://github.com/dhimasadiyasapro/selenium-saucelab-training/blob/master/MokaDev-Custom-V1-3.ipa?raw=true";
  public static IOSDriver driver;
  public static MokaIOSAppPage page;

  public String sessionId;
  public String jobName;
  public String buildTag;

  protected String platformName = System.getenv("platformName");
  // protected String appiumVersion = System.getenv("appiumVersion");
  protected String platformVersion = System.getenv("platformVersion");
  protected String deviceName = System.getenv("deviceName");
  protected String deviceOrientation = System.getenv("deviceOrientation");

  /* Sets the test name to the name of the test method. */
	@Rule
	public TestName testName = new TestName();

	/* Takes care of sending the result of the tests over to TestObject. */
	@Rule
	public TestObjectTestResultWatcher resultWatcher = new TestObjectTestResultWatcher();
  // public TestObjectAppiumSuiteWatcher resultWatcher = new TestObjectAppiumSuiteWatcher();

  @Before
  public void setUp(Scenario scenario) throws Exception {
    jobName = scenario.getName();

    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability("testobject_api_key", this.testobjectApiKey);
    // capabilities.setCapability("testobject_api_key", resultWatcher.getApiKey());
    capabilities.setCapability("testobject_app_id", "1");
    // capabilities.setCapability("testobject_test_report_id", resultWatcher.getTestReportId());
    capabilities.setCapability("recordDeviceVitals", true);

    capabilities.setCapability("privateDevicesOnly", "false");

    capabilities.setCapability("platformName", this.platformName);
    // capabilities.setCapability("platformVersion", this.platformVersion);
    capabilities.setCapability("deviceName", this.deviceName);
    // capabilities.setCapability("deviceOrientation", this.deviceOrientation);
    // capabilities.setCapability("orientation", "LANDSCAPE");
    // capabilities.setCapability("appiumVersion", this.appiumVersion);
    // capabilities.setCapability("app", app);
    capabilities.setCapability("name", jobName);

    buildTag = System.getenv("BUILD_TAG");
    if (buildTag != null) {
      capabilities.setCapability("build", buildTag);
    }

    driver = new IOSDriver(new URL(URL), capabilities);
    // driver = new IOSDriver(resultWatcher.getTestObjectOrLocalAppiumEndpointURL(), capabilities);
    sessionId = driver.getSessionId().toString();

    resultWatcher.setRemoteWebDriver(driver);

    System.out.println(driver.getCapabilities().getCapability("testobject_test_report_url"));
    System.out.println(driver.getCapabilities().getCapability("testobject_test_live_view_url"));
  }

  @Given("^I am on the Moka Start Page$")
  public void user_is_on_moka_start_page() throws Exception {
    page = new MokaIOSAppPage(driver);
  }

  @When("^I click on Sign In$")
  public void user_click_on_sign_in() throws Exception {
    page.clickSignIn();
  }

  @When("^I input correct login credentials$")
  public void user_input_correct_login_cred() throws Exception {
    page.inputEmailOnSignIn("mokasingle@mailinator.com");
    page.inputPasswordOnSignIn("123456");
  }

  @When("^I click on Login Button$")
  public void user_click_on_sign_in_button() throws Exception {
    page.clickSignInButton();
  }

  @Then("^I should be on Library Page of Moka iOS App$")
  public void moka_library_page_displayed() throws Exception {
    assertFalse(page.isOnMokaLibraryPage());
  }

  @After
  public void tearDown(Scenario scenario) throws Exception {
    // driver.quit();
    // SauceUtils.UpdateResults(USERNAME, ACCESS_KEY, !scenario.isFailed(), sessionId);
    // System.out.println("SauceOnDemandSessionID="+ sessionId + "job-name="+ jobName);
    System.out.println("Moka Test is finished!");
  }
}
