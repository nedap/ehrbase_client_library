package org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum PositionDefiningcode implements EnumValueSet {
  SITTING("Sitting", "Sitting (for example on bed or chair) at the time of blood pressure measurement.", "local", "at1002"),

  LYINGWITHTILTTOLEFT("Lying with tilt to left", "Lying flat with some lateral tilt, usually angled towards the left side.   Commonly required in the last trimester of pregnancy to relieve aortocaval compression.", "local", "at1015"),

  LYING("Lying", "Lying flat at the time of blood pressure measurement.", "local", "at1004"),

  RECLINING("Reclining", "Reclining at the time of blood pressure measurement.", "local", "at1003"),

  STANDING("Standing", "Standing at the time of blood pressure measurement.", "local", "at1001");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  PositionDefiningcode(String value, String description, String terminologyId, String code) {
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
