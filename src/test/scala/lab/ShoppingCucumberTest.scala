package lab

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FeatureSpec, GivenWhenThen}

@RunWith(classOf[JUnitRunner])
class ShoppingSpec extends FeatureSpec with GivenWhenThen {
  info("I would like to do some shopping")

  feature("Cart") {
    scenario("User pick a product during shopping") {
      Given("I have an empty cart")
      val p1 = Product("p1")
      val p2 = Product("p2")
      val catalog = new BasicCatalog(Map(p1 -> Price(10.00), p2 -> Price(5.00)))
      val warehouse = new BasicWarehouse()
      warehouse.supply(p1, 5)
      warehouse.supply(p2, 3)
      val cart = new BasicCart()
      val shopping = new Shopping(warehouse, catalog, cart, new BasicLogger())
      assert(cart.size == 0)

      When("I pick a product")
      shopping.pick(p1, 2)

      Then("my cart should contain one element")
      assert(cart.size == 1)
      assert(cart.content.contains(Item(p1, ItemDetails(2, Price(20.00)))))
    }
  }

}
