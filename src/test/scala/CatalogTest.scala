import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}
import testLab.{BasicCatalog, _};

@RunWith(classOf[JUnitRunner])
class CatalogTest extends FlatSpec with Matchers {
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