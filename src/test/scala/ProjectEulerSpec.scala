import org.scalatest._

class ProjectEulerSpec extends WordSpecLike with ShouldMatchers {
  import ProjectEuler._

  "when finding the largest palindrome made from the product of two numbers it" should {
    "yield the known solution when calculating for 2 digits" in {
      // http://projecteuler.net/problem=4
      largestPalindromeProductFor(100) shouldBe 9009
      largestPalindromeProductForUnwound(100) shouldBe 9009
      largestPalindromeProductTail(100) shouldBe 9009
    }
    "yield the known solution when calculating for 3 digits" in {
      // https://code.google.com/p/projecteuler-solutions/wiki/ProjectEulerSolutions
      largestPalindromeProduct3 shouldBe 906609
      largestPalindromeProductForUnwound(1000) shouldBe 906609
      largestPalindromeProductTail(1000) shouldBe 906609
    }
  }
}
