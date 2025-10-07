package pages;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Paths;

import com.microsoft.playwright.Page;
import utilis.WaitUtils;

public class LoginPage {
    private Page page;

    // Admin locators
    private String adminUsernameField = "//input[@id='email']";
    private String adminPasswordField = "//input[@id='password']";
    private String adminLoginBtn     = "//button[@aria-label='Click here to login']";
    private String adminProfileIcon  = "//a[@title='Go to profile']//span[1]";
    private String adminLogoutBtn    = "//div[@title='Logout']";

    // Agency locators
    private String agencyUsernameField = "//input[@id='email']";
    private String agencyPasswordField = "//input[@id='password']";
    private String agencyLoginBtn      = "//button[@aria-label='Click here to login']";
    private String agencyProfileIcon   = "//a[@title='Go to profile']//span[1]";
    private String agencyLogoutBtn     = "//div[@title='Logout']";

    // Base screenshots folder
    private final String baseDir = "C:\\Users\\syedsathiq.k\\eclipse-workspace\\New automation\\PlawrightJavaProject\\Screenshots\\";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    // Login Admin or Agency
    public void login(String username, String password, String sheetName) {
        if (sheetName.equalsIgnoreCase("Admin data")) {
            WaitUtils.safeClick(page, adminUsernameField, 10000);
            WaitUtils.safeFill(page, adminUsernameField, username, 10000);

            WaitUtils.safeClick(page, adminPasswordField, 10000);
            WaitUtils.safeFill(page, adminPasswordField, password, 10000);

            WaitUtils.safeClick(page, adminLoginBtn, 10000);

        } else if (sheetName.equalsIgnoreCase("Agency data")) {
            WaitUtils.safeClick(page, agencyUsernameField, 10000);
            WaitUtils.safeFill(page, agencyUsernameField, username, 10000);

            WaitUtils.safeClick(page, agencyPasswordField, 10000);
            WaitUtils.safeFill(page, agencyPasswordField, password, 10000);

            WaitUtils.safeClick(page, agencyLoginBtn, 10000);
        }
        page.waitForLoadState();
    }

    // Take screenshot into a folder named with today's date
    public void openProfileAndTakeScreenshot(String role, String sheetName) {
        if (sheetName.equalsIgnoreCase("Admin data")) {
            WaitUtils.safeClick(page, adminProfileIcon, 10000);
        } else if (sheetName.equalsIgnoreCase("Agency data")) {
            WaitUtils.safeClick(page, agencyProfileIcon, 10000);
        }

        // Create folder with today's date
        String dateFolder = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String folderPath = baseDir + dateFolder;

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Unique screenshot name = role + time
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        String fileName = role + "_" + timestamp + ".png";

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(folderPath, fileName)));
    }

    // Logout 
    public void logout(String sheetName) {
        if (sheetName.equalsIgnoreCase("Admin data")) {
            WaitUtils.safeClick(page, adminLogoutBtn, 10000);
        } else if (sheetName.equalsIgnoreCase("Agency data")) {
            WaitUtils.safeClick(page, agencyLogoutBtn, 10000);
        }
        page.waitForLoadState();
    }
}
