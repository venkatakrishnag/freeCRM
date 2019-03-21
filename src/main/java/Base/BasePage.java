package Base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class BasePage {
    protected WebDriver driver;
    public HashMap hmBundleKey;
    Select select=null;
    String text;
    int indexvalue;


    //constructors
    public BasePage() throws FileNotFoundException {
    }

    public BasePage(WebDriver webDriver) throws Exception {
        this.driver = webDriver;
        LoadBundleValues();
    }

    public void validateTitle(String strResourceText, String strPageTitle) throws Exception {
        Logz.Message("Verifying page title");
        Assert.assertEquals(strResourceText, strPageTitle);
        Logz.Message("Page Title Verified");
    }

  /*  protected WebElement getElement(By by) throws  Exception{
        int i = 1;
        WebElement element = null;
        while(i < Integer.valueOf(System.getProperty("waitelement"))){
            try{
                element = driver.findElement(by);
                break;
            }
            catch(Exception ex) {
                WaitForElement(1);
                //Logz.Message("Waited for one second");
                i++;
            }
        }
        return  element;
    }*/
    protected void WaitForElement(int iNoOfSeconds) throws Exception {
        Thread.sleep(iNoOfSeconds * 1000);
    }
    protected void WaitForElementVisibility(WebElement element) {
        ((WebDriverWait)driver).until(ExpectedConditions.visibilityOf(element));
    }
    // WebPage ScrollDown
    protected void pageScroll(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }
    //select the drop down ByVisibleText
    protected void selectVisibleText(WebElement element,String strVisibleText){
        select=new Select(element);
        select.selectByVisibleText(strVisibleText);
    }
    //select the drop down ByIndex
    protected void selectIndex(WebElement element,int index){
        select=new Select(element);
        select.selectByIndex(index);
    }
    //select the drop down ByValue
    protected void selectValue(WebElement element,String Value){
        select=new Select(element);
        select.selectByValue(Value);
    }

    public void LoadBundleValues() {
        hmBundleKey = new HashMap<String, String>();
        String config = System.getProperty("user.dir")+"\\src\\main\\resources\\EN_Bundle.properties";

        Properties prop = new Properties();
        try {
            InputStream inputStream = new FileInputStream(config);
            prop.load(inputStream);
            for (String key : prop.stringPropertyNames()) {
                String value = prop.getProperty(key);
                hmBundleKey.put(key.toString(), value.toString());
                Logz.Message(key + ":" + value);
            }
        } catch (IOException e) {
            Logz.Message(e.getMessage());
        }
    }


        public void dismiss() {

        }


        public void accept() {

        }


        public String getText() {
            return null;
        }


        public void sendKeys(String s) {

        }



}
