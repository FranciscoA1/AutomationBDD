package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;


public class TestPage extends BasePage {
    private final By headerContainer = By.cssSelector("#root > div > div.login_logo");

    @Override

    public void waitPageLoad() {
        waitPage(headerContainer, this.getClass().getSimpleName());
    }

    @Override

    public void verifyPage() {
        Logs.info("Verificando la UI de la pagina");

        Assertions.assertAll(
                () -> Assertions.assertTrue(find(headerContainer).isDisplayed())
        );


    }


    public void logOut() {
        find(By.className("bm-burger-button")).click();
        find(By.className("bm-cross-button")).click();
    }
}
