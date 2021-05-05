package lab.ex5

import org.junit.runner.RunWith
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Prop, Properties}
import testLecture.ScalaCheckJUnitRunner

@RunWith(classOf[ScalaCheckJUnitRunner])
class TestOnLists extends Properties("Lists") {
  def genericAssociative[A:Arbitrary] = forAll{ (l1: Seq[A], l2: Seq[A], l3: Seq[A]) =>
    (l1 ++ l2) ++ l3 == l1 ++ (l2 ++ l3)
  }

  def genericAddToEmpty[A:Arbitrary] = forAll{ l1: Seq[A] => List.empty ++ l1 == l1}

  def genericMapIdentity[A:Arbitrary] = forAll{l1: Seq[A] => l1.map(a => a) == l1}

  def genericComposeFunctions[A:Arbitrary](f: A => A, g: A => A) =
    forAll { l1: Seq[A] => l1.map(f compose g) == l1.map(g).map(f)}

  property("Associative") = Prop.all(genericAssociative[Int], genericAssociative[String], genericAssociative[Double])

  property("Add to empty") = Prop.all(genericAddToEmpty[Int], genericAddToEmpty[String], genericAddToEmpty[Double])

  property("Map identity") = Prop.all(genericMapIdentity[Int], genericMapIdentity[String], genericMapIdentity[Double])

  property("Compose") = Prop.all(genericComposeFunctions[Int](_+1, _+2),
    genericComposeFunctions[String](_+ "a", _+"b"), genericComposeFunctions[Double](_+1.0, _+2.0))

  property("Reverse") = forAll { (l1: Seq[Int]) =>
    l1.reverse.reverse == l1
  }
}


@RunWith(classOf[ScalaCheckJUnitRunner])
class TestPalindromes extends Properties("Persons") {

  case class Palindrome(string: String, char:Option[Char]) {
    def get(): String = string ++ char.getOrElse("").toString ++ string.reverse
  }

  implicit val arbitraryPalindrome: Arbitrary[Palindrome] = Arbitrary(
    for(string <- Gen.alphaStr;
        char <- Gen.option[Char](Gen.alphaChar))
      yield Palindrome(string, char)
  )

  property("Reverse") = forAll { (p: Palindrome) =>
    p.get == p.get.reverse
  }
}