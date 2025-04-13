package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import model.CreateItemRequest;
import model.ItemInfo;
import model.StatisticResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.fail;
import static specs.Specification.responseSpec;

@DisplayName("Проверки создания объявления")
public class CreateItemTest extends BaseTest {

    StatisticResponse statistic = new StatisticResponse(1, 2, 3);
    CreateItemRequest createItemRequest = new CreateItemRequest(358930, "Донской сфинкс", 20000, statistic);
    CreateItemRequest createItemRequestWithoutPrise = new CreateItemRequest(358930, "Донской сфинкс", null, statistic);
    String uuidPattern = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    @Test
    @Description("Создание объявления")
    @DisplayName("Создание объявления")
    public void createItem() {
        Response response = requests.createItem(createItemRequest);
        responseSpec(SC_OK);
        checkers.checkCreateItemBody(response);

        String responseBody = response.getBody().asString();

        Pattern pattern = Pattern.compile(uuidPattern);
        Matcher matcher = pattern.matcher(responseBody);

        if (matcher.find()) {
            String uuid = matcher.group(0);
            Response getResponse = requests.getItem(uuid);
            responseSpec(SC_OK);
            ItemInfo[] itemInfo = getResponse.as(ItemInfo[].class);
            checkers.checkItemInfoById(itemInfo, uuid, createItemRequest.getName(), createItemRequest.getPrice(),
                    createItemRequest.getSellerID(), statistic.getContacts(), statistic.getLikes(), statistic.getViewCount());

        } else {
            fail("UUID не найден в ответе.");
        }
    }

    @Test
    @Description("Создание объявления без поля prise")
    @DisplayName("Создание объявления без поля prise")
    public void createItemWithoutPrice() {
        Response response = requests.createItem(createItemRequestWithoutPrise);
        responseSpec(SC_OK);
        checkers.checkCreateItemBody(response);

        String responseBody = response.getBody().asString();

        Pattern pattern = Pattern.compile(uuidPattern);
        Matcher matcher = pattern.matcher(responseBody);

        if (matcher.find()) {
            String uuid = matcher.group(0);
            Response getResponse = requests.getItem(uuid);
            responseSpec(SC_OK);
            ItemInfo[] itemInfo = getResponse.as(ItemInfo[].class);
            checkers.checkItemInfoById(itemInfo, uuid, createItemRequestWithoutPrise.getName(), 0,
                    createItemRequestWithoutPrise.getSellerID(), statistic.getContacts(), statistic.getLikes(), statistic.getViewCount());

        } else {
            fail("UUID не найден в ответе.");
        }
    }
}