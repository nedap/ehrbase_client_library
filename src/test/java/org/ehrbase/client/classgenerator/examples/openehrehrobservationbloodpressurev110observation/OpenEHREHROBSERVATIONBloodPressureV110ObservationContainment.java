package org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.CuffSizeDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.D24HourAverageDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.DiastolicEndpointDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.LocationOfMeasurementDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.MethodDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.OpenEHREHROBSERVATIONBloodPressureV110AnyEventChoice;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.PositionDefiningcode;
import org.ehrbase.client.classgenerator.examples.openehrehrobservationbloodpressurev110observation.definition.SleepStatusDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class OpenEHREHROBSERVATIONBloodPressureV110ObservationContainment extends Containment {
  public SelectAqlField<OpenEHREHROBSERVATIONBloodPressureV110Observation> OPEN_E_H_R_E_H_R_O_B_S_E_R_V_A_T_I_O_N_BLOOD_PRESSURE_V110_OBSERVATION = new AqlFieldImp<OpenEHREHROBSERVATIONBloodPressureV110Observation>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "", "OpenEHREHROBSERVATIONBloodPressureV110Observation", OpenEHREHROBSERVATIONBloodPressureV110Observation.class, this);

  public SelectAqlField<Double> TILT_MAGNITUDE = new AqlFieldImp<Double>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/state[id1072]/items[id1006]/value[id1068]|magnitude", "tiltMagnitude", Double.class, this);

  public SelectAqlField<String> TILT_UNITS = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/state[id1072]/items[id1006]/value[id1068]|units", "tiltUnits", String.class, this);

  public SelectAqlField<String> SYSTOLIC_PRESSURE_FORMULA_VALUE = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1055]/value[id1079]|value", "systolicPressureFormulaValue", String.class, this);

  public SelectAqlField<CuffSizeDefiningcode> CUFF_SIZE_DEFININGCODE = new AqlFieldImp<CuffSizeDefiningcode>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id14]/value[id1073]|defining_code", "cuffSizeDefiningcode", CuffSizeDefiningcode.class, this);

  public SelectAqlField<LocationOfMeasurementDefiningcode> LOCATION_OF_MEASUREMENT_DEFININGCODE = new AqlFieldImp<LocationOfMeasurementDefiningcode>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1034]/items[id15]/value[id1074]|defining_code", "locationOfMeasurementDefiningcode", LocationOfMeasurementDefiningcode.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<String> DIASTOLIC_PRESSURE_FORMULA_VALUE = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1056]/value[id1080]|value", "diastolicPressureFormulaValue", String.class, this);

  public SelectAqlField<DiastolicEndpointDefiningcode> DIASTOLIC_ENDPOINT_DEFININGCODE = new AqlFieldImp<DiastolicEndpointDefiningcode>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1011]/value[id1081]|defining_code", "diastolicEndpointDefiningcode", DiastolicEndpointDefiningcode.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1059]", "extension", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<D24HourAverageDefiningcode> D24_HOUR_AVERAGE_DEFININGCODE = new AqlFieldImp<D24HourAverageDefiningcode>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/math_function[id1069]|defining_code", "d24HourAverageDefiningcode", D24HourAverageDefiningcode.class, this);

  public SelectAqlField<String> CONFOUNDING_FACTORS_VALUE = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/state[id1072]/items[id1053]/value[id1066]|value", "confoundingFactorsValue", String.class, this);

  public SelectAqlField<String> MEAN_ARTERIAL_PRESSURE_FORMULA_VALUE = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1039]/value[id1078]|value", "meanArterialPressureFormulaValue", String.class, this);

  public SelectAqlField<String> COMMENT_VALUE = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/data[id1071]/items[id34]/value[id1064]|value", "commentValue", String.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Double> PULSE_PRESSURE_MAGNITUDE = new AqlFieldImp<Double>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/data[id1071]/items[id1008]/value[id1063]|magnitude", "pulsePressureMagnitude", Double.class, this);

  public SelectAqlField<String> PULSE_PRESSURE_UNITS = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/data[id1071]/items[id1008]/value[id1063]|units", "pulsePressureUnits", String.class, this);

  public SelectAqlField<Double> DIASTOLIC_MAGNITUDE = new AqlFieldImp<Double>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/data[id1071]/items[id6]/value[id1061]|magnitude", "diastolicMagnitude", Double.class, this);

  public SelectAqlField<String> DIASTOLIC_UNITS = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/data[id1071]/items[id6]/value[id1061]|units", "diastolicUnits", String.class, this);

  public SelectAqlField<MethodDefiningcode> METHOD_DEFININGCODE = new AqlFieldImp<MethodDefiningcode>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1036]/value[id1077]|defining_code", "methodDefiningcode", MethodDefiningcode.class, this);

  public SelectAqlField<Cluster> EXERTION = new AqlFieldImp<Cluster>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/state[id1072]/items[id1031]", "exertion", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/language", "language", Language.class, this);

  public SelectAqlField<TemporalAmount> D24_HOUR_AVERAGE_VALUE = new AqlFieldImp<TemporalAmount>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/width[id1070]|value", "d24HourAverageValue", TemporalAmount.class, this);

  public SelectAqlField<String> LOCATION_OF_MEASUREMENT_VALUE = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1034]/items[id15]/value[id1075]|value", "locationOfMeasurementValue", String.class, this);

  public ListSelectAqlField<Cluster> STRUCTURED_MEASUREMENT_LOCATION = new ListAqlFieldImp<Cluster>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1058]", "structuredMeasurementLocation", Cluster.class, this);

  public SelectAqlField<Cluster> DEVICE = new AqlFieldImp<Cluster>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1026]", "device", Cluster.class, this);

  public SelectAqlField<Double> MEAN_ARTERIAL_PRESSURE_MAGNITUDE = new AqlFieldImp<Double>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/data[id1071]/items[id1007]/value[id1062]|magnitude", "meanArterialPressureMagnitude", Double.class, this);

  public SelectAqlField<String> MEAN_ARTERIAL_PRESSURE_UNITS = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/data[id1071]/items[id1007]/value[id1062]|units", "meanArterialPressureUnits", String.class, this);

  public SelectAqlField<SleepStatusDefiningcode> SLEEP_STATUS_DEFININGCODE = new AqlFieldImp<SleepStatusDefiningcode>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/state[id1072]/items[id1044]/value[id1067]|defining_code", "sleepStatusDefiningcode", SleepStatusDefiningcode.class, this);

  public SelectAqlField<PositionDefiningcode> POSITION_DEFININGCODE = new AqlFieldImp<PositionDefiningcode>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/state[id1072]/items[id9]/value[id1065]|defining_code", "positionDefiningcode", PositionDefiningcode.class, this);

  public SelectAqlField<Double> SYSTOLIC_MAGNITUDE = new AqlFieldImp<Double>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/data[id1071]/items[id5]/value[id1060]|magnitude", "systolicMagnitude", Double.class, this);

  public SelectAqlField<String> SYSTOLIC_UNITS = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id1043]/data[id1071]/items[id5]/value[id1060]|units", "systolicUnits", String.class, this);

  public SelectAqlField<String> X_SPECIFIC_LOCATION_VALUE = new AqlFieldImp<String>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/protocol[id12]/items[id1034]/items[id1035]/value[id1076]|value", "xSpecificLocationValue", String.class, this);

  public ListSelectAqlField<OpenEHREHROBSERVATIONBloodPressureV110AnyEventChoice> ANY_EVENT = new ListAqlFieldImp<OpenEHREHROBSERVATIONBloodPressureV110AnyEventChoice>(OpenEHREHROBSERVATIONBloodPressureV110Observation.class, "/data[id2]/events[id7]", "anyEvent", OpenEHREHROBSERVATIONBloodPressureV110AnyEventChoice.class, this);

  private OpenEHREHROBSERVATIONBloodPressureV110ObservationContainment() {
    super("id1");
  }

  public static OpenEHREHROBSERVATIONBloodPressureV110ObservationContainment getInstance() {
    return new OpenEHREHROBSERVATIONBloodPressureV110ObservationContainment();
  }
}
