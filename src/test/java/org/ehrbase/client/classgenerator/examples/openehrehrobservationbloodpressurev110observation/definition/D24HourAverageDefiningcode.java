package org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum D24HourAverageDefiningcode implements EnumValueSet {
  ADDEDBYPOSTPARSEPROCESSOR("(added by post-parse processor)", "(added by post-parse processor)", "local", "at1057");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  D24HourAverageDefiningcode(String value, String description, String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
     return this.value ;
  }

  public String getDescription() {
     return this.description ;
  }

  public String getTerminologyId() {
     return this.terminologyId ;
  }

  public String getCode() {
     return this.code ;
  }
}
