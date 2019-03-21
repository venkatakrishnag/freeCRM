package Page;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;

public class LoginPage extends BasePage {
    By byLogin=null;
    By byUserName=null;
    By byPassword=null;

    public LoginPage(WebDriver driver) throws Exception {
        super(driver);
        loadPageElements();
    }

    public void loadPageElements(){
        byUserName=By.xpath("//input[contains(@name,'username')]");
        byPassword=By.xpath("//input[contains(@name,'password')]");
        byLogin=By.xpath("//input[contains(@class,'btn btn-small')]");
    }

    protected WebElement userName(){
        return driver.findElement(byUserName);
    }
    protected WebElement password(){
        return driver.findElement(byPassword);
    }
    protected WebElement login(){
        return driver.findElement(byLogin);
    }


}
