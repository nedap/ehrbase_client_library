package org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.CuffSizeDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.D24HourAverageDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.DiastolicEndpointDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.LocationOfMeasurementDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.MethodDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.OpenEHREHROBSERVATIONBloodPressureV110AnyEventChoice;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.PositionDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.SleepStatusDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("id1")
@Template("openEHR-EHR-OBSERVATION.blood_pressure.v1.1.0")
public class OpenEHREHROBSERVATIONBloodPressureV110Observation {
  @Id
  private VersionUid versionUid;

  @Path("/data[id2]/events[id1043]/state[id1072]/items[id1006]/value[id1068]|magnitude")
  private Double tiltMagnitude;

  @Path("/data[id2]/events[id1043]/state[id1072]/items[id1006]/value[id1068]|units")
  private String tiltUnits;

  @Path("/protocol[id12]/items[id1055]/value[id1079]|value")
  private String systolicPressureFormulaValue;

  @Path("/protocol[id12]/items[id14]/value[id1073]|defining_code")
  private CuffSizeDefiningcode cuffSizeDefiningcode;

  @Path("/protocol[id12]/items[id1034]/items[id15]/value[id1074]|defining_code")
  private LocationOfMeasurementDefiningcode locationOfMeasurementDefiningcode;

  @Path("/data[id2]/origin|value")
  private TemporalAccessor originValue;

  @Path("/protocol[id12]/items[id1056]/value[id1080]|value")
  private String diastolicPressureFormulaValue;

  @Path("/protocol[id12]/items[id1011]/value[id1081]|defining_code")
  private DiastolicEndpointDefiningcode diastolicEndpointDefiningcode;

  @Path("/protocol[id12]/items[id1059]")
  private List<Cluster> extension;

  @Path("/data[id2]/events[id1043]/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[id2]/events[id1043]/math_function[id1069]|defining_code")
  private D24HourAverageDefiningcode d24HourAverageDefiningcode;

  @Path("/data[id2]/events[id1043]/state[id1072]/items[id1053]/value[id1066]|value")
  private String confoundingFactorsValue;

  @Path("/protocol[id12]/items[id1039]/value[id1078]|value")
  private String meanArterialPressureFormulaValue;

  @Path("/data[id2]/events[id1043]/data[id1071]/items[id34]/value[id1064]|value")
  private String commentValue;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[id2]/events[id1043]/data[id1071]/items[id1008]/value[id1063]|magnitude")
  private Double pulsePressureMagnitude;

  @Path("/data[id2]/events[id1043]/data[id1071]/items[id1008]/value[id1063]|units")
  private String pulsePressureUnits;

  @Path("/data[id2]/events[id1043]/data[id1071]/items[id6]/value[id1061]|magnitude")
  private Double diastolicMagnitude;

  @Path("/data[id2]/events[id1043]/data[id1071]/items[id6]/value[id1061]|units")
  private String diastolicUnits;

  @Path("/protocol[id12]/items[id1036]/value[id1077]|defining_code")
  private MethodDefiningcode methodDefiningcode;

  @Path("/data[id2]/events[id1043]/state[id1072]/items[id1031]")
  private Cluster exertion;

  @Path("/language")
  private Language language;

  @Path("/data[id2]/events[id1043]/width[id1070]|value")
  private TemporalAmount d24HourAverageValue;

  @Path("/protocol[id12]/items[id1034]/items[id15]/value[id1075]|value")
  private String locationOfMeasurementValue;

  @Path("/protocol[id12]/items[id1058]")
  private List<Cluster> structuredMeasurementLocation;

  @Path("/protocol[id12]/items[id1026]")
  private Cluster device;

  @Path("/data[id2]/events[id1043]/data[id1071]/items[id1007]/value[id1062]|magnitude")
  private Double meanArterialPressureMagnitude;

  @Path("/data[id2]/events[id1043]/data[id1071]/items[id1007]/value[id1062]|units")
  private String meanArterialPressureUnits;

  @Path("/data[id2]/events[id1043]/state[id1072]/items[id1044]/value[id1067]|defining_code")
  private SleepStatusDefiningcode sleepStatusDefiningcode;

  @Path("/data[id2]/events[id1043]/state[id1072]/items[id9]/value[id1065]|defining_code")
  private PositionDefiningcode positionDefiningcode;

  @Path("/data[id2]/events[id1043]/data[id1071]/items[id5]/value[id1060]|magnitude")
  private Double systolicMagnitude;

  @Path("/data[id2]/events[id1043]/data[id1071]/items[id5]/value[id1060]|units")
  private String systolicUnits;

  @Path("/protocol[id12]/items[id1034]/items[id1035]/value[id1076]|value")
  private String xSpecificLocationValue;

  @Path("/data[id2]/events[id7]")
  @Choice
  private List<OpenEHREHROBSERVATIONBloodPressureV110AnyEventChoice> anyEvent;

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }

  public void setTiltMagnitude(Double tiltMagnitude) {
     this.tiltMagnitude = tiltMagnitude;
  }

  public Double getTiltMagnitude() {
     return this.tiltMagnitude ;
  }

  public void setTiltUnits(String tiltUnits) {
     this.tiltUnits = tiltUnits;
  }

  public String getTiltUnits() {
     return this.tiltUnits ;
  }

  public void setSystolicPressureFormulaValue(String systolicPressureFormulaValue) {
     this.systolicPressureFormulaValue = systolicPressureFormulaValue;
  }

  public String getSystolicPressureFormulaValue() {
     return this.systolicPressureFormulaValue ;
  }

  public void setCuffSizeDefiningcode(CuffSizeDefiningcode cuffSizeDefiningcode) {
     this.cuffSizeDefiningcode = cuffSizeDefiningcode;
  }

  public CuffSizeDefiningcode getCuffSizeDefiningcode() {
     return this.cuffSizeDefiningcode ;
  }

  public void setLocationOfMeasurementDefiningcode(
      LocationOfMeasurementDefiningcode locationOfMeasurementDefiningcode) {
     this.locationOfMeasurementDefiningcode = locationOfMeasurementDefiningcode;
  }

  public LocationOfMeasurementDefiningcode getLocationOfMeasurementDefiningcode() {
     return this.locationOfMeasurementDefiningcode ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setDiastolicPressureFormulaValue(String diastolicPressureFormulaValue) {
     this.diastolicPressureFormulaValue = diastolicPressureFormulaValue;
  }

  public String getDiastolicPressureFormulaValue() {
     return this.diastolicPressureFormulaValue ;
  }

  public void setDiastolicEndpointDefiningcode(
      DiastolicEndpointDefiningcode diastolicEndpointDefiningcode) {
     this.diastolicEndpointDefiningcode = diastolicEndpointDefiningcode;
  }

  public DiastolicEndpointDefiningcode getDiastolicEndpointDefiningcode() {
     return this.diastolicEndpointDefiningcode ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setD24HourAverageDefiningcode(D24HourAverageDefiningcode d24HourAverageDefiningcode) {
     this.d24HourAverageDefiningcode = d24HourAverageDefiningcode;
  }

  public D24HourAverageDefiningcode getD24HourAverageDefiningcode() {
     return this.d24HourAverageDefiningcode ;
  }

  public void setConfoundingFactorsValue(String confoundingFactorsValue) {
     this.confoundingFactorsValue = confoundingFactorsValue;
  }

  public String getConfoundingFactorsValue() {
     return this.confoundingFactorsValue ;
  }

  public void setMeanArterialPressureFormulaValue(String meanArterialPressureFormulaValue) {
     this.meanArterialPressureFormulaValue = meanArterialPressureFormulaValue;
  }

  public String getMeanArterialPressureFormulaValue() {
     return this.meanArterialPressureFormulaValue ;
  }

  public void setCommentValue(String commentValue) {
     this.commentValue = commentValue;
  }

  public String getCommentValue() {
     return this.commentValue ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setPulsePressureMagnitude(Double pulsePressureMagnitude) {
     this.pulsePressureMagnitude = pulsePressureMagnitude;
  }

  public Double getPulsePressureMagnitude() {
     return this.pulsePressureMagnitude ;
  }

  public void setPulsePressureUnits(String pulsePressureUnits) {
     this.pulsePressureUnits = pulsePressureUnits;
  }

  public String getPulsePressureUnits() {
     return this.pulsePressureUnits ;
  }

  public void setDiastolicMagnitude(Double diastolicMagnitude) {
     this.diastolicMagnitude = diastolicMagnitude;
  }

  public Double getDiastolicMagnitude() {
     return this.diastolicMagnitude ;
  }

  public void setDiastolicUnits(String diastolicUnits) {
     this.diastolicUnits = diastolicUnits;
  }

  public String getDiastolicUnits() {
     return this.diastolicUnits ;
  }

  public void setMethodDefiningcode(MethodDefiningcode methodDefiningcode) {
     this.methodDefiningcode = methodDefiningcode;
  }

  public MethodDefiningcode getMethodDefiningcode() {
     return this.methodDefiningcode ;
  }

  public void setExertion(Cluster exertion) {
     this.exertion = exertion;
  }

  public Cluster getExertion() {
     return this.exertion ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setD24HourAverageValue(TemporalAmount d24HourAverageValue) {
     this.d24HourAverageValue = d24HourAverageValue;
  }

  public TemporalAmount getD24HourAverageValue() {
     return this.d24HourAverageValue ;
  }

  public void setLocationOfMeasurementValue(String locationOfMeasurementValue) {
     this.locationOfMeasurementValue = locationOfMeasurementValue;
  }

  public String getLocationOfMeasurementValue() {
     return this.locationOfMeasurementValue ;
  }

  public void setStructuredMeasurementLocation(List<Cluster> structuredMeasurementLocation) {
     this.structuredMeasurementLocation = structuredMeasurementLocation;
  }

  public List<Cluster> getStructuredMeasurementLocation() {
     return this.structuredMeasurementLocation ;
  }

  public void setDevice(Cluster device) {
     this.device = device;
  }

  public Cluster getDevice() {
     return this.device ;
  }

  public void setMeanArterialPressureMagnitude(Double meanArterialPressureMagnitude) {
     this.meanArterialPressureMagnitude = meanArterialPressureMagnitude;
  }

  public Double getMeanArterialPressureMagnitude() {
     return this.meanArterialPressureMagnitude ;
  }

  public void setMeanArterialPressureUnits(String meanArterialPressureUnits) {
     this.meanArterialPressureUnits = meanArterialPressureUnits;
  }

  public String getMeanArterialPressureUnits() {
     return this.meanArterialPressureUnits ;
  }

  public void setSleepStatusDefiningcode(SleepStatusDefiningcode sleepStatusDefiningcode) {
     this.sleepStatusDefiningcode = sleepStatusDefiningcode;
  }

  public SleepStatusDefiningcode getSleepStatusDefiningcode() {
     return this.sleepStatusDefiningcode ;
  }

  public void setPositionDefiningcode(PositionDefiningcode positionDefiningcode) {
     this.positionDefiningcode = positionDefiningcode;
  }

  public PositionDefiningcode getPositionDefiningcode() {
     return this.positionDefiningcode ;
  }

  public void setSystolicMagnitude(Double systolicMagnitude) {
     this.systolicMagnitude = systolicMagnitude;
  }

  public Double getSystolicMagnitude() {
     return this.systolicMagnitude ;
  }

  public void setSystolicUnits(String systolicUnits) {
     this.systolicUnits = systolicUnits;
  }

  public String getSystolicUnits() {
     return this.systolicUnits ;
  }

  public void setXSpecificLocationValue(String xSpecificLocationValue) {
     this.xSpecificLocationValue = xSpecificLocationValue;
  }

  public String getXSpecificLocationValue() {
     return this.xSpecificLocationValue ;
  }

  public void setAnyEvent(List<OpenEHREHROBSERVATIONBloodPressureV110AnyEventChoice> anyEvent) {
     this.anyEvent = anyEvent;
  }

  public List<OpenEHREHROBSERVATIONBloodPressureV110AnyEventChoice> getAnyEvent() {
     return this.anyEvent ;
  }
}
