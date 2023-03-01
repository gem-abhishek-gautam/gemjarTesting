package org.example.stepDefinition;

import com.gemini.generic.exception.GemException;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.stepDefinition.Locators.Locators;
import org.openqa.selenium.WebElement;

import java.sql.Date;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StepDefinition {

    @Before
    public void setup() throws GemException {
        DriverManager.setUpBrowser();
    }
    @Given("Launch browser to open MIS")
    public void launch() {
        DriverAction.waitUntilElementAppear(Locators.login_form,10);

    }


    @Then("Enter login credentials where username is {string} and {string}")
    public void enterLoginCredentials(String user, String pswd) throws InterruptedException {
        DriverAction.typeText(Locators.username,user);
        DriverAction.typeText(Locators.pswd,pswd);
        Thread.sleep(3000);
        if(DriverAction.isExist(Locators.login_button)){
            DriverAction.click(Locators.login_button);
        }
    }

    @And("Verify if MIS is launched and login")
    public void verifyIfMISIsLaunched() {
        DriverAction.waitUntilElementAppear(Locators.calendar,20);
        System.out.println("Calendar exits>>>>>>>>>>> "+DriverAction.isExist(Locators.calendar));
        if(DriverAction.isExist(Locators.calendar))
        {
            GemTestReporter.addTestStep("Validation","Login to MIS", STATUS.PASS,DriverAction.takeSnapShot());
        } else GemTestReporter.addTestStep("Validation","Login to MIS", STATUS.FAIL,DriverAction.takeSnapShot());

    }

    @Then("Verify if date {string} is shown")
    public void verifyIfDateShown(String date) throws InterruptedException {

        DriverAction.waitUntilElementAppear(Locators.calendar,20);
        List<String> months = List.of("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
        if(DriverAction.isExist(Locators.calendar)){
             String month = date.split(" ")[1];
             System.out.println("month given "+month);
//             String year = date.split(" ")[1];
             String curr_month = (DriverAction.getElementText(Locators.curr_month_year)).split(" ")[0];
             String curr_year = (DriverAction.getElementText(Locators.curr_month_year)).split(" ")[1];
            if(months.indexOf(month)>months.indexOf(curr_month)){   // future month
                int clicks = months.indexOf(curr_month) - months.indexOf(month);
                System.out.println("Clicks are "+clicks);
                for(int i=0;i<clicks;i++){
                    if(DriverAction.isExist(Locators.next_month))
                        DriverAction.click(Locators.next_month);
                }
            }
            else {
                int clicks = months.indexOf(curr_month) - months.indexOf(month);
                System.out.println("Clicks are "+clicks);
                for(int i=0;i<clicks;i++){
                    if(DriverAction.isExist(Locators.prev_month))
                        DriverAction.click(Locators.prev_month);
                }
            }
            Thread.sleep(3000);
            if((DriverAction.getElementText(Locators.curr_month_year)).contains(month)){
                GemTestReporter.addTestStep("Month Visibility","Month visible", STATUS.PASS,DriverAction.takeSnapShot());

            } else GemTestReporter.addTestStep("Month Visibility","Month not visible", STATUS.FAIL,DriverAction.takeSnapShot());


        } else GemTestReporter.addTestStep("Calendar Visibility","Calendar not visible", STATUS.FAIL,DriverAction.takeSnapShot());

    }
}
