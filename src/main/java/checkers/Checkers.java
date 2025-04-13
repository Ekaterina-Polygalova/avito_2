package checkers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.ErrorResponse;
import model.ItemInfo;
import model.StatisticResponse;

import static org.junit.jupiter.api.Assertions.*;

public class Checkers {

    @Step("Проверка статистики объявления")
    public void checkGetStatistic(StatisticResponse[] statisticResponse, Integer expectedContacts, Integer expectedLikes, Integer expectedViewCount) {
        assertAll(
                () -> assertEquals(expectedContacts, statisticResponse[0].getContacts()),
                () -> assertEquals(expectedLikes, statisticResponse[0].getLikes()),
                () -> assertEquals(expectedViewCount, statisticResponse[0].getViewCount())
        );
    }

    @Step("Проверка тела ответа: Bad Request")
    public void checkBadRequest(ErrorResponse errorResponse, String errorMessage) {
        assertAll(
                () -> assertEquals(errorMessage, errorResponse.getResult().getMessage()),
                () -> assertEquals("400", errorResponse.getStatus())
        );
    }

    @Step("Проверка тела ответа: Not Found")
    public void checkNotFound(ErrorResponse errorResponse, String errorMessage) {
        assertAll(
                () -> assertEquals(errorMessage, errorResponse.getResult().getMessage()),
                () -> assertEquals("404", errorResponse.getStatus())
        );
    }

    @Step("Получение информации об объявлении по itemId")
    public void checkItemInfoById(ItemInfo[] itemInfo, String expectedId, String expectedName, Integer expectedPrise, Integer expectedSellerId, Integer expectedContacts, Integer expectedLikes, Integer expectedViewCount) {
        assertAll(
                () -> assertNotNull(itemInfo[0].getCreatedAt()),
                () -> assertEquals(expectedId, itemInfo[0].getId()),
                () -> assertEquals(expectedName, itemInfo[0].getName()),
                () -> assertEquals(expectedPrise, itemInfo[0].getPrice()),
                () -> assertEquals(expectedSellerId, itemInfo[0].getSellerId()),
                () -> assertEquals(expectedContacts, itemInfo[0].getStatistics().getContacts()),
                () -> assertEquals(expectedLikes, itemInfo[0].getStatistics().getLikes()),
                () -> assertEquals(expectedViewCount, itemInfo[0].getStatistics().getViewCount())
        );
    }

    @Step("Получение информации об объявлениях по sellerId")
    public void checkItemInfoBySellerId(ItemInfo[] itemInfo) {
        assertAll(
                () -> assertNotNull(itemInfo[0].getCreatedAt()),
                () -> assertNotNull(itemInfo[0].getId()),
                () -> assertNotNull(itemInfo[0].getName()),
                () -> assertNotNull(itemInfo[0].getPrice()),
                () -> assertNotNull(itemInfo[0].getSellerId()),
                () -> assertNotNull(itemInfo[0].getStatistics().getContacts()),
                () -> assertNotNull(itemInfo[0].getStatistics().getLikes()),
                () -> assertNotNull(itemInfo[0].getStatistics().getViewCount()),
                () -> assertNotNull(itemInfo[1].getCreatedAt()),
                () -> assertNotNull(itemInfo[1].getId()),
                () -> assertNotNull(itemInfo[1].getName()),
                () -> assertNotNull(itemInfo[1].getPrice()),
                () -> assertNotNull(itemInfo[1].getSellerId()),
                () -> assertNotNull(itemInfo[1].getStatistics().getContacts()),
                () -> assertNotNull(itemInfo[1].getStatistics().getLikes()),
                () -> assertNotNull(itemInfo[1].getStatistics().getViewCount())
        );
    }

    @Step("Получение информации об объявлении по идентификатору продавца, у которого нет созданных объявлений")
    public void checkItemInfoBySellerIdWithoutItems(Response response) {
        assertEquals("[]\n", response.getBody().asString());
    }

    @Step("Проверка статуса созданного объявления")
    public void checkCreateItemBody(Response response) {
        assertTrue(response.getBody().asString().contains("Сохранили объявление - "));
    }
}