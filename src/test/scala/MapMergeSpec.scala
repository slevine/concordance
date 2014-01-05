import org.scalatest.{ShouldMatchers, WordSpecLike}

class MapMergeSpec extends WordSpecLike with ShouldMatchers {

  import MapMerge._


  val m1 = Map("a" → "z", "b" → "y", "c" → "b")
  val m2 = Map("b" → "a", "c" → "uzz")

  val m1a = Map("a" → BigInt(1), "b" → "y", "c" → "b")
  val m2a = Map("a" → "q", "b" → List("a"), "c" → "uzz")

  val m3 = Map("a" → List("z"), "b" → List("y", "z"))
  val m4 = Map("b" → List("a"), "c" → List("b"))

  val m5 = Map("z" → m3, "n" → m1)
  val m6 = Map("n" → m2, "y" → m4)

  "when merging two maps with Maps for the type for each key it" should {
    "yield the correct merged map" in {
      merge(m5, m6) shouldBe
        Map("y" → Map("b" → List("a"),
          "c" → List("b")),
          "a" → "z", "b" → "y:a",
          "c" → "b:uzz",
          "z" → Map("a" -> List("z"), "b" → List("y", "z"))
        )
    }
  }

  "when merging two maps with String for the type for each key it" should {
    "yield the correct merged map" in {
      merge(m1, m2) shouldBe Map("a" → "z", "b" → "y:a", "c" → "b:uzz")
    }
  }

  "when merging two maps with different types for some keys" should {
    "yield the correct merged map" in {
      merge(m1a, m2a) shouldBe Map("a" → BigInt(1), "b" → "y", "c" → "b:uzz")
    }
  }

  "when merging two maps with List for the type for each key it" should {
    "yield the correct merged map" in {
      merge(m3, m4) shouldBe Map("a" → List("z"), "b" → List("y", "z", "a"), "c" → List("b"))
    }
  }


}
