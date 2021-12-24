package com.skyrimmarket.test;

import net.jodah.failsafe.internal.util.Assert;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OrderFullfillment {
    ChromeDriver driver;

    @BeforeEach
    void setUp() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://skyrim-market.herokuapp.com");
    }

    @AfterEach
    void driverDown(){
        //driver.close();
    }

    void login(ChromeDriver driver, String username, String password){
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("login__form__submit")).click();
    }

    void waitUntil(By byLocator){
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(driver -> driver.findElement(byLocator));
    }

    WebElement findByXpath(String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    int clientCreateOrder(ChromeDriver driver){
        login(driver, "client", "client");
        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[1]/div/a[1]"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[1]/div/a[1]")).click();
        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[1]/div/mat-select/div/div[1]"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[1]/div/mat-select/div/div[1]")).click();
        // Выбор Pickpocketing
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[1]/span"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[1]/span")).click();
        // Entering name of robbed person
        waitUntil(By.xpath("//*[@id=\"person\"]"));
        driver.findElement(By.xpath("//*[@id=\"person\"]")).sendKeys("Balgruuf the Greater");
        // Entering title of robbed person: Jarl
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-pickpocketing-order-form/form/div[2]/mat-select/div/div[1]")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[3]/span"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[3]/span")).click();
        // Entering stealed item: dragon sword
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-pickpocketing-order-form/form/div[3]/app-search-select/mat-select/div/div[1]")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[1]/span/ngx-mat-select-search/div/input"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[1]/span/ngx-mat-select-search/div/input")).sendKeys("Dragon Sword");
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[3]/span")).click();
        // Entering description
        driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("Hidden in the chest of the court magician's room.");
        // Press create order
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[2]/button[2]")).click();

        // Notification
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        String link = driver.getCurrentUrl();
        int orderId = Integer.valueOf(link.split("/")[link.split("/").length - 1]);
        // Logout
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[2]/div[1]")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/div/div/button"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/button")).click();
        return orderId;
    }

    void masterDeclineOrder(ChromeDriver driver, int orderId){
        // Login
        login(driver, "master", "master");
        // Choose Orders
        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/div/a"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/div/a")).click();
        // Select all orders
        waitUntil(By.xpath("//div[@class=\"order-list__order form-control ng-star-inserted\"]"));

        int real_numba = -1;
        int num = driver.findElements(By.xpath("//div[@class=\"order-list__order form-control ng-star-inserted\"]")).size();
        for (int i = 0; i < num; ++i){
            WebElement el = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/div/app-orders-for-master/div/app-order-list/div/div["
                    + String.valueOf(i + 1) + "]/div/span[1]"));
            int orId = Integer.valueOf(el.getText().split(" ")[1]);
            if (orId == orderId){
                real_numba = i + 1;
            }
        }

        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/div/app-orders-for-master/div/app-order-list/div/div[" +
                String.valueOf(real_numba) + "]/div")).click();
        waitUntil(By.xpath("//*[@id=\"comment\"]"));
        driver.findElement(By.xpath("//*[@id=\"comment\"]")).sendKeys("There no dragon sword in this place");
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/div/app-confirm-order/div/form/div[3]/button[3]")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[2]/div[1]")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/div/div/button"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/button")).click();
    }

    void clientChangeOrder(ChromeDriver driver, int orderId){
        login(driver, "client", "client");
        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[1]/div/a[2]"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[1]/div/a[2]")).click();

        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[1]/mat-button-toggle-group/mat-button-toggle[5]/button"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[1]/mat-button-toggle-group/mat-button-toggle[5]/button")).click();

        waitUntil(By.xpath("//div[@class=\"client-orders__order-list ng-star-inserted\"]"));

        int real_numba = -1;
        int num = driver.findElements(By.xpath("//div[@class=\"order-description\"]")).size();
        if (num >= 2) {
            for (int i = 0; i < num; ++i) {
                WebElement el = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[2]/div["
                        + String.valueOf(i + 1) + "]/div/span[1]"));
                String text = el.getText();
                int orId = Integer.valueOf(el.getText().split(" ")[1]);
                if (orId == orderId) {
                    real_numba = i + 1;
                }
            }
            driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[2]/div["
                    + String.valueOf(real_numba) + "]/div")).click();
        }
        else{
            driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[2]/div/div")).click();
        }
        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-pickpocketing-order-form/form/div[3]/app-search-select/mat-select/div/div[1]"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-pickpocketing-order-form/form/div[3]/app-search-select/mat-select/div/div[1]")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[6]/span"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[6]/span")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div[1]/div[2]/button[3]")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[2]/div[1]")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/div/div/button"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/button")).click();
    }

    void masterAcceptOrder(ChromeDriver driver, int orderId){
        login(driver, "master", "master");
        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/div/a"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/div/a")).click();
        // Select all orders
        waitUntil(By.xpath("//div[@class=\"order-list__order form-control ng-star-inserted\"]"));

        int real_numba = -1;
        int num = driver.findElements(By.xpath("//div[@class=\"order-description\"]")).size();
        for (int i = 0; i < num; ++i){
            WebElement el = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/div/app-orders-for-master/div/app-order-list/div/div["
                    + String.valueOf(i + 1) + "]/div/span[1]"));
            int orId = Integer.valueOf(el.getText().split(" ")[1]);
            if (orId == orderId){
                real_numba = i + 1;
            }
        }

        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/div/app-orders-for-master/div/app-order-list/div/div[" +
                String.valueOf(real_numba) + "]/div")).click();

        waitUntil(By.xpath("//*[@id=\"price\"]"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.findElement(By.xpath("//*[@id=\"price\"]")).sendKeys("1000");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/div/app-confirm-order/div/form/div[3]/button[4]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[2]/div[1]")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/div/div/button"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/button")).click();
    }

    void clientPayOrder(ChromeDriver driver, int orderId){
        login(driver, "client", "client");
        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[1]/div/a[2]"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[1]/div/a[2]")).click();

        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[1]/mat-button-toggle-group/mat-button-toggle[4]/button"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[1]/mat-button-toggle-group/mat-button-toggle[4]/button")).click();

        waitUntil(By.xpath("//div[@class=\"client-orders__order-list ng-star-inserted\"]"));

        int real_numba = -1;
        int num = driver.findElements(By.xpath("//div[@class=\"order-description\"]")).size();
        if (num >= 2) {
            for (int i = 0; i < num; ++i) {
                WebElement el = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[2]/div["
                        + String.valueOf(i + 1) + "]/div/span[1]"));
                String text = el.getText();
                int orId = Integer.valueOf(el.getText().split(" ")[1]);
                if (orId == orderId) {
                    real_numba = i + 1;
                }
            }
            driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[2]/div["
                    + String.valueOf(real_numba) + "]/div")).click();
        }
        else{
            driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[2]/div/div")).click();
        }
        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div[2]/app-pay-form/form/div[2]/mat-select/div/div[1]/span"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div[2]/app-pay-form/form/div[2]/mat-select/div/div[1]/span")).click();

        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option/span"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option/span")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-order/div/div[2]/app-pay-form/form/div[3]/button")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[2]/div[1]")).click();
        waitUntil(By.xpath("/html/body/div[2]/div[2]/div/div/div/button"));
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/button")).click();
    }

    void employeeAssignOrder(ChromeDriver driver, int orderId){
        login(driver, "employee", "employee");
        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-employee/app-toolbar/div/div[1]/div/a[2]"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-employee/app-toolbar/div/div[1]/div/a[2]")).click();


        int real_numba = -1;
        int num = driver.findElements(By.xpath("//div[@class=\"order-description\"]")).size();
        if (num >= 2) {
            for (int i = 0; i < num; ++i) {
                WebElement el = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-employee/div/app-available-orders/div/app-order-list/div/div["
                        + String.valueOf(i + 1) + "]/div/span[1]"));
                int orId = Integer.valueOf(el.getText().split(" ")[1]);
                if (orId == orderId) {
                    real_numba = i + 1;
                }
            }
            driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-employee/div/app-available-orders/div/app-order-list/div/div[" +
                    String.valueOf(real_numba) + "]/div")).click();
        }
        else{
            driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-employee/div/app-available-orders/div/app-order-list/div/div/div")).click();
        }

        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-employee/div/app-available-order/div/div/button[2]"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-employee/div/app-available-order/div/div/button[2]")).click();
        waitUntil(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img"));
        driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img")).click();
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-employee/div/app-my-order/div/form/div[1]/input")).sendKeys("In the chest on hostage, room 3");
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-employee/div/app-my-order/div/form/div[2]/input")).sendKeys("Good order");
        driver.findElement(By.xpath("//html/body/app-root/div/div[2]/app-employee/div/app-my-order/div/form/div[3]/button[3]")).click();
        waitUntil(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img"));
        driver.findElement(By.xpath("/html/body/div/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img")).click();

        waitUntil(By.xpath("/html/body/app-root/div/div[2]/app-employee/app-toolbar/div/div[2]/div[1]"));
        driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-employee/app-toolbar/div/div[2]/div[1]")).click();
        waitUntil(By.xpath("/html/body/div/div[2]/div/div/div/button"));
        driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/button")).click();

    }

    @Test
    void FulfillmentOrder(){
        int orderId = 3;
        orderId = clientCreateOrder(driver);
        masterDeclineOrder(driver, orderId);
        clientChangeOrder(driver, orderId);
        masterAcceptOrder(driver, orderId);
        clientPayOrder(driver, orderId);
        employeeAssignOrder(driver, orderId);
    }

}
