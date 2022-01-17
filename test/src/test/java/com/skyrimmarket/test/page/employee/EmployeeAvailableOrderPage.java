package com.skyrimmarket.test.page.employee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class EmployeeAvailableOrderPage extends EmployeeMainPage{

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-employee/div/app-available-order/div/div/button[1]")
    public WebElement closeButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-employee/div/app-available-order/div/div/button[2]")
    public WebElement assignToMeButton;

    public EmployeeAvailableOrderPage(ChromeDriver driver){
        super(driver);
    }

}
