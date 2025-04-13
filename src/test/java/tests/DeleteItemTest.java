package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import model.CreateItemRequest;
import model.ErrorResponse;
import model.StatisticResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static constants.ErrorConstants.*;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.fail;
import static specs.Specification.responseSpec;

@DisplayName("Проверки удаления объявления")
public class DeleteItemTest extends BaseTest {

    StatisticResponse statistic = new StatisticResponse(1, 2, 3);
    CreateItemRequest createItemRequest = new CreateItemRequest(358930, "Донской сфинкс", 20000, statistic);
    String uuidPattern = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
    String uuid;

    @BeforeEach
    public void setup() {
        Response response = requests.createItem(createItemRequest);
        responseSpec(SC_OK);
        checkers.checkCreateItemBody(response);
        String responseBody = response.getBody().asString();
        Pattern pattern = Pattern.compile(uuidPattern);
        Matcher matcher = pattern.matcher(responseBody);
        if (matcher.find()) {
            uuid = matcher.group(0);
        } else {
            fail("UUID не найден в ответе.");
        }
    }

    @Test
    @Description("Удаление объявления по его идентификатору")
    @DisplayName("Удаление объявления по его идентификатору")
    public void deleteItemById() {
        String errorMessage = String.format(ITEM_NOT_FOUND_MESSAGE, uuid);
        requests.deleteItem(uuid);
        responseSpec(SC_OK);
        Response response = requests.getItem(uuid);
        responseSpec(SC_NOT_FOUND);
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        checkers.checkNotFound(errorResponse, errorMessage);
    }

    @Test
    @Description("Проверка повторного удаления объявления")
    @DisplayName("Проверка повторного удаления объявления")
    public void deleteItemTwice() {
        String errorMessage = String.format(ITEM_NOT_FOUND_MESSAGE, uuid);
        requests.deleteItem(uuid);
        responseSpec(SC_OK);
        Response response = requests.deleteItem(uuid);
        responseSpec(SC_NOT_FOUND);
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        checkers.checkNotFound(errorResponse, errorMessage);
    }

    @ParameterizedTest
    @Description("Удаление объявления с невалидными данными")
    @DisplayName("Удаление объявления с невалидными данными")
    @ValueSource(strings = {"12345", "Объявление", "Item", "-_.~"})
    public void deleteItemByInvalidId(String invalidId) {
        Response response = requests.deleteItem(invalidId);
        responseSpec(SC_BAD_REQUEST);
        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        checkers.checkBadRequest(errorResponse, INVALID_ITEM_ID_ERROR_MESSAGE_DELETE);
    }
}
