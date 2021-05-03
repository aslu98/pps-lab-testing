import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, Matchers}
import testLab._;

@RunWith(classOf[JUnitRunner])
class WarehouseTest extends FunSpec with Matchers {
  val tomato = Product("tomato");
  val shoes = Product("shoes");
  val warehouse = new BasicWarehouse();

  describe("A Warehouse"){
    describe("when empty"){
      it ("should have no shoes"){
        assert(warehouse.get(shoes, 1) == (shoes, 0))
      }

      it ("should have no tomato"){
        assert(warehouse.get(tomato, 1) == (tomato, 0))
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