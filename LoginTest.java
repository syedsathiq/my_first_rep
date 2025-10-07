package tests;

import base.BaseTest;
import pages.LoginPage;
import utilis.ExcelUtilis;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @DataProvider(name = "userData")
    public Object[][] getData() {
        String excelPath = "C:\\Users\\syedsathiq.k\\eclipse-workspace\\New automation\\PlawrightJavaProject\\testdata\\ctusers.xls"; // keep file in project folder
        return ExcelUtilis.getUserData(excelPath, "Admin data");
    }
    
    @DataProvider(name = "agencyData")
    public Object[][] getAgencyData() {
        String excelPath = "C:\\Users\\syedsathiq.k\\eclipse-workspace\\New automation\\PlawrightJavaProject\\testdata\\ctusers.xls";
        return ExcelUtilis.getUserData(excelPath, "Agency data");
    }

    @Test(dataProvider = "userData")
    public void testLoginAndProfileScreenshot(String username, String password, String role) throws InterruptedException {
        loginPage = new LoginPage(page);
        loginPage.navigate("https://test-b2b.infinitisoftware.net/");
        loginPage.login(username, password, "Admin data");
        loginPage.openProfileAndTakeScreenshot(role, "Admin data");
        loginPage.logout("Admin data");
        
    }
    
    @Test(dataProvider = "agencyData")
    public void testAgencyLogin(String username, String password, String role) throws InterruptedException {
        loginPage = new LoginPage(page);
        loginPage.navigate("https://test-b2b.infinitisoftware.net/");
        loginPage.login(username, password, "Agency data");  // if using Sheet2
        loginPage.openProfileAndTakeScreenshot(role, "Agency data");
        loginPage.logout("Agency data");
    }
}
