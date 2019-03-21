package MyRunner;

import Action.LoginAction;
import Base.BaseTest;
import org.testng.annotations.Test;

public class TestRunner extends BaseTest {

    LoginAction loginAction;

    @Test
    public void freeCrmLogin() throws Exception {
        loginAction=new LoginAction(driver);
        loginAction.PerformAdminLogin(String.valueOf(hmBundleKeys.get("userID")),String.valueOf(hmBundleKeys.get("password")));
    }

}
