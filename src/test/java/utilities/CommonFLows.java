package utilities;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pages.TestPage;

public class CommonFLows {
    private WebDriver getDriver() {
        return new WebDriverProvider().get();

    }

    public void loginCookies() {
        Logs.debug("Asignando Cookie para el login...");
        getDriver().get("https://www.saucedemo.com/404");
        final var cookie = new Cookie("session-username", "standard_user");
        getDriver().manage().addCookie(cookie);
    }

    public void goToUrl() {
        //Quitar comentario para hacer login con cookies
        //loginCookies();
        Logs.info("Navegando a la url...");
        getDriver().get("https://www.saucedemo.com/");

        //Esperamnos que se cargando la pagina (Llamando al constructor)
        new TestPage().waitPageLoad();
    }



}
