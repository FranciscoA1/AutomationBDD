package utilities;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    private final boolean runServer = System.getenv("JOB_NAME") != null;

    private enum Browser {
        CHROME,
        FIREFOX,
        EDGE,
        SAFARI
    }

    public void buildDriver() {
        if (runServer) {
            buildRemoteDriver();
        } else {
            buildLocalDriver();
        }
    }

    public void killDriver() {
        Logs.debug("Killing Driver...");
        new WebDriverProvider().get().quit();
    }
    private void buildLocalDriver() {
        var headlessProperty = System.getProperty("headless") != null;
        var browserProperty = System.getProperty("browser");
        if (browserProperty == null) {
            browserProperty = "CHROME";
        }

        final var browser = Browser.valueOf(browserProperty.toUpperCase());
        Logs.debug("Setting Driver to %s", browser);
        Logs.debug("Headless Mode? %s", headlessProperty);

        final var driver = switch (browser) {
            case CHROME -> {
                final var chromeOptions = new ChromeOptions();
                if (headlessProperty) {
                    chromeOptions.addArguments("--headless=new");
                }
                yield new ChromeDriver(chromeOptions);
            }
            case FIREFOX -> {
                final var firefoxOptions = new FirefoxOptions();
                if (headlessProperty) {
                    firefoxOptions.addArguments("--headless=new");
                }
                yield new FirefoxDriver();
            }
            case EDGE -> {
                final var edgeOptions = new EdgeOptions();
                if (headlessProperty) {
                    edgeOptions.addArguments("--headless=new");
                }
                yield new EdgeDriver();
            }
            case SAFARI -> new SafariDriver();
        };

        Logs.debug("Maximizing Window...");
        driver.manage().window().maximize();

        Logs.debug("Deleting Cookies...");
        driver.manage().deleteAllCookies();

        Logs.debug("Setting Driver to webdriver provider...");
        new WebDriverProvider().set(driver);
    }

    private void buildRemoteDriver() {

    }
}
