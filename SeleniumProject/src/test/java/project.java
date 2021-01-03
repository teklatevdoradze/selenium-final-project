import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Random;

public class project {
    WebDriver driver;

        //= new HtmlUnitDriver(true);
    /*@BeforeClass
    public void setUp() {
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
    }*/
        @BeforeTest
        @Parameters ("crossbrowser")
        public void setUp(String crossbrowser)  {
            if(crossbrowser.equals("chrome")){
                // System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            else if (crossbrowser.equals("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
        }


    @Test
    public void projectTest() {
        driver.get("http://automationpractice.com/index.php");
        Actions act = new Actions(driver);
        WebElement women = driver.findElement(By.cssSelector("a[title='Women']"));
        act.moveToElement(women).perform();

        WebElement shirts = driver.findElement(By.cssSelector("a[title='T-shirts']"));
        shirts.click();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 7);
        WebElement pic = driver.findElement(By.xpath("//img[@class='replace-2x img-responsive' and @title='Faded Short Sleeve T-shirts']"));
        act.moveToElement(pic).perform();
        WebElement quickview = driver.findElement(By.className("quick-view"));
        wait.until(ExpectedConditions.visibilityOf(quickview));
        quickview.click();

        try {
            WebElement quickviewframe = driver.findElement(By.cssSelector("iframe[id^='fancybox-frame']"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(quickviewframe));
        } catch (NoSuchElementException e) {
            System.out.println("No such element");
        }

        WebElement img1 = driver.findElement(By.id("thumbnail_1"));
        act.moveToElement(img1).perform();
        WebElement img2 = driver.findElement(By.id("thumbnail_2"));
        act.moveToElement(img2).perform();
        WebElement img3 = driver.findElement(By.id("thumbnail_3"));
        act.moveToElement(img3).perform();
        WebElement img4 = driver.findElement(By.id("thumbnail_4"));
        act.moveToElement(img4).perform();

        WebElement slct = driver.findElement(By.cssSelector("div#uniform-group_1"));
        slct.click();
        WebElement m = driver.findElement(By.cssSelector("option[value='2']"));
        m.click();
        WebElement plus = driver.findElement(By.cssSelector("i.icon-plus"));
        plus.click();
        WebElement addToCart = driver.findElement(By.cssSelector("button[type='submit']"));
        addToCart.click();
        driver.switchTo().defaultContent();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='layer_cart']"))));
        try {
            WebElement cntnue = driver.findElement(By.xpath("//div[@id='layer_cart']//span[@title='Continue shopping']"));
            wait.until(ExpectedConditions.visibilityOf(cntnue));
            //cntnue.click();
            js.executeScript("arguments[0].click()", cntnue);
        } catch (ElementNotInteractableException e) {
            System.out.println("try harder");
        }
        js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
        WebElement home = driver.findElement(By.xpath("//i[@class='icon-home']"));

        home.click();

        /*WebElement dresses = driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/a"));
       act.moveToElement(dresses).perform();
        WebElement casuals = driver.findElement(By.cssSelector("//*[@id='lock_top_menu']/ul/li[2]/ul/li[1]/a"));
        casuals.click();*/
        List<WebElement> dresses = driver.findElements(By.xpath("//*[@id='block_top_menu']/ul/li"));

        for (WebElement button : dresses) {
            if (button.getText().equals("DRESSES")) {
                act.moveToElement(button).perform();
                List<WebElement> kabebi = driver.findElements(By.xpath("//*[@id='block_top_menu']/ul/li/following::ul/li"));
                for (WebElement option : kabebi) {
                    if (option.getText().equals("CASUAL DRESSES")) {
                        option.click();
                        break;
                    }
                }
                break;
            }
        }
        js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector("div[class$='product-image-container']")));
        act.moveToElement(driver.findElement(By.cssSelector("div[class$='product-image-container']")));
        act.click(driver.findElement(By.xpath("//a[@title='Add to cart']/span[contains(text(), 'Add to cart')]")));
        act.perform();
        WebElement cntnue2 = driver.findElement(By.xpath("//div[@id='layer_cart']//span[@title='Continue shopping']"));
        wait.until(ExpectedConditions.visibilityOf(cntnue2));
        cntnue2.click();


        WebElement cart = driver.findElement(By.cssSelector("span[class*='ajax_cart_product_txt_']"));
        act.moveToElement(cart).perform();

        WebElement checkout = driver.findElement(By.cssSelector("a#button_order_cart"));
        wait.until(ExpectedConditions.visibilityOf(checkout));
        checkout.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("order-detail-content"))));

        act.moveToElement(driver.findElement(By.xpath("//*[@id='order-detail-content']"))).perform();




        String Price;
        Price = driver.findElement(By.id("total_price")).getText();
        // Clicking 'Proceed To Checkout'
        js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector("p[class^='cart_navigation']")));
        List<WebElement> proceedButtons = driver.findElements(By.xpath("//a[@title='Proceed to checkout']"));
        for (WebElement proceedButton : proceedButtons) {
            if (proceedButton.isDisplayed()) {
                proceedButton.click();
            }
        }

        WebElement createacc = driver.findElement(By.cssSelector("form[id^='create-account']"));
        wait.until(ExpectedConditions.visibilityOf(createacc));
        js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector("form[id^='create-account']")));

        Random tochangemail = new Random() ;
        String newmail = "ttevdoradze"+tochangemail.nextInt()+"@gmail.com";
        driver.findElement(By.id("email_create")).sendKeys(newmail);

        driver.findElement(By.id("SubmitCreate")).click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("uniform-id_gender2")));


        driver.findElement(By.id("id_gender2")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys(" Tekla");
        driver.findElement(By.id("customer_lastname")).sendKeys("Tevdoradze");
        driver.findElement(By.id("company")).sendKeys("smthn");
        driver.findElement(By.cssSelector("input#passwd")).sendKeys("passwd");

        Select days = new Select(driver.findElement(By.id("days")));
        Select months = new Select(driver.findElement(By.id("months")));
        Select years = new Select(driver.findElement(By.id("years")));

        days.selectByValue("31");
        months.selectByValue("12");
        years.selectByValue("2001");

        driver.findElement(By.id("address1")).sendKeys("just");
        driver.findElement(By.id("city")).sendKeys("fill");

        Select state = new Select(driver.findElement(By.id("id_state")));
        state.selectByValue("1");

        driver.findElement(By.id("postcode")).sendKeys("00000");

        Select country = new Select(driver.findElement(By.id("id_country")));
        country.selectByValue("21");

        driver.findElement(By.id("phone_mobile")).sendKeys("4815162342");

        WebElement submitacc = driver.findElement(By.id("submitAccount"));
        submitacc.click();
    //}



        driver.findElement(By.cssSelector("button[type='submit'][name='processAddress']")).click();

        driver.findElement(By.cssSelector("button[type='submit'][name='processCarrier']")).click();

        WebElement mustagree = driver.findElement(By.xpath("//p[contains(text(),'must agree')]"));

        if (mustagree.isDisplayed()) {
            js.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("div[class^='fancybox-overlay']")));
        }
        driver.findElement(By.id("cgv")).click();
        driver.findElement(By.cssSelector("button[type='submit'][name='processCarrier']")).click();



        WebElement trueprice = driver.findElement(By.id("total_price"));
        act.moveToElement(trueprice).perform();
        if (Price.equals(trueprice.getText())) {
                    System.out.println("match");
        } else {
            System.out.println(Price);
            System.out.println(trueprice.getText());
        }
        WebElement check = driver.findElement(By.cssSelector("a[class='cheque']"));
        act.moveToElement(check).click().perform();
        WebElement proceed =driver.findElement(By.xpath("//button/span[contains(text(), 'confirm')]")) ;
        act.moveToElement(proceed).click().perform();

        WebElement csdlink = driver.findElement(By.partialLinkText("customer service"));

        //wait.until(ExpectedConditions.visibilityOf(csdlink));
        act.moveToElement(csdlink).click().perform();

        Select heading = new Select(driver.findElement(By.id("id_contact")));
        //Select reference = new Select(driver.findElement(By.cssSelector("select.form-control")));

        heading.selectByValue("1");
       // reference.selectByValue( "272871");
        //Select product = new Select(driver.findElement(By.id("272871_order_products")));
        //product.selectByValue("3");

        driver.findElement(By.id("message")).sendKeys("FINALLY");


        File fileToUpload = new File("src/test/fileToUpload");
        String filePath = fileToUpload.getAbsolutePath();
        driver.findElement(By.id("fileUpload")).sendKeys(filePath);


        WebElement finalSubmit = driver.findElement(By.id("submitMessage"));
        act.moveToElement(finalSubmit).click().perform();


    }
}





