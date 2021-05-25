package gitHub_RestAssured_Project;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Github_api {
	
	RequestSpecification requestSpec;
	
	int id;
	@BeforeClass
	public void setUp() {
	    requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON)
	    		.addHeader("Authorization", "token XXXXXXX")
	    		.setBaseUri("https://api.github.com")
	        .build();
	}
	@Test(priority=1)
	public void post_key() {

		String ssh_req = "{"
				+"\"title\": \"TestAPIKey\"," 
				+"\"key\": \"XXXXXXXXXXXXX\""
				+"}";
		Response response=given().spec(requestSpec).body(ssh_req).when().post("/user/keys");
		id=response.then().extract().path("id");
		System.out.print(response.getBody().asPrettyString());		
		response.then().statusCode(201);
		
	}
	@Test(priority=2)
	public void get_key() {
		Response response=given().spec(requestSpec).when().get("/user/keys");
		System.out.print(response.getBody().asPrettyString());
		
		response.then().statusCode(200);
	}
	@Test(priority=3)
	public void delete_key() {
		Response response= given().spec(requestSpec).pathParam("keyId", id)
				.when().delete("/user/keys/{keyId}");
		System.out.print(response.getBody().asPrettyString());
		response.then().statusCode(204);
	}

	
}
