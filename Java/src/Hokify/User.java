package Hokify;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  public String Name = "";

  public void cg_init_User_1(final String name) {

    Name = name;
  }

  public User(final String name) {

    cg_init_User_1(name);
  }

  public void changeName(final String name) {

    Name = name;
  }

  public User() {}

  public String toString() {

    return "User{" + "Name := " + Utils.toString(Name) + "}";
  }
}
