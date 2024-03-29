package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;
import Hokify.quotes.*;

@SuppressWarnings("all")
public class Job {
  public static Number ID = 1;
  private String Name = "";
  private VDMSet Skills = SetUtil.set();
  private VDMSet Areas = SetUtil.set();
  private String Location = "";
  private Object Type = FullTimeQuote.getInstance();
  private String Description = "";
  public Number id = Job.ID;

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
    ID = Job.ID.intValue() + 1;
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

  public String getName() { 
 
    return Name; 
  } 

  public Job() {}

  public String toString() {

    return "\nJob: "
        + Utils.toString(Name).replaceAll("\\W","")
        + ", Skills: "
        + Utils.toString(Skills).replaceAll("\\{","").replaceAll("}","").replaceAll("\"","")
        + ", Areas: "
        + Utils.toString(Areas).replaceAll("\\{","").replaceAll("}","").replaceAll("\"","")
        + ", Location: "
        + Utils.toString(Location).replaceAll("\\W","")
        + ", Type: "
        + Utils.toString(Type).replaceAll("\\W","")
        + ", Description: "
        + Utils.toString(Description).replaceAll("\\{","").replaceAll("}","").replaceAll("\"","")
        + ", id: "
        + Utils.toString(id).replaceAll("\\W","")
        + "\n";

  }
}
