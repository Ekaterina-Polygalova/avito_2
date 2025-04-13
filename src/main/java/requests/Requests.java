package requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.CreateItemRequest;

import static constants.Endpoints.*;
import static specs.Specification.requestSpecification;

public class Requests {

    @Step("Get-запрос, получение статистики по айтем id")
    public Response getStatistic(String id) {
        return requestSpecification()
                .get(GET_STATISTIC, id)
                .then().log().all()
                .extract().response();
    }

    @Step("Get-запрос, получение объявления по айтем id")
    public Response getItem(String id) {
        return requestSpecification()
                .get(GET_ITEM, id)
                .then().log().all()
                .extract().response();
    }

    @Step("Get-запрос, получение всех объявлений по sellerId")
    public Response getItemsBySellerId(String id) {
        return requestSpecification()
                .get(GET_ITEMS_BY_SELLERID, id)
                .then().log().all()
                .extract().response();
    }

    @Step("Post-запрос, создание объявления")
    public Response createItem(CreateItemRequest createItemRequest) {
        return requestSpecification()
                .body(createItemRequest)
                .post(CREATE_ITEM)
                .then().log().all()
                .extract().response();
    }

    @Step("Get-запрос, получение статистики по айтем id версия 2")
    public Response getStatisticV2(String id) {
        return requestSpecification()
                .get(GET_STATISTIC_V2, id)
                .then().log().all()
                .extract().response();
    }

    @Step("Delete-запрос, удаление объявления")
    public Response deleteItem(String id) {
        return requestSpecification()
                .delete(DELETE_ITEM, id)
                .then().log().all()
                .extract().response();
    }
}