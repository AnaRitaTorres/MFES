package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Application {
  private User user;
  private Job job;

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

    return "Application{"
        + "user := "
        + Utils.toString(user)
        + ", job := "
        + Utils.toString(job)
        + "}";
  }
}
