/**
 * Created by Ismael Ojeda Perez on 08/11/2015.
 */


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class webextractor {

        public static void main(String[] args) throws Exception {
            // The Firefox driver supports javascript
            WebDriver browser = new FirefoxDriver();
            Actions action = new Actions(browser);

            //Use implicit timeouts (if no element is found when search is done it will wait)
            browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            // Go to the web page
            browser.get("http://www.binarycanarias.com");

            //www.binarycanarias.com uses frames we need to switch to the desired frame to click on login icon
            browser.switchTo().frame("Derecho");
            browser.switchTo().frame("Permanente");

            //Launch Login window
            while (browser.findElement(By.id("SesionTXT")).getText().equals("ESPERE...")){}

            System.out.println(browser.findElement(By.id("SesionTXT")).getText());
            browser.findElement(By.id("SesionTXT")).click();

            // Switch to new window opened
            for(String winHandle : browser.getWindowHandles()) {
                browser.switchTo().window(winHandle);
            }

            //Login to web
            browser.findElement(By.name("Usuario")).sendKeys("usuario");
            browser.findElement(By.name("Clave")).sendKeys("clave");
            browser.findElement(By.id("aLP")).click();

            //Close login window
            browser.close();
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
