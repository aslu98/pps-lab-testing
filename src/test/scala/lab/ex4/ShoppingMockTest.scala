package lab.ex4

import lab._
import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class ShoppingMockTest extends FunSuite with MockFactory with Matchers {
  test("Test Shopping"){
    val loggerMock= mock[Logger]
    val catalogMock = mock[Catalog]
    val cartMock = mock[Cart]

    val warehouse = new BasicWarehouse()
    val product1 = Product("p1")
    val shopping = new Shopping(warehouse, catalogMock, cartMock, loggerMock)

    inSequence {
      (loggerMock.log _).expects("Trying to pick 10 pieces of Product(p1).")
      (loggerMock.log _).expects("There are no pieces of the requested product in the warehouse.")

      (loggerMock.log _).expects("Trying to pick 2 pieces of Product(p1).")
      (loggerMock.log _).expects("The warehouse has 2 pieces; adding them to cart.")
      (catalogMock.priceFor (_: Product, _: Int)).expects(product1, 2).returning(Price(20.0))
      (loggerMock.log _).expects("2 pieces of Product(p1) collectively cost Price(20.0).")
      (cartMock.add _).expects(Item(product1, ItemDetails(2, Price(20.00))))
      (cartMock.size _).expects().returning(1)
      (cartMock.totalCost _).expects().returning(20.0)
      (loggerMock.log _).expects("Updated cart: now it contains 1 items for total 20.0")

      (loggerMock.log _).expects("Trying to pick 6 pieces of Product(p1).")
      (loggerMock.log _).expects("The warehouse has 3 pieces; adding them to cart.")
      (catalogMock.priceFor (_: Product, _: Int)).expects(product1, 3).returning(Price(30.0))
      (loggerMock.log _).expects("3 pieces of Product(p1) collectively cost Price(30.0).")
      (cartMock.add _).expects(Item(product1, ItemDetails(3, Price(30.00))))
      (cartMock.size _).expects().returning(1)
      (cartMock.totalCost _).expects().returning(50.0)
      (loggerMock.log _).expects("Updated cart: now it contains 1 items for total 50.0")

      (loggerMock.log _).expects("Trying to pick 1 pieces of Product(p1).")
      (loggerMock.log _).expects("There are no pieces of the requested product in the warehouse.")
    }

    shopping.pick(product1, 10)
    warehouse.supply(product1, 5)
    shopping.pick(product1, 2)
    shopping.pick(product1, 6)
    shopping.pick(product1, 1)
  }
}