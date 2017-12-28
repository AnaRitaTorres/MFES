package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class HokifyTest extends MyTestCase {
  private Employee employee1 = new Employee("Sara");
  private Employee employee2 = new Employee("Rita");
  private Employer employer1 = new Employer("Ana");
  private Employer employer2 = new Employer("Luis");
  private Job job1 =
      new Job(
          "Programador HTML",
          SetUtil.set("HTML"),
          SetUtil.set("Programação", "Web"),
          "Porto",
          Hokify.quotes.FullTimeQuote.getInstance(),
          "Vaga para programador em HTML");
  private Job job2 =
      new Job(
          "Programador Versátil",
          SetUtil.set("Java", "C++"),
          SetUtil.set("Programação"),
          "Porto",
          Hokify.quotes.PartTimeQuote.getInstance(),
          "Vaga para programador em multiplas linguagens");
  private Job job3 =
      new Job(
          "Designer",
          SetUtil.set("CSS"),
          SetUtil.set("Programação", "Design"),
          "Lisboa",
          Hokify.quotes.PartTimeQuote.getInstance(),
          "Vaga design");
  public static Hokify hokify = new Hokify();

  public static void main() {

    HokifyTest test = new HokifyTest();
    IO.print("Test Create User => ");
    test.testCreateUser();
    IO.println("Passed");
    IO.print("Test Add Interest => ");
    test.testAddInterest();
    IO.println("Passed");
    IO.print("Test Add Skills => ");
    test.testAddSkills();
    IO.println("Passed");
    IO.print("Test Add Location => ");
    test.testAddLocation();
    IO.println("Passed");
    IO.print("Test Add CV => ");
    test.testAddCV();
    IO.println("Passed");
    IO.print("Test Add Job => ");
    test.testAddJob();
    IO.println("Passed");
    IO.print("Test Add Job Skill => ");
    test.testAddJobSkill();
    IO.println("Passed");
    IO.print("Test Add Job Area => ");
    test.testAddJobArea();
    IO.println("Passed");
    IO.print("Test Change Job Location => ");
    test.testChangeJobLocation();
    IO.println("Passed");
    IO.print("Test Change Job Type => ");
    test.testChangeJobType();
    IO.println("Passed");
    IO.print("Test Change Job Description => ");
    test.testChangeJobDescription();
    IO.println("Passed");
    IO.print("Test Change User Name => ");
    test.testChangeName();
    IO.println("Passed");
    IO.print("Test Delete Job => ");
    test.testDeleteJob();
    IO.println("Passed");
    IO.print("Test Delete User => ");
    test.testDeleteUser();
    IO.println("Passed");
  }

  private void testCreateUser() {

    HokifyTest.hokify.createUser(employee1);
    HokifyTest.hokify.createUser(employee2);
    HokifyTest.hokify.createUser(employer1);
    HokifyTest.hokify.createUser(employer2);
    assertEqual(4L, hokify.users.size());
    assertEqual(SetUtil.set(employee1, employee2, employer1, employer2), hokify.users);
  }

  private void testAddInterest() {

    Employee user1 = hokify.getUserByName("Sara");
    Employee user2 = hokify.getUserByName("Rita");
    user1.addInterest("Programação");
    user1.addInterest("Web");
    user2.addInterest("Programação");
    user2.addInterest("Web");
    user2.addInterest("Design");
    assertEqual(2L, user1.Interests.size());
    assertEqual(3L, user2.Interests.size());
  }

  private void testAddSkills() {

    Employee user1 = hokify.getUserByName("Sara");
    Employee user2 = hokify.getUserByName("Rita");
    user1.addSkill("Java");
    user1.addSkill("C++");
    user2.addSkill("HTML");
    assertEqual(2L, user1.Skills.size());
    assertEqual(1L, user2.Skills.size());
  }

  private void testAddLocation() {

    Employee user1 = hokify.getUserByName("Sara");
    Employee user2 = hokify.getUserByName("Rita");
    user1.addLocation("Porto");
    user2.addLocation("Lisboa");
    assertTrue(!(Utils.equals(user1.Location, user2.Location)));
    assertEqual("Porto", user1.Location);
    assertEqual("Lisboa", user2.Location);
    user2.addLocation("Porto");
    assertEqual("Porto", user2.Location);
  }

  private void testAddCV() {

    Employee user1 = hokify.getUserByName("Sara");
    Employee user2 = hokify.getUserByName("Rita");
    user1.createCV("Nome: Sara Santos Morada: Rua Testes");
    user2.createCV("Nome: Rita Torres Morada: Rua Teste Emprego: ....");
    assertTrue(!(Utils.equals(user1.CV, "")));
    assertTrue(!(Utils.equals(user2.CV, "")));
    assertTrue(!(Utils.equals(user1.CV, user2.CV)));
  }

  private void testAddJob() {

    Employer user1 = hokify.getUserByName("Ana");
    Employer user2 = hokify.getUserByName("Luis");
    HokifyTest.hokify.addJob(user1, job1);
    HokifyTest.hokify.addJob(user2, job2);
    HokifyTest.hokify.addJob(user1, job3);
    assertEqual(3L, hokify.jobs.size());
    assertEqual(SetUtil.set(job1, job2, job3), hokify.jobs);
    assertEqual(2L, user1.jobs.size());
    assertEqual(1L, user2.jobs.size());
  }

  private void testAddJobSkill() {

    job1.addSkill("CSS");
    job2.addSkill("VDM++");
    assertEqual(SetUtil.set("CSS", "HTML"), job1.getSkills());
    assertEqual(SetUtil.set("VDM++", "Java", "C++"), job2.getSkills());
  }

  private void testAddJobArea() {

    job1.addArea("Engenharia");
    job2.addArea("Engenharia");
    assertEqual(SetUtil.set("Engenharia", "Programação", "Web"), job1.getAreas());
    assertEqual(SetUtil.set("Engenharia", "Programação"), job2.getAreas());
  }

  private void testChangeJobLocation() {

    job1.changeLocation("Maia");
    job2.changeLocation("Lisboa");
    assertEqual("Maia", job1.getLocation());
    assertEqual("Lisboa", job2.getLocation());
  }

  private void testChangeJobType() {

    job1.changeType(Hokify.quotes.PartTimeQuote.getInstance());
    job2.changeType(Hokify.quotes.FullTimeQuote.getInstance());
    assertEqual(Hokify.quotes.PartTimeQuote.getInstance(), ((Object) job1.getType()));
    assertEqual(Hokify.quotes.FullTimeQuote.getInstance(), ((Object) job2.getType()));
  }

  private void testChangeJobDescription() {

    job1.changeDescription("Vaga para programador em HTML e CSS");
    job2.changeDescription("Vaga para programador em multiplas linguagens a tempo inteiro");
    assertEqual("Vaga para programador em HTML e CSS", job1.getDescription());
    assertEqual(
        "Vaga para programador em multiplas linguagens a tempo inteiro", job2.getDescription());
  }

  private void testChangeName() {

    employee1.changeName("Sara Santos");
    employer1.changeName("Ana Soares");
    assertEqual("Sara Santos", employee1.Name);
    assertEqual("Ana Soares", employer1.Name);
  }

  private void testDeleteJob() {

    HokifyTest.hokify.deleteJob(employer1, job1);
    assertEqual(2L, hokify.jobs.size());
    assertEqual(1L, employer1.jobs.size());
    assertEqual(SetUtil.set(job3), employer1.jobs);
    assertEqual(SetUtil.set(job2, job3), hokify.jobs);
  }

  private void testDeleteUser() {

    HokifyTest.hokify.deleteUser(employer1);
    HokifyTest.hokify.deleteUser(employee1);
    assertEqual(2L, hokify.users.size());
    assertEqual(1L, hokify.jobs.size());
  }

  public void testFailAddInterest() {

    Employee user1 = new Employee("Test");
    user1.addInterest("");
  }

  public void testAddRepeatedInterest() {

    Employee user1 = new Employee("Test");
    user1.addInterest("I1");
    user1.addInterest("I1");
  }

  public void testAddRepeatedSkill() {

    employee1.addSkill("Web");
    employee1.addSkill("Web");
  }

  public void testAddFailSkill() {

    employee1.addSkill("");
  }

  public void testFailChangeLocation() {

    employee1.addLocation("");
  }

  public void testFailCreateCV() {

    employee1.createCV("");
  }

  public void testFailUpdateUser() {

    employee1.changeName("");
  }

  public void testFailCreateJobName() {

    Job job1_1 = null;
    job1_1 =
        new Job(
            "",
            SetUtil.set("HTML"),
            SetUtil.set("Programação", "Web"),
            "Porto",
            Hokify.quotes.FullTimeQuote.getInstance(),
            "Vaga para programador em HTML");
  }

  public void testFailCreateJobLocation() {

    Job job1 = null;
    job1 =
        new Job(
            "Programador HTML",
            SetUtil.set("HTML"),
            SetUtil.set("Programação", "Web"),
            "",
            Hokify.quotes.FullTimeQuote.getInstance(),
            "Vaga para programador em HTML");
  }

  public void testFailCreateJobDescription() {

    Job job1 = null;
    job1 =
        new Job(
            "Programador HTML",
            SetUtil.set("HTML"),
            SetUtil.set("Programação", "Web"),
            "Porto",
            Hokify.quotes.FullTimeQuote.getInstance(),
            "");
  }

  public void testFailCreateJobUser() {

    Job job1 =
        new Job(
            "Programador HTML",
            SetUtil.set("HTML"),
            SetUtil.set("Programação", "Web"),
            "Porto",
            Hokify.quotes.FullTimeQuote.getInstance(),
            "Vaga para programador em HTML");
    Employer user1 = new Employer("Test");
    HokifyTest.hokify.addJob(user1, job1);
  }

  public void testFailCreateRepeatedJob() {

    Job job1 =
        new Job(
            "Programador HTML",
            SetUtil.set("HTML"),
            SetUtil.set("Programação", "Web"),
            "Porto",
            Hokify.quotes.FullTimeQuote.getInstance(),
            "Vaga para programador em HTML");
    Employer user1 = new Employer("Test");
    HokifyTest.hokify.createUser(user1);
    HokifyTest.hokify.addJob(user1, job1);
    HokifyTest.hokify.addJob(user1, job1);
  }

  public HokifyTest() {}

  public String toString() {

    return "HokifyTest{"
        + "employee1 := "
        + Utils.toString(employee1)
        + ", employee2 := "
        + Utils.toString(employee2)
        + ", employer1 := "
        + Utils.toString(employer1)
        + ", employer2 := "
        + Utils.toString(employer2)
        + ", job1 := "
        + Utils.toString(job1)
        + ", job2 := "
        + Utils.toString(job2)
        + ", job3 := "
        + Utils.toString(job3)
        + ", hokify := "
        + Utils.toString(hokify)
        + "}";
  }
}
