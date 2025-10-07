package tests;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import base.BaseTest;

public class LoginAgency extends BaseTest {
	
	//mvn exec:exec -Dexec.executable=npx -Dexec.args="playwright codegen https://test-b2b.infinitisoftware.net/"
	
	@Test
  public void test() {
      page.navigate("https://test-b2b.infinitisoftware.net/BDF49C3C3882102FC017FFB661108C63A836D065888A4093994398CC55C2EA2F/428821350E9691491F616B754CD8315FB86D797AB35D843479E732EF90665324");
      page.getByText("Email id").click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email id")).fill("syedagency01@yopmail.com");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email id")).press("Tab");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("Infi@123");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Click here to login")).click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("E").setExact(true)).click();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" My profile")).click();
      page.locator("#maindiv").getByText("Logout").click();
    }
  }