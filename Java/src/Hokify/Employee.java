package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Employee extends User {
  public VDMSet Skills = SetUtil.set();
  public VDMSet Interests = SetUtil.set();
  public String Location = "";
  public String CV = "";

  public void cg_init_Employee_1(final String name) {

    cg_init_User_1(name);
  }

  public Employee(final String name) {

    cg_init_Employee_1(name);
  }

  public void addInterest(final String interest) {

    Interests = SetUtil.union(Utils.copy(Interests), SetUtil.set(interest));
  }

  public void addSkill(final String skill) {

    Skills = SetUtil.union(Utils.copy(Skills), SetUtil.set(skill));
  }

  public void addLocation(final String location) {

    Location = location;
  }

  public void createCV(final String cv) {

    CV = cv;
  }

  public void changeName(final String name) {

    Name = name;
  }

  public Employee() {}

  public String toString() {

    return "Employee{"
        + "Skills := "
        + Utils.toString(Skills)
        + ", Interests := "
        + Utils.toString(Interests)
        + ", Location := "
        + Utils.toString(Location)
        + ", CV := "
        + Utils.toString(CV)
        + "}";
  }
}
