import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSuite, Matchers}
import testLab.{BasicCart, BasicCatalog, BasicWarehouse, Cart, Catalog, Item, ItemDetails, Logger, Price, Product, Shopping, Warehouse}
import testLecture.code.Server._

@RunWith(classOf[JUnitRunner])
class TestShoppingWithMock extends FunSuite with MockFactory with Matchers {
  test("Test Shopping (LoggerMock)"){
    val loggerMock= mock[Logger]

    val warehouse = new BasicWarehouse()
    val product1 = Product("p1")
    val catalog = new BasicCatalog(Map(product1 -> Price(10.00)))
    val cart = new BasicCart()
    val shopping = new Shopping(warehouse, catalog, cart, loggerMock)

    inSequence {
      (loggerMock.log _).expects("Trying to pick 10 pieces of Product(p1).")
      (loggerMock.log _).expects("There are no pieces of the requested product in the warehouse.")

      (loggerMock.log _).expects("Trying to pick 2 pieces of Product(p1).")
      (loggerMock.log _).expects("The warehouse has 2 pieces; adding them to cart.")
      (loggerMock.log _).expects("2 pieces of Product(p1) collectively cost Price(20.0).")
      (loggerMock.log _).expects("Updated cart: now it contains 1 items for total 20.0")

      (loggerMock.log _).expects("Trying to pick 6 pieces of Product(p1).")
      (loggerMock.log _).expects("The warehouse has 3 pieces; adding them to cart.")
      (loggerMock.log _).expects("3 pieces of Product(p1) collectively cost Price(30.0).")
      (loggerMock.log _).expects("Updated cart: now it contains 1 items for total 50.0")
    }

    shopping.pick(product1, 10)
    warehouse.supply(product1, 5)
    shopping.pick(product1, 2)
    shopping.pick(product1, 6)

  }
}