package com.skyrimmarket.page.master;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class MasterCreateEmployeePage extends MasterMainPage{


    @FindBy(xpath = "//*[@id=\"username-input\"]")
    public WebElement usernameInput;

    @FindBy(xpath = "//*[@id=\"password\"]")
    public WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"confirmPassword\"]")
    public WebElement confirmPasswordInput;

    @FindBy(xpath = "//*[@id=\"create\"]")
    public WebElement createButton;


    public MasterCreateEmployeePage(ChromeDriver driver) {
        super(driver);
    }
}
