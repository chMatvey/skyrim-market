package com.skyrimmarket.page.employee;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class EmployeeAvailableOrderPage extends EmployeeMainPage{

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-employee/div/app-available-order/div/div/button[1]")
    public WebElement closeButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-employee/div/app-available-order/div/div/button[2]")
    public WebElement assignToMeButton;

    public EmployeeAvailableOrderPage(ChromeDriver driver){
        super(driver);
    }

}
