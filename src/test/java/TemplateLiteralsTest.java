import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.JavascriptExecutor;

public class TemplateLiteralsTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp() {
        String browserName = BrowserUtils.getWebDriverName();

        // debugging statement
        System.out.println(browserName);

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("headless");
                webDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-headless");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                webDriver = new EdgeDriver(edgeOptions);
                break;

            case "ie":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.addCommandSwitches("-headless");
                webDriver = new InternetExplorerDriver(ieOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    
        File file = new File("src/main/java/index.html");
        String path = "file://" + file.getAbsolutePath();
        webDriver.get(path);
    }


    @Test
    public void testExercise1() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String  actual = (String) jsExecutor.executeScript("return exercise1(arguments[0]);", "Bobby");
        String expected = "hey bobby,";
        Assertions.assertEquals(expected, actual.toLowerCase());
    }

    @Test
    public void testExercise1Again() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String  actual = (String) jsExecutor.executeScript("return exercise1(arguments[0]);", "Charlie");
        String expected = "hey charlie,";
        Assertions.assertEquals(expected, actual.toLowerCase());
    }

    @Test
    public void testExercise2() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String  actual = (String) jsExecutor.executeScript("return exercise2(arguments[0], arguments[1], arguments[2]);", "grand", "read books", "fabulous");
        
        Assertions.assertTrue(actual.toLowerCase().trim().contains("i just wanted to take a moment to tell you how grand you are!"));
        Assertions.assertTrue(actual.toLowerCase().trim().contains("remember that time we read books together?"));
        Assertions.assertTrue(actual.toLowerCase().trim().contains("that was fabulous! we need to do it again soon."));
    }

    @Test
    public void testExercise2Again() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String  actual = (String) jsExecutor.executeScript("return exercise2(arguments[0], arguments[1], arguments[2]);", "pretty", "drove motorcycles", "chill");
        
        Assertions.assertTrue(actual.toLowerCase().trim().contains("i just wanted to take a moment to tell you how pretty you are!"));
        Assertions.assertTrue(actual.toLowerCase().trim().contains("remember that time we drove motorcycles together?"));
        Assertions.assertTrue(actual.toLowerCase().trim().contains("that was chill! we need to do it again soon."));
    }

    @Test
    public void testExercise3() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String  actual = (String) jsExecutor.executeScript("return exercise3(arguments[0]);", "fantastic");

        Assertions.assertEquals("i hOpE YoU'Re hAvInG A fAnTaStIc DaY!", actual.trim());
    }

    @Test
    public void testExercise3Again() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String  actual = (String) jsExecutor.executeScript("return exercise3(arguments[0]);", "good");

        Assertions.assertEquals("i hOpE YoU'Re hAvInG A gOoD DaY!", actual.trim());
    }

    @Test
    public void testForTemplateLiterals1() {
        String jsCode = TestingUtils.getContent().toLowerCase();

        Assertions.assertTrue(jsCode.contains("`hey ${"));
        Assertions.assertTrue(jsCode.contains("},`"));
    }

    @Test
    public void testForTemplateLiterals2() {
        String jsCode = TestingUtils.getContent().toLowerCase();

        Assertions.assertTrue(jsCode.contains("`i just wanted to take"));
        Assertions.assertTrue(jsCode.contains("do it again soon.`"));
    }

    @Test
    public void testForTemplateLiterals3() {
        String jsCode = TestingUtils.getContent().toLowerCase();

        Assertions.assertTrue(jsCode.contains("`i hope "));
        Assertions.assertTrue(jsCode.contains("day!`"));
    }
}

class TestingUtils {
    public static String getContent() {
        String content = "";
        try {
            content = Files.readString(Paths.get("./src/main/java/index.js"));
        } catch(IOException e){
            e.printStackTrace();
        }

        System.out.println(content);
        return content;
    }
}


class BrowserUtils {
    public static String getWebDriverName() {
        String[] browsers = { "chrome", "firefox", "edge", "ie" };

        for (String browser : browsers) {
            try {
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        new ChromeDriver().quit();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        new FirefoxDriver().quit();
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        new EdgeDriver().quit();
                        break;
                    case "ie":
                        WebDriverManager.iedriver().setup();
                        new InternetExplorerDriver().quit();
                        break;
                }
                return browser;
            } catch (Exception e) {
                continue;
            }
        }
        return "Unsupported Browser";
    }
}

