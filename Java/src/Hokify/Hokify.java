package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Hokify {
  public VDMSet users = SetUtil.set();
  public VDMSet jobs = SetUtil.set();
  public VDMSet applications = SetUtil.set();

  public void cg_init_Hokify_1() {

    return;
  }

  public Hokify() {

    cg_init_Hokify_1();
  }

  public void createUser(final User user) {

    users = SetUtil.union(Utils.copy(users), SetUtil.set(user));
  }

  public User getUserByName(final String name) {

    User u = null;
    for (Iterator iterator_1 = users.iterator(); iterator_1.hasNext(); ) {
      User user = (User) iterator_1.next();
      if (Utils.equals(user.Name, name)) {
        return user;
      }
    }
    return u;
  }

  public void addJob(final Employer user, final Job job) {

    user.addJob(job);
    jobs = SetUtil.union(Utils.copy(jobs), SetUtil.set(job));
  }

  public void deleteJob(final Employer user, final Job job) {

    deleteJobApplications(job);
    user.deleteJob(job);
    jobs = SetUtil.diff(Utils.copy(jobs), SetUtil.set(job));
  }

  public void deleteUser(final Employer user) {

    for (Iterator iterator_2 = user.jobs.iterator(); iterator_2.hasNext(); ) {
      Job job = (Job) iterator_2.next();
      deleteJobApplications(job);
      jobs = SetUtil.diff(Utils.copy(jobs), SetUtil.set(job));
    }
    users = SetUtil.diff(Utils.copy(users), SetUtil.set(user));
  }

  public void deleteUser(final Employee user) {

    deleteUserApplications(user);
    users = SetUtil.diff(Utils.copy(users), SetUtil.set(user));
  }

  public void apply(final User user, final Job job) {

    Application application = new Application(user, job);
    if (!(SetUtil.inSet(application, applications))) {
      applications = SetUtil.union(Utils.copy(applications), SetUtil.set(application));
    }
  }

  public void deleteJobApplications(final Job job) {

    for (Iterator iterator_3 = applications.iterator(); iterator_3.hasNext(); ) {
      Application application = (Application) iterator_3.next();
      if (Utils.equals(application.getJob(), job)) {
        applications = SetUtil.diff(Utils.copy(applications), SetUtil.set(application));
      }
    }
  }

  public void deleteUserApplications(final Employee user) {

    for (Iterator iterator_4 = applications.iterator(); iterator_4.hasNext(); ) {
      Application application = (Application) iterator_4.next();
      if (Utils.equals(application.getUser(), user)) {
        applications = SetUtil.diff(Utils.copy(applications), SetUtil.set(application));
      }
    }
  }

  public Job getJobById(final Number id) {

    Job j = null;
    for (Iterator iterator_5 = jobs.iterator(); iterator_5.hasNext(); ) {
      Job job = (Job) iterator_5.next();
      if (Utils.equals(job.id, id)) {
        j = job;
      }
    }
    return j;
  }

  public VDMSet getEmployees() {

    VDMSet employees = SetUtil.set();
    for (Iterator iterator_6 = users.iterator(); iterator_6.hasNext(); ) {
      User user = (User) iterator_6.next();
      if (Utils.is_(user, Employee.class)) {
        employees = SetUtil.union(Utils.copy(employees), SetUtil.set(user));
      }
    }
    return Utils.copy(employees);
  }

  public VDMSet getEmployers() {

    VDMSet employers = SetUtil.set();
    for (Iterator iterator_7 = users.iterator(); iterator_7.hasNext(); ) {
      User user = (User) iterator_7.next();
      if (Utils.is_(user, Employer.class)) {
        employers = SetUtil.union(Utils.copy(employers), SetUtil.set(user));
      }
    }
    return Utils.copy(employers);
  }

  public VDMSet getEmployeeApplications(final User user) {

    VDMSet apps = SetUtil.set();
    for (Iterator iterator_8 = applications.iterator(); iterator_8.hasNext(); ) {
      Application application = (Application) iterator_8.next();
      if (Utils.equals(application.getUser(), user)) {
        apps = SetUtil.union(Utils.copy(apps), SetUtil.set(application));
      }
    }
    return Utils.copy(apps);
  }

  public VDMSet searchJobBySkills(final Employee user) { 
 
    VDMSet j = SetUtil.set(); 
    for (Iterator iterator_9 = jobs.iterator(); iterator_9.hasNext(); ) { 
      Job job = (Job) iterator_9.next(); 
      if (Utils.empty(SetUtil.diff(job.getSkills(), user.Skills))) { 
        j = SetUtil.union(Utils.copy(j), SetUtil.set(job)); 
      } 
    } 
    return Utils.copy(j); 
  } 

  public VDMSet searchJobByInterests(final Employee user) { 
 
    VDMSet j = SetUtil.set(); 
    for (Iterator iterator_10 = jobs.iterator(); iterator_10.hasNext(); ) { 
      Job job = (Job) iterator_10.next(); 
      if (!(Utils.empty(SetUtil.intersect(job.getAreas(), user.Interests)))) { 

        j = SetUtil.union(Utils.copy(j), SetUtil.set(job)); 
      } 
    } 
    return Utils.copy(j); 
  } 

  public VDMSet searchJobByLocation(final String location) { 
 
    VDMSet j = SetUtil.set(); 
    for (Iterator iterator_11 = jobs.iterator(); iterator_11.hasNext(); ) { 
      Job job = (Job) iterator_11.next(); 
      if (Utils.equals(job.getLocation(), location)) { 
        j = SetUtil.union(Utils.copy(j), SetUtil.set(job)); 
      } 
    } 
    return Utils.copy(j); 
  } 

  public VDMSet searchJobByName(final String name) { 
 
    VDMSet j = SetUtil.set(); 
+
    for (Iterator iterator_12 = jobs.iterator(); iterator_12.hasNext(); ) { 
      Job job = (Job) iterator_12.next(); 
      if (Utils.equals(job.getName(), name)) { 
        j = SetUtil.union(Utils.copy(j), SetUtil.set(job)); 
      } 
    } 
    return Utils.copy(j); 
  } 
  
  public String toString() {

    return "Hokify{"
            + "users := "
            + Utils.toString(users)
            + ", jobs := "
            + Utils.toString(jobs)
            + ", applications := "
            + Utils.toString(applications)
            + "}";
  }
}
