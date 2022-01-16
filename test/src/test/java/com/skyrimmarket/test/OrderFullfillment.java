package com.skyrimmarket.test;

import com.skyrimmarket.test.page.LoginPage;
import com.skyrimmarket.test.page.client.ClientMainPage;
import com.skyrimmarket.test.page.client.ClientMakeOrderPage;
import com.skyrimmarket.test.page.client.ClientMyOrdersPage;
import com.skyrimmarket.test.page.client.ClientPayOrderPage;
import com.skyrimmarket.test.page.employee.EmployeeAvailableOrderPage;
import com.skyrimmarket.test.page.employee.EmployeeMyOrderPage;
import com.skyrimmarket.test.page.employee.EmployeeOrdersPage;
import com.skyrimmarket.test.page.master.MasterOrderPage;
import com.skyrimmarket.test.page.master.MasterOrdersPage;
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
import java.util.concurrent.TimeUnit;
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

    void login(ChromeDriver driver, String username, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginButton.click();
    }

    void openClientOrder(int orderId){
        login(driver, "client", "client");
        ClientMainPage clientMainPage = new ClientMainPage(driver);
        clientMainPage.myOrdersButton.click();
        ClientMyOrdersPage clientMyOrdersPage = new ClientMyOrdersPage(driver);
        int orderNumber = -1;
        int num = clientMyOrdersPage.getOrdersCount();
        if (num >= 2) {
            for (int i = 0; i < num; ++i) {
                WebElement el = clientMyOrdersPage.getOrder(i + 1);
                String text = el.getText();
                int orId = Integer.valueOf(el.getText().split(" ")[1]);
                if (orId == orderId) {
                    orderNumber = i + 1;
                }
            }
            clientMyOrdersPage.getOrder(orderNumber).click();
        }
        else{
            clientMyOrdersPage.singleOrder.click();
        }
    }

    @Test
    void createOrder() throws InterruptedException {
        int order_type_id = 1;                                      // 1: Pickpocketing, 2: Sweep, 3: Forgery
        login(driver, "client", "client");
        ClientMainPage clientMainPage = new ClientMainPage(driver);
        clientMainPage.makeOrderButton.click();
        ClientMakeOrderPage clientMakeOrderPage = new ClientMakeOrderPage(driver);
        clientMakeOrderPage.selectServiceButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        clientMakeOrderPage.selectOrderType(order_type_id).click();
        switch (order_type_id){
            case 1:
                clientMakeOrderPage.personEdit.sendKeys("Balgruuf the Greater");
                clientMakeOrderPage.robbedPickpoketingTitleEdit.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.getRobbedTitle(2).click();
                clientMakeOrderPage.itemPickpoketingEdit.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.getItem(4).click();
                clientMakeOrderPage.descriptionPickpoketingTextEdit.sendKeys("Hidden in the chest of the court magician's room.");
                clientMakeOrderPage.createOrderButton.click();
                TimeUnit.MILLISECONDS.sleep(1000);
                clientMakeOrderPage.exitNotification.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.usernameButton.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.logoutButton.click();
                break;
            case 2:
                clientMakeOrderPage.addressEdit.sendKeys("Vaitran, Mainhall, Grand Wizard's room");
                clientMakeOrderPage.robbedSweepTitleEdit.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.getRobbedTitle(2).click();
                clientMakeOrderPage.itemSweepEdit.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.getItem(4).click();
                clientMakeOrderPage.descriptionSweepTextEdit.sendKeys("Hidden in the chest of the court magician's room.");
                clientMakeOrderPage.createOrderButton.click();
                TimeUnit.MILLISECONDS.sleep(1000);
                clientMakeOrderPage.exitNotification.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.usernameButton.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.logoutButton.click();
                break;
            case 3:
                clientMakeOrderPage.personEdit.sendKeys("Balgruuf the Greater");
                clientMakeOrderPage.addressEdit.sendKeys("Vaitran, Mainhall, Grand Wizard's room");
                clientMakeOrderPage.itemForgeryEdit.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.getItem(4).click();
                clientMakeOrderPage.descriptionForgeryTextEdit.sendKeys("Hidden in the chest of the court magician's room.");
                clientMakeOrderPage.createOrderButton.click();
                TimeUnit.MILLISECONDS.sleep(1000);
                clientMakeOrderPage.exitNotification.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.usernameButton.click();
                TimeUnit.MILLISECONDS.sleep(200);
                clientMakeOrderPage.logoutButton.click();
                break;
        }
    }

    @Test
    void masterCommentOrder() throws InterruptedException{
        login(driver, "master", "master");
        MasterOrdersPage masterOrdersPage = new MasterOrdersPage(driver);
        masterOrdersPage.ordersButton.click();
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrdersPage.getOrder(1).click();
        MasterOrderPage masterOrderPage = new MasterOrderPage(driver);
        masterOrderPage.commentText.sendKeys("Wrong, change title");
        masterOrderPage.commentButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        masterOrderPage.notificationExit.click();
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrderPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        masterOrderPage.logoutButton.click();
    }

    @Test
    void clientChangeOrder() throws  InterruptedException{
        login(driver, "client", "client");
        ClientMyOrdersPage clientMyOrdersPage = new ClientMyOrdersPage(driver);
        clientMyOrdersPage.myOrdersButton.click();
        clientMyOrdersPage.chooseFilter(5);
        clientMyOrdersPage.singleOrder.click();
        ClientMakeOrderPage clientMakeOrderPage = new ClientMakeOrderPage(driver);
        String orderType = clientMakeOrderPage.orderType.getText();
        String needOrderType = "Forgery";
        if (!orderType.equals(needOrderType)) {
            System.out.println(clientMakeOrderPage.orderType.getText());
            clientMakeOrderPage.robbedSweepTitleEdit.click();
            TimeUnit.MILLISECONDS.sleep(200);
            clientMakeOrderPage.getRobbedTitle(1).click();
        }
        clientMakeOrderPage.applyOrderButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        clientMakeOrderPage.exitNotification.click();
        TimeUnit.MILLISECONDS.sleep(100);
        clientMakeOrderPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        clientMakeOrderPage.logoutButton.click();
    }

    @Test
    void masterAcceptOrder() throws InterruptedException{
        login(driver, "master", "master");
        MasterOrdersPage masterOrdersPage = new MasterOrdersPage(driver);
        masterOrdersPage.ordersButton.click();
        masterOrdersPage.getOrder(1).click();
        MasterOrderPage masterOrderPage = new MasterOrderPage(driver);
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrderPage.priceEdit.sendKeys(Keys.LEFT_CONTROL + "A");
        masterOrderPage.priceEdit.sendKeys("500");
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrderPage.approveButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        masterOrderPage.notificationExit.click();
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrderPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        masterOrderPage.logoutButton.click();
    }

    @Test
    void masterAcceptOrderWithEmployee() throws InterruptedException{
        login(driver, "master", "master");
        MasterOrdersPage masterOrdersPage = new MasterOrdersPage(driver);
        masterOrdersPage.ordersButton.click();
        masterOrdersPage.getOrder(1).click();
        MasterOrderPage masterOrderPage = new MasterOrderPage(driver);
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrderPage.chooseContractor(1);
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrderPage.priceEdit.sendKeys(Keys.LEFT_CONTROL + "A");
        masterOrderPage.priceEdit.sendKeys("500");
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrderPage.approveButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        masterOrderPage.notificationExit.click();
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrderPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        masterOrderPage.logoutButton.click();
    }

    @Test
    void clientPayOrder() throws  InterruptedException{
        login(driver, "client", "client");
        ClientMyOrdersPage clientMyOrdersPage = new ClientMyOrdersPage(driver);
        clientMyOrdersPage.myOrdersButton.click();
        clientMyOrdersPage.chooseFilter(4);
        clientMyOrdersPage.singleOrder.click();
        ClientPayOrderPage clientPayOrderPage = new ClientPayOrderPage(driver);
        TimeUnit.MILLISECONDS.sleep(500);
        clientPayOrderPage.payWayComboBox.click();
        TimeUnit.MILLISECONDS.sleep(500);
        clientPayOrderPage.payWayCash.click();
        clientPayOrderPage.applyButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        clientPayOrderPage.exitNotification.click();
        TimeUnit.MILLISECONDS.sleep(100);
        clientPayOrderPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        clientPayOrderPage.logoutButton.click();
    }

    @Test
    void employeeAssignOrder() throws InterruptedException{
        login(driver, "employee", "employee");
        EmployeeOrdersPage employeeOrdersPage = new EmployeeOrdersPage(driver);
        employeeOrdersPage.availableOrdersButton.click();
        TimeUnit.MILLISECONDS.sleep(100);
        employeeOrdersPage.getOrder(1, 2).click();
        TimeUnit.MILLISECONDS.sleep(100);
        EmployeeAvailableOrderPage employeeAvailableOrderPage = new EmployeeAvailableOrderPage(driver);
        employeeAvailableOrderPage.assignToMeButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        employeeAvailableOrderPage.exitNotification.click();
        TimeUnit.MILLISECONDS.sleep(100);
        employeeAvailableOrderPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        employeeAvailableOrderPage.logoutButton.click();
    }

    @Test
    void employeeCompleteOrder() throws InterruptedException{
        login(driver, "employee", "employee");
        EmployeeOrdersPage employeeOrdersPage = new EmployeeOrdersPage(driver);
        employeeOrdersPage.myOrdersButton.click();
        TimeUnit.MILLISECONDS.sleep(100);
        employeeOrdersPage.getOrder(1, 1).click();
        TimeUnit.MILLISECONDS.sleep(100);
        EmployeeMyOrderPage employeeMyOrderPage = new EmployeeMyOrderPage(driver);
        TimeUnit.MILLISECONDS.sleep(100);
        employeeMyOrderPage.wheresItemEdit.sendKeys("Under the bed on the tavern in RiverWood");
        TimeUnit.MILLISECONDS.sleep(100);
        employeeMyOrderPage.commentEdit.sendKeys("Completed");
        TimeUnit.MILLISECONDS.sleep(100);
        employeeMyOrderPage.completeButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        employeeMyOrderPage.exitNotification.click();
        TimeUnit.MILLISECONDS.sleep(100);
        employeeMyOrderPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        employeeMyOrderPage.logoutButton.click();
    }

}
