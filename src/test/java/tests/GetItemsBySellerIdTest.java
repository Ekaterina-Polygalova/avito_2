package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import model.ErrorResponse;
import model.ItemInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static constants.ErrorConstants.INVALID_SELLER_ID_ERROR_MESSAGE;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static specs.Specification.responseSpec;

@DisplayName("Проверки получение всех объявлений по идентификатору продавца")
public class GetItemsBySellerIdTest extends BaseTest {

    String sellerId = "124628";
    String sellerIdWithoutItems = "519411";

    @Test
    @Description("Получение всех объявлений по идентификатору продавца")
    @DisplayName("Получение всех объявлений по идентификатору продавца")
    public void getItemBySellerId() {
        Response response = requests.getItemsBySellerId(sellerId);
        responseSpec(SC_OK);
        ItemInfo[] itemInfo = response.as(ItemInfo[].class);
        checkers.checkItemInfoBySellerId(itemInfo);
    }

    @Test
    @Description("Получение объявлений по идентификатору продавца без объявлений")
    @DisplayName("Получение объявлений по идентификатору продавца без объявлений")
    public void getItemBySellerIdWithoutItems() {
        Response response = requests.getItemsBySellerId(sellerIdWithoutItems);
        responseSpec(SC_OK);
        checkers.checkItemInfoBySellerIdWithoutItems(response);
    }

    @ParameterizedTest
    @Description("Получение всех объявлений по идентификатору продавца с невалидными данными")
    @DisplayName("Получение всех объявлений по идентификатору продавца с невалидными данными")
    @ValueSource(strings = {"Объявление", "Item", "-_.~"})
    public void getItemByInvalidSellerId(String invalidId) {
        Response response = requests.getItemsBySellerId(invalidId);
        responseSpec(SC_BAD_REQUEST);
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        checkers.checkBadRequest(errorResponse, INVALID_SELLER_ID_ERROR_MESSAGE);
    }
}