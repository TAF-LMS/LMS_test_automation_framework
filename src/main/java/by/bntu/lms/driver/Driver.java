package by.bntu.lms.driver;

import by.bntu.lms.properties.ProjectProperties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Driver {
    private static WebDriver webDriver;

    private void Driver() {
        throw new AssertionError("No instances of this class should exist");
    }

    public static WebDriver getWebDriverInstance(String name) throws Exception {
        //if (webDriver != null) return webDriver;

        switch (name.toUpperCase()) {
            case "CHROME": {
                System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
            }
            case "REMOTE_CHROME": {
                String hub = System.getProperty("hub") == null ?
                        ProjectProperties.getInstance().getProperty("hub") : System.getProperty("hub");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setJavascriptEnabled(true);
                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(Platform.WINDOWS);
                RemoteWebDriver rwd = new RemoteWebDriver(new URL(hub), capabilities);
                rwd.setLogLevel(Level.OFF);
                webDriver = rwd;
                break;
            }
            default:
                throw new Exception("Unknown web driver specified: " + name);
        }
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }
}
