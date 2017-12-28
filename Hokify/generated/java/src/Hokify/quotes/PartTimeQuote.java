package Hokify.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PartTimeQuote {
  private static int hc = 0;
  private static PartTimeQuote instance = null;

  public PartTimeQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static PartTimeQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new PartTimeQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof PartTimeQuote;
  }

  public String toString() {

    return "<PartTime>";
  }
}
