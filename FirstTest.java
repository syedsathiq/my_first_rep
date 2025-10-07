package tests;

import org.testng.annotations.Test;

import base.BaseTest;

public class FirstTest extends BaseTest{
	
	@Test
	public void verifyTitle() {
		
		page.navigate("https://test-b2b.infinitisoftware.net/");
		//optional
		if(page.isVisible("buttin:has-text('Accept all')")) {
			page.click("button:has-text('Accept all')");
		}
		System.out.println("This page title is : " + page.title());
	}
	
//	public static void main(String[] args) throws InterruptedException {
//		
//		
//		
//		try(Playwright playwright = Playwright.create()){
//			
//			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//			
//			Page page = browser.newPage();
//			
//			page.navigate("https://google.com");
//			System.out.println("The Page title is :"+page.title());
//			
//			Thread.sleep(5000);
//			browser.close();			
//		}
//	}

}
