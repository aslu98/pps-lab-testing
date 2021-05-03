import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSuite, Matchers}
import testLab.{Cart, Catalog, Item, ItemDetails, Logger, Price, Product, Shopping, Warehouse}
import testLecture.code.Server._

@RunWith(classOf[JUnitRunner])
class TestShoppingWithMock extends FunSuite with MockFactory with Matchers {
  test("Test Shopping"){
    val warehouseMock = mock[Warehouse]
    val catalogMock = mock[Catalog]
    val cartMock = mock[Cart]
    val loggerStub = stub[Logger]
    
    // Wire SUT with stubbed/mocked dependencies
    val shopping = new Shopping(warehouseMock, catalogMock, cartMock, loggerStub);
    
    // Arrange
    val product = Product("prod");
    val item = Item(product, ItemDetails(2, Price(10.00)));

    // Mock expectations
    inSequence {
      warehouseMock.supply(product, 5)
      warehouseMock.get(product, 3)._2 == 3
      warehouseMock.get(product, 3)._2 == 2
    }
  }
}