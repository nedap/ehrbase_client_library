package org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.MathFunctionDefiningcode;

@Entity
@OptionFor("INTERVAL_EVENT")
public class OpenEHREHROBSERVATIONBloodPressureV110AnyEventIntervalEvent implements OpenEHREHROBSERVATIONBloodPressureV110AnyEventChoice {
  @Path("/data[id4]/items[id5]/value[id1060]|magnitude")
  private Double systolicMagnitude;

  @Path("/data[id4]/items[id5]/value[id1060]|units")
  private String systolicUnits;

  @Path("/state[id8]/items[id1031]")
  private Cluster exertion;

  @Path("/data[id4]/items[id1007]/value[id1062]|magnitude")
  private Double meanArterialPressureMagnitude;

  @Path("/data[id4]/items[id1007]/value[id1062]|units")
  private String meanArterialPressureUnits;

  @Path("/state[id8]/items[id1053]/value[id1066]|value")
  private String confoundingFactorsValue;

  @Path("/width|value")
  private TemporalAmount widthValue;

  @Path("/state[id8]/items[id1006]/value[id1068]|magnitude")
  private Double tiltMagnitude;

  @Path("/state[id8]/items[id1006]/value[id1068]|units")
  private String tiltUnits;

  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[id4]/items[id1008]/value[id1063]|magnitude")
  private Double pulsePressureMagnitude;

  @Path("/data[id4]/items[id1008]/value[id1063]|units")
  private String pulsePressureUnits;

  @Path("/data[id4]/items[id6]/value[id1061]|magnitude")
  private Double diastolicMagnitude;

  @Path("/data[id4]/items[id6]/value[id1061]|units")
  private String diastolicUnits;

  @Path("/state[id8]/items[id9]/value[id1065]|defining_code")
  private PositionDefiningcode positionDefiningcode;

  @Path("/data[id4]/items[id34]/value[id1064]|value")
  private String commentValue;

  @Path("/state[id8]/items[id1044]/value[id1067]|defining_code")
  private SleepStatusDefiningcode sleepStatusDefiningcode;

  @Path("/math_function|defining_code")
  private MathFunctionDefiningcode mathFunctionDefiningcode;

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

  public void setExertion(Cluster exertion) {
     this.exertion = exertion;
  }

  public Cluster getExertion() {
     return this.exertion ;
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

  public void setConfoundingFactorsValue(String confoundingFactorsValue) {
     this.confoundingFactorsValue = confoundingFactorsValue;
  }

  public String getConfoundingFactorsValue() {
     return this.confoundingFactorsValue ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
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

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
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

  public void setPositionDefiningcode(PositionDefiningcode positionDefiningcode) {
     this.positionDefiningcode = positionDefiningcode;
  }

  public PositionDefiningcode getPositionDefiningcode() {
     return this.positionDefiningcode ;
  }

  public void setCommentValue(String commentValue) {
     this.commentValue = commentValue;
  }

  public String getCommentValue() {
     return this.commentValue ;
  }

  public void setSleepStatusDefiningcode(SleepStatusDefiningcode sleepStatusDefiningcode) {
     this.sleepStatusDefiningcode = sleepStatusDefiningcode;
  }

  public SleepStatusDefiningcode getSleepStatusDefiningcode() {
     return this.sleepStatusDefiningcode ;
  }

  public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
     this.mathFunctionDefiningcode = mathFunctionDefiningcode;
  }

  public MathFunctionDefiningcode getMathFunctionDefiningcode() {
     return this.mathFunctionDefiningcode ;
  }
}
