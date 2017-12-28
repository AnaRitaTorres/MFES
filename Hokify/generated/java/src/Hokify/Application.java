package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Application {
  private User user;
  private Job job;

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
