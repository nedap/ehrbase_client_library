package org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MethodDefiningcode implements EnumValueSet {
  PALPATION("Palpation", "Method of measuring blood pressure externally, using palpation (usually of the brachial or radial arteries).", "local", "at1038"),

  AUSCULTATION("Auscultation", "Method of measuring blood pressure externally, using a stethoscope and Korotkoff sounds.", "local", "at1037"),

  INVASIVE("Invasive", "Method of measuring blood pressure internally ie involving penetration of the skin and measuring inside blood vessels.", "local", "at1041"),

  MACHINE("Machine", "Method of measuring blood pressure externally, using a blood pressure machine.", "local", "at1040");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MethodDefiningcode(String value, String description, String terminologyId, String code) {
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
