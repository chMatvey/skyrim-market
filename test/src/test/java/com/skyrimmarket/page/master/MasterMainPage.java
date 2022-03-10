package com.skyrimmarket.page.master;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MasterMainPage {

    protected ChromeDriver driver;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/a")
    public WebElement logoButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/div/a[1]")
    public  WebElement ordersButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/div/a[2]")
    public  WebElement analyticsButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/div/a[3]")
    public  WebElement employeesButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/div/a[4]")
    public  WebElement studentsButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/div/a[5]")
    public  WebElement createEmployeeButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[2]/div[1]")
    public  WebElement usernameButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div/div/div/button")
    public  WebElement logoutButton;

    public MasterMainPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
