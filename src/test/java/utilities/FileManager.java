package utilities;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileManager {
    private static final String screenshotPath = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\";
    private static final String pageSourcePath = System.getProperty("user.dir") + "\\src\\test\\resources\\pageSourceStructure\\";

    public static void getScreenshot(String screenshotName) {

        Logs.debug("Taking Screenshot: %s", screenshotName);
        String sanitizedScreenshotName = screenshotName.replaceAll("\\s+", "_");
        Logs.debug("Taking Screenshot: %s", sanitizedScreenshotName);

        final var screenshotFile = ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.FILE);
        final var path = String.format("%s%s.png", screenshotPath, sanitizedScreenshotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Error: %s", ioException.getLocalizedMessage());
        }
    }

    public static void getPageSource(String fileName) {
        Logs.debug("Taking Page Source:");
        final var path = String.format("%s%s.html", pageSourcePath, fileName);

        try {
            final var file = new File(path);

            Logs.debug("Creating father directory if it doesn't exist");
            if(file.getParentFile().mkdirs()) {
                final var fileWriter = new FileWriter(file);
                final var pageSource = new WebDriverProvider().get().getPageSource();
                fileWriter.write(Jsoup.parse(pageSource).toString());
                fileWriter.close();
            }
        } catch (IOException ioException) {
            Logs.error("Error: %s", ioException.getLocalizedMessage());
        }
    }

    public static void deleteScreenshotPrevious() {
        try {
            Logs.debug("Deleting Previous Screenshot");
            FileUtils.deleteDirectory(new File(screenshotPath));
            FileUtils.deleteDirectory(new File(pageSourcePath));
        } catch (IOException ioException) {
            Logs.error("Error: %s", ioException.getLocalizedMessage());
        }

    }

    public static void attachScreenshot(Scenario scenario) {
        final var screenhotFile = ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.BYTES);

        scenario.attach(screenhotFile, "image/png", scenario.getName());
    }

    public static void attachPageSource(Scenario scenario) {
        final var pageSource = new WebDriverProvider().get().getPageSource();
        final var parsedPageSource = Jsoup.parse(pageSource).toString();

        scenario.attach(parsedPageSource, "text/plain", scenario.getName());
    }



}



