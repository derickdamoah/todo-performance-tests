package computerdatabase

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class BasicSimulation extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:9000/") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn: ScenarioBuilder = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(http("load the homepage")
      .get("/"))
    .pause(2) // Note that Gatling has recorder real time pauses
    .exec(http("load the add item page")
      .get("/addItem"))
    .pause(2)
    .exec(http("complete and submit the form on the add item page") // Here's an example of a POST request
      .post("/addItem")
      .formParam("""title""", """test title 1""") // Note the triple double quotes: used in Scala for protecting a whole chain of characters (no need for backslash)
      .formParam("""description""", """test description 1""")
    )
    .exec(http("load the homepage")
      .get("/"))

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
