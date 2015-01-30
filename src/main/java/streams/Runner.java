package streams;

/**
 * Author: Steve Levine
 * Date: 1/8/15
 */
import java.util.List;
import java.util.ArrayList;
import java.time.chrono.IsoChronology;
import java.time.LocalDate;
import java.util.function.Predicate;

public class Runner {

  public static class Person {

      public static enum Sex {
          MALE, FEMALE
      }

      String name;
      LocalDate birthday;
      Sex gender;
      String emailAddress;

      Person(String nameArg, LocalDate birthdayArg,
             Sex genderArg, String emailArg) {
          name = nameArg;
          birthday = birthdayArg;
          gender = genderArg;
          emailAddress = emailArg;
      }

      public int getAge() {
          return birthday
                  .until(IsoChronology.INSTANCE.dateNow())
                  .getYears();
      }

      public void printPerson() {
          System.out.println(name + ", " + this.getAge());
      }

      public Sex getGender() {
          return gender;
      }

      public String getName() {
          return name;
      }

      public String getEmailAddress() {
          return emailAddress;
      }

      public LocalDate getBirthday() {
          return birthday;
      }


      public static int compareByAge(Person a, Person b) {
          return a.birthday.compareTo(b.birthday);
      }

      public static List<Person> createRoster() {

          List<Person> roster = new ArrayList<>();
          roster.add(
                  new Person(
                          "Fred",
                          IsoChronology.INSTANCE.date(1980, 6, 20),
                          Person.Sex.MALE,
                          "fred@example.com"));
          roster.add(
                  new Person(
                          "Jane",
                          IsoChronology.INSTANCE.date(1990, 7, 15),
                          Person.Sex.FEMALE, "jane@example.com"));
          roster.add(
                  new Person(
                          "George",
                          IsoChronology.INSTANCE.date(1991, 8, 13),
                          Person.Sex.MALE, "george@example.com"));
          roster.add(
                  new Person(
                          "Bob",
                          IsoChronology.INSTANCE.date(2000, 9, 12),
                          Person.Sex.MALE, "bob@example.com"));

          return roster;
      }

      /**
       * Approach 1: Create Methods That Search for Members That Match One Characteristic
       *
       * @param roster
       * @param age
       */
      public static void printPersonsOlderThan(List<Person> roster, int age) {
          for (Person p : roster) {
              if (p.getAge() >= age) {
                  p.printPerson();
              }
          }
      }

      /**
       * Approach 2: Create More Generalized Search Methods
       *
       * @param roster
       * @param low
       * @param high
       */
      public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
          for (Person p : roster) {
              if (low >= p.getAge() && p.getAge() < high) {
                  p.printPerson();
              }
          }
      }


      /**
       * Approach 3: Specify Search Criteria Code in a Local Class
       */
      interface CheckPerson {
          boolean test(Person p);
      }

      public static void printPersons(List<Person> roster, CheckPerson tester) {
          for (Person p : roster) {
              if (tester.test(p)) {
                  p.printPerson();
              }
          }
      }

      static class CheckPersonEligibleForSelectiveService implements CheckPerson {
          public boolean test(Person p) {
              return p.gender == Person.Sex.MALE &&
                      p.getAge() >= 18 &&
                      p.getAge() <= 25;
          }
      }

      /**
       * NOTE: Approach 6: Use Standard Functional Interfaces with Lambda Expressions
       *
       * @param roster
       * @param tester
       */
      public static void printPersonsWithPredicate(
              List<Person> roster, Predicate<Person> tester) {
          for (Person p : roster) {
              if (tester.test(p)) {
                  p.printPerson();
              }
          }
      }



  }

  public static void main(String[] args) {
      //Person.printPersonsOlderThan(Person.createRoster(), 20);
      Person.printPersons(Person.createRoster(), new Person.CheckPersonEligibleForSelectiveService());

      //Approach 4: Specify Search Criteria Code in an Anonymous Class
      Person.printPersons(Person.createRoster(), new Person.CheckPerson() {
          public boolean test(Person p) {
              return p.getGender() == Person.Sex.MALE
                      && p.getAge() >= 18
                      && p.getAge() <= 25;
          }
      });

      // Approach 5: Specify Search Criteria Code with a Lambda Expression
      Person.printPersons(Person.createRoster(), (Person p) -> p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);

      // Approach 6: Use Standard Functional Interfaces with Lambda Expressions
      Person.printPersonsWithPredicate(Person.createRoster(), p -> p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);

      // Approach 9: Use Aggregate Operations That Accept Lambda Expressions as Parameters
      Person.createRoster()
              .stream()
              .filter(p -> p.gender == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25)
              .forEach(System.out::println);
  }
}
