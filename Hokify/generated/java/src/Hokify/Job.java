package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Job {
  private String Name = "";
  private VDMSet Skills = SetUtil.set();
  private VDMSet Areas = SetUtil.set();
  private String Location = "";
  private Object Type = Hokify.quotes.FullTimeQuote.getInstance();
  private String Description = "";

  public void cg_init_Job_1(
      final String name,
      final VDMSet skills,
      final VDMSet areas,
      final String location,
      final Object type,
      final String description) {

    Name = name;
    Skills = Utils.copy(skills);
    Areas = Utils.copy(areas);
    Location = location;
    Type = type;
    Description = description;
  }

  public Job(
      final String name,
      final VDMSet skills,
      final VDMSet areas,
      final String location,
      final Object type,
      final String description) {

    cg_init_Job_1(name, Utils.copy(skills), Utils.copy(areas), location, type, description);
  }

  public void addSkill(final String skill) {

    Skills = SetUtil.union(Utils.copy(Skills), SetUtil.set(skill));
  }

  public void addArea(final String area) {

    Areas = SetUtil.union(Utils.copy(Areas), SetUtil.set(area));
  }

  public void changeLocation(final String location) {

    Location = location;
  }

  public void changeType(final Object type) {

    Type = type;
  }

  public void changeDescription(final String description) {

    Description = description;
  }

  public VDMSet getSkills() {

    return Utils.copy(Skills);
  }

  public VDMSet getAreas() {

    return Utils.copy(Areas);
  }

  public String getLocation() {

    return Location;
  }

  public Object getType() {

    return Type;
  }

  public String getDescription() {

    return Description;
  }

  public Job() {}

  public String toString() {

    return "Job{"
        + "Name := "
        + Utils.toString(Name)
        + ", Skills := "
        + Utils.toString(Skills)
        + ", Areas := "
        + Utils.toString(Areas)
        + ", Location := "
        + Utils.toString(Location)
        + ", Type := "
        + Utils.toString(Type)
        + ", Description := "
        + Utils.toString(Description)
        + "}";
  }
}