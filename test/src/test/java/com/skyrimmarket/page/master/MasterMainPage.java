package com.skyrimmarket.page.master;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MasterMainPage {

    protected ChromeDriver driver;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/a")
    public WebElement logoButton;

    @FindBy(xpath = "//*[@id=\"orders\"]")
    public  WebElement ordersButton;

    @FindBy(xpath = "//*[@id=\"analytic\"]")
    public  WebElement analyticsButton;

    @FindBy(xpath = "//*[@id=\"employees\"]")
    public  WebElement employeesButton;

    @FindBy(xpath = "//*[@id=\"students\"]")
    public  WebElement studentsButton;

    @FindBy(xpath = "//*[@id=\"create_employee\"]")
    public  WebElement createEmployeeButton;

    @FindBy(xpath = "//*[@id=\"username\"]")
    public  WebElement usernameButton;

    @FindBy(xpath = "//*[@id=\"close-popup\"]")
    public WebElement notificationExit;

    @FindBy(xpath = "//*[@id=\"logout\"]")
    public  WebElement logoutButton;

    public MasterMainPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
