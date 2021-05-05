package lab.ex6

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(features = Array("classpath:features/ShoppingFeature.feature"),
  tags = Array("not @Wip"), glue = Array("classpath:lab.ex6.steps"),
  plugin = Array("pretty", "html:target/cucumber/html"))
class RunCucumberTests

