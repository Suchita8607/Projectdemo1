package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
	@CucumberOptions(
	        features={"src/test/resources/Features/MyFeature.feature"},          //Path of Feature folder which hold feature file
	        glue={"StepDef","StepDefination" }, // Path of StepDefinition file
	        tags= "@ProductCategory", 
	        plugin = {"pretty",                      
	            "html:target/html/htmlReport.html",
	            "json:target/json/jsonReport.json",
	            
	            
	            },
	        monochrome=true,
	        publish= true,
	        dryRun=false
	        )




public class Runner {

}
