package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Employer extends User {
  public VDMSet jobs = SetUtil.set();

  public void cg_init_Employer_1(final String name) {

    cg_init_User_1(name);
  }

  public Employer(final String name) {

    cg_init_Employer_1(name);
  }

  public void addJob(final Job job) {

    jobs = SetUtil.union(Utils.copy(jobs), SetUtil.set(job));
  }

  public void changeName(final String name) {

    Name = name;
  }

  public void deleteJob(final Job job) {

    jobs = SetUtil.diff(Utils.copy(jobs), SetUtil.set(job));
  }

  public Employer() {}

  public String toString() {

    return "Name: "
            + Utils.toString(Name).replaceAll("\\W","")
            + " Jobs:\n " + Utils.toString(jobs).replaceAll("\\{","").replaceAll("}","")
            + "\n";
  }
}
