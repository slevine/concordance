object MapMerge {
  /**
   * 1. Given two immutable maps of String to AnyRef, do an arbitrarily deep merge. The rules are

     If Map1 and Map2 both declare a key "foo" and for both the key "foo" is another Map,
            then merge the Maps in both the parent Maps.
            This merge should be recursive.
     If for both the key "foo" is a List, concatenate those Lists.
     If for both the key "foo" is a String, concatenate those Strings using a semicolon separator.
     In all other cases where both Map1 and Map2 have the same key, the value at that key in Map1 takes precedence.
   */
  def merge(m1: Map[String, AnyRef], m2: Map[String, AnyRef]): Map[String, AnyRef] = {
    def combine(x: String) = (m1.get(x) getOrElse None, m2.get(x) getOrElse None) match {
      case (None, v) ⇒ Map(x → v)
      case (v, None) ⇒ Map(x → v)
      case (l: List[Any], r: List[Any]) ⇒ Map(x → (l ++ r))
      case (l: String, r: String) ⇒ Map(x → s"$l:$r")
      case (l: Map[_,_], r: Map[_,_]) ⇒ merge(l.asInstanceOf[Map[String,AnyRef]], r.asInstanceOf[Map[String,AnyRef]])
      case (l, r) ⇒ Map(x -> l)
    }

    def innerMerge(keys: List[String]): Map[String, AnyRef] = keys match {
      case Nil ⇒ Map()
      case x :: Nil ⇒ combine(x)
      case x :: xs ⇒ combine(x) ++ innerMerge(xs)
    }

    innerMerge((m1.keys ++ m2.keys) toList)
  }
}