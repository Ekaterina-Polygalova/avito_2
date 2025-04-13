package tests;

import io.qameta.allure.Description;
import model.ErrorResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import io.restassured.response.Response;
import model.StatisticResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static constants.ErrorConstants.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static specs.Specification.*;

@DisplayName("Проверки получения статистики для версии 2")
public class GetStatisticV2Test extends BaseTest {

    String id = "bdfaabd0-431d-419f-a17e-ccd6bb4357f2";
    Integer expectedContacts = 6;
    Integer expectedLikes = 60;
    Integer expectedViewCount = 1098;

    @Test
    @Description("Получение статистики по айтем id")
    @DisplayName("Получение статистики по айтем id")
    public void getStatisticTest() {
        Response response = requests.getStatisticV2(id);
        responseSpec(SC_OK);
        StatisticResponse[] statisticResponse = response.as(StatisticResponse[].class);
        checkers.checkGetStatistic(statisticResponse, expectedContacts, expectedLikes, expectedViewCount);
    }

    @Test
    @Description("Поиск статистики несуществующего объявления")
    @DisplayName("Поиск статистики несуществующего объявления")
    public void notFoundStatisticTest() {
        Response response = requests.getStatisticV2(NOT_EXIST_ID);
        responseSpec(SC_BAD_REQUEST);
        ErrorResponse errorResponses = response.as(ErrorResponse.class);
        checkers.checkNotFound(errorResponses, STATISTIC_NOT_FOUND_MESSAGE);
    }

    @ParameterizedTest
    @Description("Получить статистику по объявлению с невалидными данными")
    @DisplayName("Получить статистику по объявлению с невалидными данными")
    @ValueSource(strings = {"12345", "Объявление", "Item", "-_.~"})
    public void getStatisticInvalidIdTest(String invalidId) {
        Response response = requests.getStatisticV2(invalidId);
        responseSpec(SC_BAD_REQUEST);
        ErrorResponse errorResponses = response.as(ErrorResponse.class);
        checkers.checkBadRequest(errorResponses, INVALID_ITEM_ID_ERROR_MESSAGE);
    }
}