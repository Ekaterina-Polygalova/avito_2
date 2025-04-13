package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static constants.Endpoints.BASE_URL;
import static io.restassured.RestAssured.given;

public class Specification {
    public static RequestSpecification requestSpecification() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when().log().all();
    }

    public static ResponseSpecification responseSpec(Integer statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }
}