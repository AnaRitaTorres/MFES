package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Application {
  public User user;
  public Job job;

  public void cg_init_Application_1(final User u, final Job j) {

    user = u;
    job = j;
  }

  public Application(final User u, final Job j) {

    cg_init_Application_1(u, j);
  }

  public Employee getUser() {

    return (Employee) user;
  }

  public Job getJob() {

    return job;
  }

  public Application() {}

  public String toString() {

    return "Application:\n"
        + "User:\n "
        + Utils.toString(user)
        + "Job:\n "
        + Utils.toString(job)
        + "\n";
  }
}
