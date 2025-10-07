package base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class BaseTest {
	protected static Playwright playwright;
	protected static Browser browser;
	protected BrowserContext context;
	protected static Page page;

	@BeforeClass
	public void beforeClass() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	}

	@BeforeMethod
	public void setUp() {
		context = browser.newContext();
		page = context.newPage();
	}

	@AfterMethod
	public void tearDown() {
		context.close();
	}

	@AfterClass
	public void afterClass() {
		browser.close();
		playwright.close();
	}
}
