package feature;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/feature/sprint_3",
        plugin = {"pretty", "json:/home/mperez/google_drive/LifeWay/reporter/2016-06-07 17-16-01/capitan/firefox/cucumber_output.json"})
public class LifeWayUS {
    
}