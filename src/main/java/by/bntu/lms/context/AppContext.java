package by.bntu.lms.context;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

@Configuration
public class AppContext {
    @Bean(name="chrome")
    public WebDriver chromeDriver() {
        System.setProperty("webdriver.chrome.driver",
                "./drivers/chromedriver.exe");
        return new ChromeDriver();
    }

    @Bean(name="remotecrome")
    public WebDriver remoteChromeDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled(true);
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.LINUX);
        RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://10.153.30.106:5555/wd/hub"), capabilities);
        rwd.setLogLevel(Level.OFF);
        return rwd;
    }
}
