package com.skyrimmarket.test.page.employee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class EmployeeMyOrderPage extends EmployeeMainPage{

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-employee/div/app-my-order/div/form/div[3]/button[1]")
    public WebElement closeButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-employee/div/app-my-order/div/form/div[3]/button[2]")
    public WebElement declineButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-employee/div/app-my-order/div/form/div[3]/button[3]")
    public WebElement completeButton;

    @FindBy(xpath = "//*[@id=\"droppoint\"]")
    public WebElement wheresItemEdit;


    @FindBy(xpath = "//*[@id=\"comment\"]")
    public WebElement commentEdit;

    public EmployeeMyOrderPage(ChromeDriver driver){
        super(driver);
    }

}
