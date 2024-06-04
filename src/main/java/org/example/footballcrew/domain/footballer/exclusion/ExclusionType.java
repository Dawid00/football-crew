package org.example.footballcrew.domain.footballer.exclusion;

public enum ExclusionType {
  TIME("time"), STATE("state"), QUANTITATIVE("quantitative");

  public final String name;

  ExclusionType(String name) {
    this.name = name;
  }
}
