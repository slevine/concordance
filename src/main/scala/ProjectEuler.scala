import scala.annotation.tailrec

object ProjectEuler {
  /**
   * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
   * Find the largest palindrome made from the product of two 3-digit numbers.
   *
   * http://projecteuler.net/problem=4
   */
  def largestPalindromeProduct3 = largestPalindromeProductFor(1000)

  /**
   * Using Scala for {} syntactic sugar
   * Cleanest code, but requires more memory
   */
  def largestPalindromeProductFor(n: Int) = {
    for {
      i ← 1 until n
      j ← 1 until n
      if isPalindrome(i * j)
    } yield i * j
  }.max

  /**
   * Unwind the above, requires less space, but has mutable var
   */
  def largestPalindromeProductForUnwound(n: Int) = {
    var max = 1
    (1 until n) foreach (x ⇒
      (1 until n) foreach (y ⇒
        if (isPalindrome(x * y) && x * y > max) max = x * y))
    max
  }

  /**
   * Tail Recursive Solution
   */
  def largestPalindromeProductTail(n: Int) = {
    @tailrec
    def loop(m: Int, x: Int): Int = {
      @tailrec
      def innerLoop(im: Int, c: Int, y: Int): Int = {
        if (y < n)
          if (isPalindrome(y * x) && y * x > im)
            innerLoop(y * x, c, y + 1)
          else
            innerLoop(im, c, y + 1)
        else im
      }
      if (x < n) {
        val cm = innerLoop(m, x, 1)
        if (cm > m)
          loop(cm, x + 1)
        else
          loop(m, x + 1)
      }
      else m
    }
    loop(1, 1)
  }

  /**
   * Utility to help determine if a number is a palindrome
   */
  def isPalindrome(i: Int) = {
    val is = i.toString
    is == is.reverse
  }

}
