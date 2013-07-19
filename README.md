Concordance
===========

[Problem Definition](http://bit.ly/13pZg9I)

__Problem definition__

"Given an arbitrary text document written in English, write a program that
will generate a concordance, i.e. an alphabetical list of all word
occurrences, labeled with word frequencies. Bonus: label each word with the
sentence numbers in which each occurrence appeared." -- From http://bit.ly/13pZg9I

For example, this is a concordance of the above text:

```
alphabetical          {1}
program               {1}
in                    {2}
"given                {1}
bonus                 {1}
document              {1}
generate              {1}
all                   {1}
occurrences           {1}
label                 {1}
a                     {2}
text                  {1}
each                  {2}
english               {1}
arbitrary             {1}
concordance           {1}
i                     {1}
that                  {1}
numbers               {1}
occurrence            {1}
appeared."            {1}
labeled               {1}
will                  {1}
written               {1}
with                  {2}
from                  {1}
which                 {1}
an                    {2}
--                    {1}
word                  {3}
write                 {1}
of                    {1}
sentence              {1}
frequencies           {1}
list                  {1}
the                   {1}
```
 
__To run this example__

* Install [Scala](http://www.scala-lang.org/downloads)
* `cd concordance`
* `scalac Concordance.scala`
* `scala Concordance` 
optionally `Concordance <filename>` otherwise will parse the `doc.txt` file located in the dir.
  
  
### Note
* This is not meant to be an Enterprise Solution, it simply illustrates one potential solution
* It is not the fastest, and might crack given a huge file
* There are no framework dependencies 
* It doesn't take in to account most edge cases such as
  * `-` at end of line
  * Sentence Count
  * Treats symbols as words
