import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSuite, Matchers}
import testLab._;

@RunWith(classOf[JUnitRunner])
class CartTest extends FunSuite with Matchers {
  val cart = new BasicCart();
  val it = Item(Product("shoes"), ItemDetails(2, Price(35.99)));
  val otherit = Item(Product("tomato"), ItemDetails(10, Price(10.00)));

  test("An empty chart should have size 0"){
     cart.size shouldBe 0
  }

  test("Adding and element to cart, cart should have size 1"){
    cart.add(it)
    cart.size shouldBe 1
    cart.totalCost shouldBe 35.99
    cart.content.contains(it) shouldBe true
  }

  test("Adding same element to cart, size should be 1"){
    cart.add(it)
    cart.size shouldBe 1
    cart.totalCost shouldBe 71.98
    cart.content.contains(Item(Product("shoes"), ItemDetails(4, Price(71.98)))) shouldBe true
  }

  test("Adding a different element to cart, size should be 2"){
    cart.add(otherit)
    cart.size shouldBe 2
    cart.totalCost shouldBe 81.98
    cart.content.contains(otherit) shouldBe true
  }

}