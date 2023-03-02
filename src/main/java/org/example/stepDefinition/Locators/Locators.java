package org.example.stepDefinition.Locators;

import org.openqa.selenium.By;

public class Locators {
    public static By login_form = By.xpath("//form[@class='login-form sign-box']");
    public static By username = By.xpath("//input[@placeholder='Username']");
    public static By pswd = By.xpath("//input[@placeholder='Password']");
    public static By login_button = By.xpath("//input[@value='Sign in']");
    public static By calendar = By.xpath("//div[@id='employeeCalendar']");
    public static By prev_month = By.xpath("//button[@aria-label='prev']");
    public static By next_month = By.xpath("//button[@aria-label='next']");
    public static By curr_month_year = By.xpath("//div[@id='employeeCalendar']//div[@class='fc-center']//h2");
    public static By profileName = By.xpath("//div[@id='userName']");
    public static By userCard = By.xpath("//div[@class='widget-user-stat']");

}
