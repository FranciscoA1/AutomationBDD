package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class ResultadosPage extends BasePage {

    private final By tituloResultadosBusqueda = By.xpath("//*[@id=\"app\"]/div/div[3]/h2");
    private final By listaCanciones = By.xpath("//*[@id=\"app\"]/div/div[3]/table/tbody/tr/td[1]");
    private final By listaFavoritos = By.xpath("//*[@id=\"app\"]/div/div[4]/table/tbody/tr/td[1]");

    private final By totalAlbunes = By.xpath("//*[@id=\"app\"]/div/div[2]/p[1]");
    private final By totalCanciones = By.xpath("//*[@id=\"app\"]/div/div[2]/p[2]");
    private final By buttonAgregarFavorito = By.xpath("//*[@id=\"app\"]/div/div[3]/table/tbody/tr[1]/td[6]/button");

    @Override
    public void waitPageLoad() {
        waitPage(tituloResultadosBusqueda, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("Resultados de Búsqueda", find(tituloResultadosBusqueda).getText()),
                () -> Assertions.assertTrue(find(totalAlbunes).isDisplayed()),
                () -> Assertions.assertTrue(find(totalCanciones).isDisplayed())
        );
    }


    public void agregarCancionAFavoritos() {
        final var totalFavoritas = findAll(listaFavoritos).size();
        Logs.debug("Total de canciones favoritas: %s", totalFavoritas);

        find(buttonAgregarFavorito).click();


        final var newTotalFavoritas = findAll(listaFavoritos).size();
        Logs.debug("Total de canciones favoritas: %s", newTotalFavoritas);

        Assertions.assertAll(
                () -> Assertions.assertTrue(newTotalFavoritas > totalFavoritas)
        );
    }

    public void validarTotalCanciones() {
        final var total =find(totalCanciones).getText();
        final var totalNum = total.replaceAll("[^0-9]", "");
        Logs.debug("Total de canciones: %s", totalNum);

        Assertions.assertAll(
                () -> Assertions.assertTrue(Integer.parseInt(totalNum) <= 25)
        );
    }


}
