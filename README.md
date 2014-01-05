### Interview Problems

- Given two immutable maps of String to AnyRef, do an arbitrarily deep merge. The rules are:
    - If Map1 and Map2 both declare a key "foo" and for both the key "foo" is another Map, then merge the Maps in both the parent Maps. 
        This merge should be recursive.
    - If for both the key "foo" is a List, concatenate those Lists. 
    - If for both the key "foo" is a String, concatenate those Strings using a semicolon separator.
    - In all other cases where both Map1 and Map2 have the same key, the value at that key in Map1 takes precedence.

- Implement http://projecteuler.net/problem=4 as efficiently as possible and expect larger numbers.

### To run:
- `$tar -zxf interview-problems.tgz`
- `$cd interview-problems`
- `$sbt test`