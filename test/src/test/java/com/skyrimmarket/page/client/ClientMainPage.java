package com.skyrimmarket.page.client;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientMainPage {

    ChromeDriver driver;

    @FindBy(xpath = "//*[@id=\"close-popup\"]")
    public static WebElement closePopup;

    @FindBy(xpath = "//*[@id=\"close-popup\"]")
    public WebElement exitNotification;

    @FindBy(xpath = "//*[@id=\"create_order\"]")
    public  WebElement makeOrderButton;

    @FindBy(xpath = "//*[@id=\"my_orders\"]")
    public  WebElement myOrdersButton;

    @FindBy(xpath = "//*[@id=\"username\"]")
    public  WebElement usernameButton;

    @FindBy(xpath = "//*[@id=\"logout\"]")
    public  WebElement logoutButton;

    public ClientMainPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
