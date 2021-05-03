package testLecture.code.e1scalatest

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, FunSpec, FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class BasicTest extends FunSuite with Matchers {

  test("Simple test"){
    true shouldBe true
  }
}

@RunWith(classOf[JUnitRunner])
class BasicFlatSpec extends FlatSpec with Matchers {
  "An empty set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "raise NoSuchElementException for head" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}

@RunWith(classOf[JUnitRunner])
class BasicFunSpec extends FunSpec with Matchers {
  describe(" A Set ") {
    describe(" when empty ") {
      it(" should have size 0 ") {
        assert(Set.empty.size == 0)
      }
      it("should raise NoSuchElementException for head") {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }
    }

    describe(" when not empty"){
      it(" should not have size 0 ") {
        assert(Set(1, 2).size != 0)
      }
    }
  }

  describe("Two sets") {
    describe("when with the same values"){
      it("should be equal") {
        assert(Set(1, 2) == Set(1, 2))
      }
    }
  }
}

class ScalaTestExampleWithoutRunWithAnnotation extends FunSuite {
  test("simple test"){ }
}