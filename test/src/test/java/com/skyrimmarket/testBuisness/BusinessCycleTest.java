package com.skyrimmarket.testBuisness;

import com.skyrimmarket.page.LoginPage;
import com.skyrimmarket.page.client.ClientMainPage;
import com.skyrimmarket.page.client.ClientMakeOrderPage;
import com.skyrimmarket.page.client.ClientMyOrdersPage;
import com.skyrimmarket.page.client.ClientPayOrderPage;
import com.skyrimmarket.page.employee.EmployeeAvailableOrderPage;
import com.skyrimmarket.page.employee.EmployeeMyOrderPage;
import com.skyrimmarket.page.employee.EmployeeOrdersPage;
import com.skyrimmarket.page.master.MasterCreateEmployeePage;
import com.skyrimmarket.page.master.MasterMainPage;
import com.skyrimmarket.page.master.MasterOrderPage;
import com.skyrimmarket.page.master.MasterOrdersPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BusinessCycleTest {
    ChromeDriver driver = null;

    @BeforeEach
    void setUp() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:4200/");
    }

    @AfterEach
    void driverDown() {
        driver.close();
    }

    void login(ChromeDriver driver, String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginButton.click();
    }

    @Test
    void completeOrderCycle() throws InterruptedException {
        login(driver, "client", "client");
        ClientMainPage clientMainPage = new ClientMainPage(driver);
        TimeUnit.MILLISECONDS.sleep(500);
        clientMainPage.closePopup.click();
        TimeUnit.MILLISECONDS.sleep(500);
        clientMainPage.makeOrderButton.click();
        ClientMakeOrderPage clientMakeOrderPage = new ClientMakeOrderPage(driver);
        clientMakeOrderPage.selectServiceButton.click();
        TimeUnit.MILLISECONDS.sleep(500);
        clientMakeOrderPage.selectOrderType(1).click();
        clientMakeOrderPage.personEdit.sendKeys("??????????????");
        clientMakeOrderPage.robbedPickpoketingTitleEdit.click();
        TimeUnit.MILLISECONDS.sleep(200);
        clientMakeOrderPage.getRobbedTitle(2).click();
        clientMakeOrderPage.itemPickpoketingEdit.click();
        TimeUnit.MILLISECONDS.sleep(200);
        clientMakeOrderPage.getItem(4).click();
        clientMakeOrderPage.descriptionPickpoketingTextEdit.sendKeys("?? ?????????????? ?????????? ?? ????????????????");
        clientMakeOrderPage.createOrderButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        clientMakeOrderPage.exitNotification.click();
        TimeUnit.MILLISECONDS.sleep(200);
        clientMakeOrderPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        clientMakeOrderPage.logoutButton.click();

        TimeUnit.MILLISECONDS.sleep(200);
        login(driver, "master", "master");
        MasterOrdersPage masterOrdersPage = new MasterOrdersPage(driver);
        masterOrdersPage.ordersButton.click();
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrdersPage.getOrder(1).click();
        MasterOrderPage masterOrderPage = new MasterOrderPage(driver);
        masterOrderPage.commentText.sendKeys("???????????????? ????????????, ?????????????? ???????????????? ?????????? ???? '??????'");
        masterOrderPage.commentButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        masterOrderPage.notificationExit.click();
        TimeUnit.MILLISECONDS.sleep(100);
        masterOrderPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        masterOrderPage.logoutButton.click();

        TimeUnit.MILLISECONDS.sleep(200);
        login(driver, "client", "client");
        ClientMyOrdersPage clientMyOrdersPage = new ClientMyOrdersPage(driver);
        TimeUnit.MILLISECONDS.sleep(500);
        ClientMyOrdersPage.closePopup.click();
        TimeUnit.MILLISECONDS.sleep(500);
        clientMyOrdersPage.myOrdersButton.click();
        clientMyOrdersPage.chooseFilter(5);
        clientMyOrdersPage.singleOrder.click();
        String orderType = clientMakeOrderPage.orderType.getText();
        String needOrderType = "?????????? ???? ??????????????";
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

        login(driver, "master", "master");
        masterOrdersPage.ordersButton.click();
        masterOrdersPage.getOrder(1).click();
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

        login(driver, "client", "client");
        TimeUnit.MILLISECONDS.sleep(500);
        ClientMyOrdersPage.closePopup.click();
        TimeUnit.MILLISECONDS.sleep(500);
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


        login(driver, "employee", "employee");
        employeeOrdersPage.myOrdersButton.click();
        TimeUnit.MILLISECONDS.sleep(100);
        employeeOrdersPage.getOrder(1, 1).click();
        TimeUnit.MILLISECONDS.sleep(100);
        EmployeeMyOrderPage employeeMyOrderPage = new EmployeeMyOrderPage(driver);
        TimeUnit.MILLISECONDS.sleep(100);
        employeeMyOrderPage.wheresItemEdit.sendKeys("?????? ???????????????? ?? ???????????? ?????????????? ?????????????? ??????????????????");
        TimeUnit.MILLISECONDS.sleep(100);
        employeeMyOrderPage.commentEdit.sendKeys("????????????????");
        TimeUnit.MILLISECONDS.sleep(100);
        employeeMyOrderPage.completeButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        employeeMyOrderPage.exitNotification.click();
        TimeUnit.MILLISECONDS.sleep(100);
        employeeMyOrderPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        employeeMyOrderPage.logoutButton.click();

    }

    @Test
    void createEmployeeAndLogin() throws InterruptedException{
        login(driver, "master", "master");
        TimeUnit.MILLISECONDS.sleep(200);
        MasterMainPage masterMainPage = new MasterMainPage(driver);
        TimeUnit.MILLISECONDS.sleep(200);
        masterMainPage.createEmployeeButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        MasterCreateEmployeePage masterCreateEmployeePage = new MasterCreateEmployeePage(driver);
        TimeUnit.MILLISECONDS.sleep(200);
        masterCreateEmployeePage.usernameInput.sendKeys("employee");
        masterCreateEmployeePage.passwordInput.sendKeys("test");
        masterCreateEmployeePage.confirmPasswordInput.sendKeys("test");
        masterCreateEmployeePage.createButton.click();
        TimeUnit.MILLISECONDS.sleep(200);

        masterCreateEmployeePage.notificationExit.click();
        masterCreateEmployeePage.usernameInput.sendKeys(Keys.LEFT_CONTROL + "A");
        masterCreateEmployeePage.usernameInput.sendKeys("testEmployee");
        masterCreateEmployeePage.createButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        masterCreateEmployeePage.notificationExit.click();
        TimeUnit.MILLISECONDS.sleep(200);
        masterCreateEmployeePage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        masterCreateEmployeePage.logoutButton.click();
        TimeUnit.MILLISECONDS.sleep(200);

        login(driver, "testEmployee", "test");
        TimeUnit.MILLISECONDS.sleep(200);
        masterCreateEmployeePage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        masterCreateEmployeePage.logoutButton.click();
        TimeUnit.MILLISECONDS.sleep(200);



    }


}
