package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utilis.ExcelUtilis;
import utilis.WaitUtils;

public class Ageingmodule_TC01 extends BaseTest {

    @DataProvider(name = "adminData")
    public Object[][] getAdminData() {
        String excelPath = "C:\\Users\\syedsathiq.k\\eclipse-workspace\\New automation\\PlawrightJavaProject\\testdata\\ctusers.xls";
        return ExcelUtilis.getUserData(excelPath, "Admin data");
    }

    @Test(dataProvider = "adminData")
    public void testAdminFlow(String username, String password, String role) {

        LoginPage loginPage = new LoginPage(page);

        // 1️⃣ Navigate
        page.navigate("https://test-b2b.infinitisoftware.net/BDF49C3C3882102FC017FFB661108C63A836D065888A4093994398CC55C2EA2F/428821350E9691491F616B754CD8315FB86D797AB35D843479E732EF90665324");

        // 2️⃣ Login
        loginPage.login(username, password, "Admin data");

        // 3️⃣ Ageing module steps (safe clicks / fills)
        WaitUtils.safeClick(page, "//span[normalize-space(text())='My Wallet']", 10000);

        WaitUtils.safeClick(page, "//li[@title='Agency']", 10000);
        WaitUtils.safeClick(page, "//a[text()='View agency']", 10000);

        WaitUtils.safeClick(page, "//input[@name='Agency name']", 10000);
        WaitUtils.safeFill(page, "//input[@name='Agency name']", "1039", 10000);
        WaitUtils.safeClick(page, "//*[text()='SYED SATHIQ(TN)SBA1039']", 10000);

        WaitUtils.safeClick(page, "//button[text()='']", 10000);
        WaitUtils.safeClick(page, "//div[contains(@class,'cls-body-cell link')]//em[1]", 10000);
        WaitUtils.safeClick(page, "//tab[text()='Account & Credit Details']", 10000);
        WaitUtils.safeClick(page, "//tab[text()='Controls & Components']", 10000);
        WaitUtils.safeClick(page, "//*[text()='Back to list']", 10000);
        WaitUtils.safeClick(page, "//button[text()=' Back to booking']", 10000);

        // 4️⃣ Take screenshot
        loginPage.openProfileAndTakeScreenshot(role, "Admin data");

        // 5️⃣ Logout
        loginPage.logout("Admin data");
    }
}
