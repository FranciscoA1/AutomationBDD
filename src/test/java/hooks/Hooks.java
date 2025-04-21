package hooks;

import io.cucumber.java.*;
import utilities.DriverManager;
import utilities.FileManager;
import utilities.Logs;

public class Hooks {

    private static final DriverManager driverManager = new DriverManager();

    @BeforeAll
    public static void beforeAll() {
        Logs.info("beforeAll sex");
        FileManager.deleteScreenshotPrevious();
    }

    @AfterAll
    public static void afterAll() {
        Logs.info("afterAll");
    }

    @Before
    public static void before(Scenario scenario) {
        Logs.info("before: %s", scenario.getName());
        driverManager.buildDriver();

    }

    @After
    public static void after(Scenario scenario) {
        Logs.info("after: %s, status: %s", scenario.getName(), scenario.getStatus());
        switch (scenario.getStatus()) {
            case SKIPPED, FAILED -> {
                FileManager.getScreenshot(scenario.getName());
                FileManager.getPageSource(scenario.getName());
                FileManager.attachScreenshot(scenario);
                FileManager.attachPageSource(scenario);
            }
        }
        driverManager.killDriver();
    }
}
