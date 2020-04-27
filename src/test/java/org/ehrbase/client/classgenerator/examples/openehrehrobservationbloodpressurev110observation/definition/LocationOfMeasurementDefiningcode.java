package org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum LocationOfMeasurementDefiningcode implements EnumValueSet {
  RIGHTANKLE("Right ankle", "The right ankle of the subject.", "local", "at1027"),

  LEFTTHIGH("Left thigh", "The left thigh of the person.", "local", "at29"),

  RIGHTWRIST("Right wrist", "The right wrist of the subject.", "local", "at1021"),

  TOE("Toe", "A toe of the subject.", "local", "at1052"),

  RIGHTARM("Right arm", "The right arm of the person.", "local", "at26"),

  LEFTARM("Left arm", "The left arm of the person.", "local", "at27"),

  INTRAARTERIAL("Intra-arterial", "Invasive measurement via transducer access line within an artery.", "local", "at1054"),

  LEFTANKLE("Left ankle", "The left ankle of the subject.", "local", "at1032"),

  RIGHTTHIGH("Right thigh", "The right thigh of the person.", "local", "at28"),

  FINGER("Finger", "A finger of the subject.", "local", "at1033"),

  LEFTWRIST("Left wrist", "The left wrist of the subject.", "local", "at1022");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  LocationOfMeasurementDefiningcode(String value, String description, String terminologyId,
      String code) {
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
