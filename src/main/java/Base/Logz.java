package Base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logz {
    public static String extentReport = "C:\\Reports\\freeCRM\\freeCRM_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +"CRMextent.html";

    // public static String extentImage = "C:\\Reports\\PIMTest\\extent.png";
    public static ExtentReports extentReportseport = new ExtentReports(extentReport, true);
    public static ExtentTest extest = extentReportseport.startTest("");


    //When new testcase starts
    public static void startTestCase(String sTestCaseName){

        //  Logz.Message("****************************************************************************************");

        // Logz.Message("****************************************************************************************");

        // Logz.Message("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

        // Logz.Message("****************************************************************************************");

        //  Logz.Message("****************************************************************************************");

        extest = extentReportseport.startTest(sTestCaseName);

    }

    //When test case ends
    public static void endTestCase(){

        Logz.Message("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
    }

    //To print a message
    public static void Message(String strMessage) {
        extest.log(LogStatus.PASS, strMessage);
    }

    //To Print an error
    public static void Error(String strMessage) {
        extest.log(LogStatus.ERROR, strMessage);
    }

    //To print a warning
    public static void Warning(String strMessage) {
        extest.log(LogStatus.WARNING, strMessage);
    }

    //To print as the test case failed
    public static void Fail() {
        extest.log(LogStatus.FAIL, "Test case Failed");
    }

    //To print as the test case passed
    public static void Pass() {
        extest.log(LogStatus.PASS, "Test case Passed");
    }
}

