/**
 * Created by Ismael Ojeda Perez on 08/11/2015.
 */


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class webextractor {


        public static void main(String[] args) throws Exception {
            // The Firefox driver supports javascript
            WebDriver browser = new FirefoxDriver();

            // Go to the web page
            browser.get("https://www.binarycanarias.com");
/*
            // Steps for login access
            browser.findElement(By.id("user")).sendKeys("user");
            browser.findElement(By.id("passwd")).sendKeys("pass");
            browser.findElement(By.id("entrar")).click();

            String mainWindow = browser.getWindowHandle();
            browser.get("https://url");

            Set<String> handles = browser.getWindowHandles();

            for (String handle : handles) {
                if (!handle.equals(mainWindow)) {
                    browser.switchTo().window(handle);
                    break;
                }
            }

            browser.findElement(By.name("form1")).click();

            /*
            // Sleep until the div we want is visible or 5 seconds is over
            long end = System.currentTimeMillis() + 5000;
            while (System.currentTimeMillis() < end) {
                WebElement resultsDiv = driver.findElement(By.className("gssb_e"));

                // If results have been returned, the results are displayed in a drop down.
                if (resultsDiv.isDisplayed()) {
                    break;
                }
            }

            // And now list the suggestions
            List<WebElement> allSuggestions = driver.findElements(By.xpath("//td[@class='gssb_a gbqfsf']"));

            for (WebElement suggestion : allSuggestions) {
                System.out.println(suggestion.getText());
            }
*/
            browser.wait();
            browser.quit();
        }

}
