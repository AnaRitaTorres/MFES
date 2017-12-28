package Hokify.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FullTimeQuote {
  private static int hc = 0;
  private static FullTimeQuote instance = null;

  public FullTimeQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static FullTimeQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new FullTimeQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof FullTimeQuote;
  }

  public String toString() {

    return "<FullTime>";
  }
}
