/**
 * Created by Ismael Ojeda Perez on 08/11/2015.
 */


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
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

            //Wait till web is loaded
            while (browser.findElement(By.id("SesionTXT")).getText().equals("ESPERE...")){}

            //Save main window handler and launch login windows
            String mainwinHandler = browser.getWindowHandle();
            browser.findElement(By.id("SesionTXT")).click();

            // Switch to new window opened
            for(String winHandle : browser.getWindowHandles()) {
                browser.switchTo().window(winHandle);
            }

            //Login to web
            browser.findElement(By.name("Usuario")).sendKeys("7430");
            browser.findElement(By.name("Clave")).sendKeys("blj2qI");
            browser.findElement(By.id("aLP")).click();

            //Close login window
            browser.close();
            browser.switchTo().window(mainwinHandler);
            browser.switchTo().frame("Derecho");
            browser.switchTo().frame("Permanente");

            //Open Purchase Window
            browser.findElement(By.id("Compras")).click();

            //Switch to new window opened
            for(String winHandle : browser.getWindowHandles()) {
                browser.switchTo().window(winHandle);
            }

            browser.switchTo().frame("Izquierdo");
            browser.switchTo().frame("Noticias");
            browser.findElement(By.id("link1")).click();
            browser.findElement(By.id("link2")).click();

            //Change to extract Articles table
//            List<WebElement> elements = browser.findElements(By.xpath())
//
//            for(WebElement frame: frames){
//                System.out.println(frame.getText());
//            }

            browser.switchTo().defaultContent(); // Switch to the top frame
            browser.switchTo().frame("Derecho");
            browser.switchTo().frame("Almacen");
            browser.switchTo().frame("Articulos");
            browser.switchTo().frame("idTAB1");

            WebElement table = browser.findElement(By.id("ListaArticulos"));
            List<WebElement> tr_collection=table.findElements(By.xpath("id('ListaArticulos')/tbody/tr"));

            //almacenamiento de Articulos
            List<List<String>> filas = new ArrayList<List<String>>();
            List<String> columnas = new ArrayList<String>();

            //System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
            //int row_num,col_num;
            //row_num=1;
            for(WebElement trElement : tr_collection) {
                List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
                //System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
                //col_num=1;
                for(WebElement tdElement : td_collection)
                {
                    //System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
                    //col_num++;
                    columnas.add(tdElement.getText());

                }
                filas.add(columnas);
                //row_num++;
            }
            System.out.println("Terminao");
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
