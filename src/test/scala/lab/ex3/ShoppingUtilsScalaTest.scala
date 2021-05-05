package lab.ex3

import lab._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, FunSpec, FunSuite, Matchers};

@RunWith(classOf[JUnitRunner])
class CartScalaTest extends FunSuite with Matchers {
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

@RunWith(classOf[JUnitRunner])
class CatalogScalaTest extends FlatSpec with Matchers {
  val tomato = Product("tomato");
  val shoes = Product("shoes");
  val cat = new BasicCatalog(Map(shoes -> Price(15.99), tomato -> Price(1.00)));

  "An empty catalog" should " have no products "  in {
    assert(new BasicCatalog(Map()).products.isEmpty)
  }

  "The example catalog" should " have 2 products "  in {
    assert(cat.products.size == 2)
  }

  "The example catalog" should " contain tomato and shoes"  in {
    assert(cat.products.contains(tomato))
    assert(cat.products.contains(shoes))
  }

  "Tomato price" should " be 1.00"  in {
    assert(cat.priceFor(tomato) == Price(1.00))
  }

  "The price for 3 tomatoes" should " be 3.00"  in {
    assert(cat.priceFor(tomato, 3) == Price(3.00))
  }
}

@RunWith(classOf[JUnitRunner])
class WarehouseScalaTest extends FunSpec with Matchers {
  val tomato = Product("tomato");
  val shoes = Product("shoes");
  val warehouse = new BasicWarehouse();

  describe("A Warehouse"){
    describe("when empty"){
      it ("should have no shoes"){
        assert(warehouse.get(shoes, 1) == (shoes, 0))
      }

      it ("should have no tomato"){
        assert(warehouse.get(tomato, 5) == (tomato, 0))
      }

      it ("should be possible to add shoes and tomatoes"){
        warehouse.supply(shoes, 2)
        warehouse.supply(tomato, 7)
      }
    }

    describe("when shoes and tomatoes have been supplied"){
      it ("should be possible to get shoes"){
        assert(warehouse.get(shoes, 1) == (shoes, 1))
      }

      it ("should be possibile to get tomatoes"){
        assert(warehouse.get(tomato, 5) == (tomato, 5))
      }
    }

    describe("when asked for more quantity of a product that it actually has"){
      it ("should give all the quantity it has but no more"){
        assert(warehouse.get(tomato, 5) == (tomato, 2))
      }
    }

  }

}