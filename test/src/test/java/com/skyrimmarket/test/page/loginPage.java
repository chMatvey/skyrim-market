package com.skyrimmarket.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage {
    @FindBy(id = "username")
    public WebElement usernameInput;
}
