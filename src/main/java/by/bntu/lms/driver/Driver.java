package by.bntu.lms.driver;

import by.bntu.lms.properties.ProjectProperties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Driver {
    private static ThreadLocal<WebDriver> webDriver;
    public static final int IMPLICITY_WAIT_TIME = 15;

    private static WebDriver getWebDriver() {
        return webDriver == null ? null : webDriver.get();
    }

    public static void setWebDriver(WebDriver webDriver) {
        Driver.webDriver = new ThreadLocal<>();
        Driver.webDriver.set(webDriver);
    }

    public static WebDriver getWebDriverInstance() {
        if (getWebDriver() != null) return getWebDriver();

        String browserType = System.getProperty("browserType") == null ?
                ProjectProperties.getInstance().getProperty("browserType") : System.getProperty("browserType");

        switch (browserType.toUpperCase()) {
            case "CHROME": {
                System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
                webDriver.set(new ChromeDriver());
                break;
            }
            case "REMOTE_CHROME": {
                String hub = System.getProperty("hub") == null ?
                        ProjectProperties.getInstance().getProperty("hub") : System.getProperty("hub");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setJavascriptEnabled(true);
                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(Platform.WINDOWS);
                RemoteWebDriver rwd;
                try {
                    rwd = new RemoteWebDriver(new URL(hub), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    throw new IllegalArgumentException("Incorrect URL provided!");
                }
                rwd.setLogLevel(Level.OFF);
                webDriver.set(rwd);
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown web driver specified: " + browserType);
        }
        getWebDriver().manage().timeouts().implicitlyWait(IMPLICITY_WAIT_TIME, TimeUnit.SECONDS);
        return getWebDriver();
    }
}