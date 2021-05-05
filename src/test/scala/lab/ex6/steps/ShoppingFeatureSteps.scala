package lab.ex6.steps

import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import lab._
import org.junit.Assert

class ShoppingFeatureSteps extends ScalaDsl with EN {
  val warehouse = new BasicWarehouse()
  var catalog = new BasicCatalog(Map())
  var cart = new BasicCart()
  var shopping = new Shopping(warehouse, catalog, cart, new BasicLogger())

  Given("""^An empty cart$"""){cart = new BasicCart()}
  When("""^I pick a product with name "([^"]*)"$"""){ (p: String) =>
    //creating "world" to make picking possible
    catalog = new BasicCatalog(Map(Product(p) -> Price(10.00)))
    shopping = new Shopping(warehouse, catalog, cart, new BasicLogger())
    warehouse.supply(Product(p), 5)

    shopping.pick(Product(p), 1)
  }
  Then("""^My cart should contain (\d+) element$"""){ (nElements:Int) =>
    Assert.assertEquals(cart.size, nElements)
  }

  var nItemsBefore = 0;
  var removeProductName = "";
  Given("""^A cart with (\d+) items:$"""){ (nItems:Int, productNames:DataTable) => {
    nItemsBefore = nItems
    cart = new BasicCart()
    productNames.asList(classOf[String]).forEach( productName => {
      //creating "world" to make picking possible
      catalog = new BasicCatalog(catalog.products + (Product(productName) -> Price(10.00)))
      shopping = new Shopping(warehouse, catalog, cart, new BasicLogger())
      warehouse.supply(Product(productName), 5)

      shopping.pick(Product(productName), 1)
    })
  }}
  When("""^I remove the product "([^"]*)"$"""){ (productName: String) => {
    removeProductName = productName
    cart.remove(Product(productName))
  }}
  Then("""^My cart should contain (\d+) items:$"""){ (nItemsAfter:Int, productNames:DataTable) => {
    Assert.assertEquals(nItemsAfter, nItemsBefore - 1)
    productNames.asList(classOf[String]).forEach( productName => {
      Assert.assertTrue(cart.content.map(_.product).contains(Product(productName)))
    })
    Assert.assertFalse(cart.content.map(_.product).contains(Product(removeProductName)))
  }}
}
