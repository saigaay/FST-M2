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
	    		.addHeader("Authorization", "token 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")
	    		.setBaseUri("https://api.github.com")
	        .build();
	}
	@Test(priority=1)
	public void post_key() {

		String ssh_req = "{"
				+"\"title\": \"TestAPIKey\"," 
				+"\"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDKK2CcwgrENitVW+CUgv9N4ECpDK/z6i"
				+ "hD3X8nQi6PmOzNraYlCTZRngwJ8oB4c2Dv2H7EiVnq2rOOC5YgeXFxiyF18MQSez2uJp3vceY/dqSY"
				+ "TV7+NJRJJ7+S/rmH2tQthVHrYZorIcnt7CTm3rtFeX1HPiWQwDAl9I9zBDBuU9zIJNzeSctWVNp+tieFS"
				+ "ROjOmduuFfTX9pXM/0jlFYUnE0cKI9tFVXG6aXowbwjYn5BgiCLfKJguAnDkr0qOIFDHv+50/9Sq4gPv3mq7"
				+ "5yVYr7N0/YKwo0PFVe4RZFKzmy5EaCEBrOiiVhTyQ3wqjvM9Mh7w9oSW0YzJH/kRoBD\""
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
