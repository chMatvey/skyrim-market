package com.skyrimmarket.page.master;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class MasterAnalyticPage extends MasterMainPage{

    protected ChromeDriver driver;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/div/app-analytic/app-toolbar/div/div[1]/div/a[1]")
    public WebElement ordersButton;

    public MasterAnalyticPage(ChromeDriver driver) {
        super(driver);
    }
}
