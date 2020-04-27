package org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum CuffSizeDefiningcode implements EnumValueSet {
  INFANT("Infant", "A cuff used for infants.", "local", "at1019"),

  SMALLADULT("Small Adult", "A cuff used for a small adult.", "local", "at1009"),

  ADULTTHIGH("Adult Thigh", "A cuff used for an adult thigh.", "local", "at16"),

  NEONATAL("Neonatal", "A cuff used for a neonate, assuming cuff is the appropriate size for maturity and birthweight of the neonate.", "local", "at1020"),

  LARGEADULT("Large Adult", "A cuff for adults with larger arms.", "local", "at17"),

  CHILD("Paediatric/Child", "A cuff that is appropriate for a child or adult with a thin arm.", "local", "at1010"),

  ADULT("Adult", "A cuff that is standard for an adult.", "local", "at18");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  CuffSizeDefiningcode(String value, String description, String terminologyId, String code) {
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
