package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import model.ErrorResponse;
import model.ItemInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static constants.ErrorConstants.*;
import static org.apache.http.HttpStatus.*;
import static specs.Specification.responseSpec;

@DisplayName("Проверки получения информации об объявлении")
public class GetItemTest extends BaseTest {

    String id = "ee17dacd-1677-48be-b021-e1a2e0d3ac67";
    String expectedName = "печенька";
    Integer expectedPrice = 100;
    Integer expectedSellerId = 876509;
    Integer expectedContacts = 6;
    Integer expectedLikes = 60;
    Integer expectedViewCount = 1098;

    @Test
    @Description("Получение информации об объявлении по itemID")
    @DisplayName("Получение информации об объявлении по itemID")
    public void getItemInfoById() {
        Response response = requests.getItem(id);
        responseSpec(SC_OK);
        ItemInfo[] itemInfo = response.as(ItemInfo[].class);
        checkers.checkItemInfoById(itemInfo, id, expectedName, expectedPrice, expectedSellerId, expectedContacts, expectedLikes, expectedViewCount);
    }

    @Test
    @Description("Поиск несуществующего объявления")
    @DisplayName("Поиск несуществующего объявления")
    public void getItemInfoByIdNotFound() {
        String errorMessage = String.format(ITEM_NOT_FOUND_MESSAGE, NOT_EXIST_ID);
        Response response = requests.getItem(NOT_EXIST_ID);
        responseSpec(SC_NOT_FOUND);
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        checkers.checkNotFound(errorResponse, errorMessage);
    }

    @ParameterizedTest
    @Description("Получение объявления по его идентификатору с невалидными данными")
    @DisplayName("Получение объявления по его идентификатору с невалидными данными")
    @ValueSource(strings = {"12345", "Объявление", "Item", "-_.~"})
    public void getItemInfoByInvalidId(String invalidId) {
        String errorMessage = String.format(ITEM_ID_IS_NOT_UUID_MESSAGE, invalidId);
        Response response = requests.getItem(invalidId);
        responseSpec(SC_BAD_REQUEST);
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        checkers.checkBadRequest(errorResponse, errorMessage);
    }
}