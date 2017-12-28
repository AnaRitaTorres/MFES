package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Hokify {
  public VDMSet users = SetUtil.set();
  public VDMSet jobs = SetUtil.set();

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

    user.deleteJob(job);
    jobs = SetUtil.diff(Utils.copy(jobs), SetUtil.set(job));
  }

  public void deleteUser(final Employer user) {

    for (Iterator iterator_2 = user.jobs.iterator(); iterator_2.hasNext(); ) {
      Job job = (Job) iterator_2.next();
      jobs = SetUtil.diff(Utils.copy(jobs), SetUtil.set(job));
    }
    users = SetUtil.diff(Utils.copy(users), SetUtil.set(user));
  }

  public void deleteUser(final Employee user) {

    users = SetUtil.diff(Utils.copy(users), SetUtil.set(user));
  }

  public String toString() {

    return "Hokify{"
        + "users := "
        + Utils.toString(users)
        + ", jobs := "
        + Utils.toString(jobs)
        + "}";
  }
}
