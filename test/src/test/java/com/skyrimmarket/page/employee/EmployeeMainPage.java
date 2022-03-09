package com.skyrimmarket.page.employee;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeMainPage {

    protected ChromeDriver driver;

    @FindBy(xpath = "//*[@id=\"close-popup\"]")
    public WebElement exitNotification;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-employee/app-toolbar/div/div[1]/div/a[1]")
    public  WebElement myOrdersButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-employee/app-toolbar/div/div[1]/div/a[2]")
    public  WebElement availableOrdersButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-employee/app-toolbar/div/div[1]/div/a[3]")
    public  WebElement completedOrdersButton;

    @FindBy(xpath = "//*[@id=\"username\"]")
    public  WebElement usernameButton;

    @FindBy(xpath = "//*[@id=\"logout\"]")
    public  WebElement logoutButton;

    public EmployeeMainPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
