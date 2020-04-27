package org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SleepStatusDefiningcode implements EnumValueSet {
  ALERTAWAKE("Alert & awake", "Subject is fully conscious.", "local", "at1045"),

  SLEEPING("Sleeping", "Subject is in the natural state of bodily rest.", "local", "at1046");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SleepStatusDefiningcode(String value, String description, String terminologyId, String code) {
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
