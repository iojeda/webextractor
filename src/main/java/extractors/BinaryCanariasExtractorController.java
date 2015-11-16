package extractors;

import main.Article;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ismael Ojeda Perez on 15/11/2015.
 */
public class BinaryCanariasExtractorController {

    public void start() {
        // The Firefox driver supports javascript
        WebDriver browser = new FirefoxDriver();

        // Use implicit timeouts (if no element is found when search is done it will wait)
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // Go to the web page
        browser.get("http://www.binarycanarias.com");

        // www.binarycanarias.com uses frames we need to switch to the desired frame to click on login icon
        browser.switchTo().frame("Derecho");
        browser.switchTo().frame("Permanente");

        // Wait till web is loaded
        while (browser.findElement(By.id("SesionTXT")).getText().equals("ESPERE...")) {
        }

        // Save main window handler and launch login windows
        String mainwinHandler = browser.getWindowHandle();
        browser.findElement(By.id("SesionTXT")).click();

        // Switch to new window opene
        for (String winHandle : browser.getWindowHandles()) {
            browser.switchTo().window(winHandle);
        }

        // Login to web
        browser.findElement(By.name("Usuario")).sendKeys("7430");
        browser.findElement(By.name("Clave")).sendKeys("blj2qI");
        browser.findElement(By.id("aLP")).click();

        // Close login window
        browser.close();
        browser.switchTo().window(mainwinHandler);
        browser.switchTo().frame("Derecho");
        browser.switchTo().frame("Permanente");

        // Open Purchase Window
        browser.findElement(By.id("Compras")).click();

        // Switch to new window opened
        for (String winHandle : browser.getWindowHandles()) {
            browser.switchTo().window(winHandle);
        }
        // Frame navigation
        browser.switchTo().frame("Izquierdo");
        browser.switchTo().frame("Noticias");

        // Article extraction loop
        List<WebElement> linksMainWindow = browser.findElements(By.tagName("a"));

        for (WebElement link1 : linksMainWindow
                ) {
            link1.click();
            List<WebElement> linksSecondaryWindow = browser.findElements(By.tagName("a"));
            // Moves back link and 1st to the end of the list
            linksSecondaryWindow.add(linksSecondaryWindow.get(0));
            linksSecondaryWindow.remove(0);
            //linksSecondaryWindow.add(linksSecondaryWindow.get(0));
            //linksSecondaryWindow.remove(0);

            for (WebElement link2 : linksSecondaryWindow
                    ) {
                link2.click();
                browser.switchTo().defaultContent(); // Switch to the top frame
                browser.switchTo().frame("Derecho");
                browser.switchTo().frame("Almacen");
                browser.switchTo().frame("Articulos");
                getArticles(browser);
            }
        }


    }

    private List<Article> getArticles(WebDriver browser) {
        browser.switchTo().frame("idTAB1");

        browser.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        List<Article> articles = new ArrayList<Article>();

        WebElement table = browser.findElement(By.id("ListaArticulos"));
        List<WebElement> tr_collection = table.findElements(By.tagName("tr"));

        for (WebElement trElement : tr_collection) {
            List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
            String group = "";

            if (td_collection.size() < 7) {// grupo
                group = trElement.findElement(By.className("ModoImagenTITCAB")).getText();
            } else {// new article
                Article article = new Article(
                        getText(td_collection.get(0)),
                        getImage(td_collection.get(1)),
                        getText(td_collection.get(2)),
                        getText(td_collection.get(3)),
                        getText(td_collection.get(4)),
                        getText(td_collection.get(5)),
                        getText(td_collection.get(6)),
                        group
                );
                articles.add(article);
                System.out.println(article);
            }
        }

        System.out.println("Terminao lista de articuloss");
        browser.switchTo().defaultContent(); // Switch to the top frame to find and close idTAB1
        browser.switchTo().frame("Derecho");
        browser.switchTo().frame("Almacen");
        browser.switchTo().frame("Articulos");
        browser.findElement(By.id("Cerrar1")).click();
        return articles;
    }

    private String getImage(WebElement webElement) {
        if (webElement.findElements(By.tagName("a")).size() > 0) {
            return webElement.findElement(By.tagName("a")).getAttribute("onclick");
        }
        return "";
    }

    private String getText(WebElement webElement) {
        return webElement.getText();
    }

}
