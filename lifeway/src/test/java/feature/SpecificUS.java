package feature;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature/sprint_7_na/us_247_shopping_cart_items.feature",
        plugin = {"pretty", "json:/home/mperez/google_drive/LifeWay/temporal/cucumber_output.json"})
public class SpecificUS {
    
}
    