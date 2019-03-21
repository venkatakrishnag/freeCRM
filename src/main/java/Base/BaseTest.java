package Base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {
    public DesiredCapabilities capabilities;
    protected WebDriver driver;
    public HashMap hmBundleKeys;

    // Browser Initialization
    protected WebDriver InitializeWebDriver() {
        Logz.Message("Initializing WebDriver");
        Logz.Message("Launching " + System.getProperty("browsername").toUpperCase() + " WebDriver");
        if (System.getProperty("browsername").toUpperCase().equals("FIREFOX")) {
            // Browser Mobile View
            if(System.getProperty("mobileView").equals("mobile")){
                Map<String, String> mobileEmulation = new HashMap<String, String>();
                mobileEmulation.put("deviceName", System.getProperty("mobileType"));
                Map<String, Object>firefoxOptions=new HashMap<String, Object>();
                firefoxOptions.put("mobileEmulation", mobileEmulation);
                capabilities= DesiredCapabilities.firefox();
                driver=new FirefoxDriver(capabilities);
            }
            else {
                driver = new FirefoxDriver();
            }
        }
        else if (System.getProperty("browsername").toUpperCase().equals("CHROME")) {
            // Browser Mobile View
            if(System.getProperty("mobileView").equals("mobile")){
                Map<String, String> mobileEmulation = new HashMap<String, String>();
                mobileEmulation.put("deviceName", System.getProperty("mobileType"));
                Map<String, Object> chromeOptions = new HashMap<String, Object>();
                chromeOptions.put("mobileEmulation", mobileEmulation);
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                driver = new ChromeDriver(capabilities);
            }
            else{
                driver=new ChromeDriver();
            }
           
        }
        Logz.Message("Web Driver is Initilaized");
        driver.manage().window().maximize();
        Logz.Message("Launching home page : " + System.getProperty("appUrl"));
        driver.navigate().to(System.getProperty("appUrl"));
        return driver;

    }
    public void LoadBundleValues() {
        hmBundleKeys= new HashMap<String, String>();
        String config = System.getProperty("user.dir")+"\\src\\test\\resource\\EN_Bundle.properties";

        Properties prop = new Properties();
        try {
            InputStream inputStream = new FileInputStream(config);
            prop.load(inputStream);
            for (String key : prop.stringPropertyNames()) {
                String value = prop.getProperty(key);
                hmBundleKeys.put(key.toString(), value.toString());
                Logz.Message(key + ":" + value);
            }
        } catch (IOException e) {
            Logz.Message(e.getMessage());
        }
    }

    @BeforeSuite
    public void suiteSetup1() {
        //loading test bundle values
        LoadBundleValues();
        Logz.Message("Before Suite");
    }

    @BeforeTest
    public void beforeTest() throws  Exception {
        Logz.Message("Before test");
    }
    @BeforeClass
    public static void setUpClass() throws  Exception {
        Logz.Message("Before Class");
    }

    @BeforeMethod
    public void beforeMethod(Method methodName, ITestContext testContext) {
        Logz.startTestCase(methodName.getName());
        InitializeWebDriver();

    }

    @AfterMethod
    public void screenShot(ITestResult result){

        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Reporter.log("Test Case is Fail");
                File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                File screenshotName=new File("C:\\Reports\\freeCRM\\freeCRM"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"extent.png");
                FileUtils.copyFile(scrFile,screenshotName);
                System.out.println("Successfully captured a screenshot");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
            if (result.isSuccess()) {
                Logz.Pass();
            } else {
                Logz.Fail();
            }
            Logz.endTestCase();

        }

        driver.quit();

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        Logz.Message("After Class");
    }

    @AfterTest
    public void afterTest() {
        Logz.Message("After test");
    }

    @AfterSuite(alwaysRun = true )
    public void cleanupSuite() {
        Logz.extentReportseport.flush();
    }

}
