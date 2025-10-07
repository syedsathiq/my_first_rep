package utilis;

import com.microsoft.playwright.Page;

public class WaitUtils {

    /**
     * Safely clicks on a locator. 
     * Waits up to the given timeout (ms). 
     * Returns true if successful, false if skipped.
     */
    public static boolean safeClick(Page page, String locator, int timeout) {
        try {
            page.waitForSelector(locator, new Page.WaitForSelectorOptions().setTimeout(timeout));
            page.click(locator);
            System.out.println("✅ Clicked: " + locator);
            return true;
        } catch (Exception e) {
            System.out.println("❌ Skipped click (not found within " + timeout + "ms): " + locator);
            return false;
        }
    }

    /**
     * Safely fills text into an input field. 
     * Waits up to the given timeout (ms). 
     * Returns true if successful, false if skipped.
     */
    public static boolean safeFill(Page page, String locator, String text, int timeout) {
        try {
            page.waitForSelector(locator, new Page.WaitForSelectorOptions().setTimeout(timeout));
            page.fill(locator, text);
            System.out.println("✅ Filled text into: " + locator + " → " + text);
            return true;
        } catch (Exception e) {
            System.out.println("❌ Skipped fill (not found within " + timeout + "ms): " + locator);
            return false;
        }
    }

    /**
     * Safely types text character by character into an input field.
     * Returns true if successful, false if skipped.
     */
    public static boolean safeType(Page page, String locator, String text, int timeout) {
        try {
            page.waitForSelector(locator, new Page.WaitForSelectorOptions().setTimeout(timeout));
            page.type(locator, text);
            System.out.println("✅ Typed text into: " + locator + " → " + text);
            return true;
        } catch (Exception e) {
            System.out.println("❌ Skipped type (not found within " + timeout + "ms): " + locator);
            return false;
        }
    }

    /**
     * Safely retrieves text from an element.
     * Returns the text if found, empty string if skipped.
     */
    public static String safeGetText(Page page, String locator, int timeout) {
        try {
            page.waitForSelector(locator, new Page.WaitForSelectorOptions().setTimeout(timeout));
            String text = page.textContent(locator);
            System.out.println("✅ Retrieved text from: " + locator + " → " + text);
            return text != null ? text : "";
        } catch (Exception e) {
            System.out.println("❌ Skipped getText (not found within " + timeout + "ms): " + locator);
            return "";
        }
    }
}
