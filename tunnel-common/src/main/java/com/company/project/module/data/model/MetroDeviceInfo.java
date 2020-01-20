package com.company.project.module.data.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "J302_METRO_DEVICE_INFO_GW")
public class MetroDeviceInfo {


    @Id
    @Column(name = "NID")
    private Long nid;

    @Column(name = "DEVICE_ID")
    private String deviceId;

    @Column(name = "LINE_ID")
    private String lineId;

    @Column(name = "START_STA")
    private String startSta;

    @Column(name = "END_STA")
    private String endSta;

    @Column(name = "DEVICE_TYPE")
    private String deviceType;

    @Column(name = "HIGH_LOW")
    private String highLow;

    @Column(name = "DEPT_ID")
    private String deptId;

    @Column(name = "DEVICE_NAME")
    private String deviceName;

    @Column(name = "XH")
    private String xh;

    @Column(name = "FACTORY")
    private String factory;

    @Column(name = "FACTORY_NUM")
    private String factoryNum;

    @Column(name = "FACTORY_TIME")
    private String factoryTime;

    @Column(name = "CAPACITY")
    private String capacity;

    @Column(name = "VOLTAGE")
    private String voltage;

    @Column(name = "ELECTRICITY")
    private String electricity;

    @Column(name = "IMPEDANCE")
    private String impedance;

    @Column(name = "CONNECTION")
    private String connection;

    @Column(name = "PLACE")
    private String place;

    @Column(name = "ADJUSTING")
    private String adjusting;

    @Column(name = "TH")
    private String th;

    @Column(name = "TUNNEL_NUM")
    private BigDecimal tunnelNum;

    @Column(name = "DEVICE_START_DIRECTION")
    private String deviceStartDirection;

    @Column(name = "DEVICE_START_KFLAG")
    private String deviceStartKflag;

    @Column(name = "DEVICE_START_HFLAG")
    private String deviceStartHflag;

    @Column(name = "DEVICE_END_DIRECTION")
    private String deviceEndDirection;

    @Column(name = "DEVICE_END_KFLAG")
    private String deviceEndKflag;

    @Column(name = "DEVICE_END_HFLAG")
    private String deviceEndHflag;

    @Column(name = "DEVICE_LOC")
    private String deviceLoc;

    @Column(name = "TRAIN_MODEL")
    private String trainModel;

    @Column(name = "TRAIN_ID")
    private String trainId;

    @Column(name = "PITCH_NUMBER")
    private String pitchNumber;

    @Column(name = "TRAIN_GROUP")
    private String trainGroup;

    @Column(name = "PITCH_NO")
    private String pitchNo;

    @Column(name = "SPECIAL_TYPE")
    private String specialType;

    @Column(name = "MAJOR")
    private String major;

    @Column(name = "SEGMENT_UUID")
    private String segmentUuid;

    /**
     * @return NID
     */
    public Long getNid() {
        return nid;
    }

    /**
     * @param nid
     */
    public void setNid(Long nid) {
        this.nid = nid;
    }

    /**
     * @return DEVICE_ID
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return LINE_ID
     */
    public String getLineId() {
        return lineId;
    }

    /**
     * @param lineId
     */
    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    /**
     * @return START_STA
     */
    public String getStartSta() {
        return startSta;
    }

    /**
     * @param startSta
     */
    public void setStartSta(String startSta) {
        this.startSta = startSta;
    }

    /**
     * @return END_STA
     */
    public String getEndSta() {
        return endSta;
    }

    /**
     * @param endSta
     */
    public void setEndSta(String endSta) {
        this.endSta = endSta;
    }

    /**
     * @return DEVICE_TYPE
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return HIGH_LOW
     */
    public String getHighLow() {
        return highLow;
    }

    /**
     * @param highLow
     */
    public void setHighLow(String highLow) {
        this.highLow = highLow;
    }

    /**
     * @return DEPT_ID
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * @return DEVICE_NAME
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * @return XH
     */
    public String getXh() {
        return xh;
    }

    /**
     * @param xh
     */
    public void setXh(String xh) {
        this.xh = xh;
    }

    /**
     * @return FACTORY
     */
    public String getFactory() {
        return factory;
    }

    /**
     * @param factory
     */
    public void setFactory(String factory) {
        this.factory = factory;
    }

    /**
     * @return FACTORY_NUM
     */
    public String getFactoryNum() {
        return factoryNum;
    }

    /**
     * @param factoryNum
     */
    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
    }

    /**
     * @return FACTORY_TIME
     */
    public String getFactoryTime() {
        return factoryTime;
    }

    /**
     * @param factoryTime
     */
    public void setFactoryTime(String factoryTime) {
        this.factoryTime = factoryTime;
    }

    /**
     * @return CAPACITY
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * @param capacity
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * @return VOLTAGE
     */
    public String getVoltage() {
        return voltage;
    }

    /**
     * @param voltage
     */
    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    /**
     * @return ELECTRICITY
     */
    public String getElectricity() {
        return electricity;
    }

    /**
     * @param electricity
     */
    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    /**
     * @return IMPEDANCE
     */
    public String getImpedance() {
        return impedance;
    }

    /**
     * @param impedance
     */
    public void setImpedance(String impedance) {
        this.impedance = impedance;
    }

    /**
     * @return CONNECTION
     */
    public String getConnection() {
        return connection;
    }

    /**
     * @param connection
     */
    public void setConnection(String connection) {
        this.connection = connection;
    }

    /**
     * @return PLACE
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return ADJUSTING
     */
    public String getAdjusting() {
        return adjusting;
    }

    /**
     * @param adjusting
     */
    public void setAdjusting(String adjusting) {
        this.adjusting = adjusting;
    }

    /**
     * @return TH
     */
    public String getTh() {
        return th;
    }

    /**
     * @param th
     */
    public void setTh(String th) {
        this.th = th;
    }

    /**
     * @return TUNNEL_NUM
     */
    public BigDecimal getTunnelNum() {
        return tunnelNum;
    }

    /**
     * @param tunnelNum
     */
    public void setTunnelNum(BigDecimal tunnelNum) {
        this.tunnelNum = tunnelNum;
    }

    /**
     * @return DEVICE_START_DIRECTION
     */
    public String getDeviceStartDirection() {
        return deviceStartDirection;
    }

    /**
     * @param deviceStartDirection
     */
    public void setDeviceStartDirection(String deviceStartDirection) {
        this.deviceStartDirection = deviceStartDirection;
    }

    /**
     * @return DEVICE_START_KFLAG
     */
    public String getDeviceStartKflag() {
        return deviceStartKflag;
    }

    /**
     * @param deviceStartKflag
     */
    public void setDeviceStartKflag(String deviceStartKflag) {
        this.deviceStartKflag = deviceStartKflag;
    }

    /**
     * @return DEVICE_START_HFLAG
     */
    public String getDeviceStartHflag() {
        return deviceStartHflag;
    }

    /**
     * @param deviceStartHflag
     */
    public void setDeviceStartHflag(String deviceStartHflag) {
        this.deviceStartHflag = deviceStartHflag;
    }

    /**
     * @return DEVICE_END_DIRECTION
     */
    public String getDeviceEndDirection() {
        return deviceEndDirection;
    }

    /**
     * @param deviceEndDirection
     */
    public void setDeviceEndDirection(String deviceEndDirection) {
        this.deviceEndDirection = deviceEndDirection;
    }

    /**
     * @return DEVICE_END_KFLAG
     */
    public String getDeviceEndKflag() {
        return deviceEndKflag;
    }

    /**
     * @param deviceEndKflag
     */
    public void setDeviceEndKflag(String deviceEndKflag) {
        this.deviceEndKflag = deviceEndKflag;
    }

    /**
     * @return DEVICE_END_HFLAG
     */
    public String getDeviceEndHflag() {
        return deviceEndHflag;
    }

    /**
     * @param deviceEndHflag
     */
    public void setDeviceEndHflag(String deviceEndHflag) {
        this.deviceEndHflag = deviceEndHflag;
    }

    /**
     * @return DEVICE_LOC
     */
    public String getDeviceLoc() {
        return deviceLoc;
    }

    /**
     * @param deviceLoc
     */
    public void setDeviceLoc(String deviceLoc) {
        this.deviceLoc = deviceLoc;
    }

    /**
     * @return TRAIN_MODEL
     */
    public String getTrainModel() {
        return trainModel;
    }

    /**
     * @param trainModel
     */
    public void setTrainModel(String trainModel) {
        this.trainModel = trainModel;
    }

    /**
     * @return TRAIN_ID
     */
    public String getTrainId() {
        return trainId;
    }

    /**
     * @param trainId
     */
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    /**
     * @return PITCH_NUMBER
     */
    public String getPitchNumber() {
        return pitchNumber;
    }

    /**
     * @param pitchNumber
     */
    public void setPitchNumber(String pitchNumber) {
        this.pitchNumber = pitchNumber;
    }

    /**
     * @return TRAIN_GROUP
     */
    public String getTrainGroup() {
        return trainGroup;
    }

    /**
     * @param trainGroup
     */
    public void setTrainGroup(String trainGroup) {
        this.trainGroup = trainGroup;
    }

    /**
     * @return PITCH_NO
     */
    public String getPitchNo() {
        return pitchNo;
    }

    /**
     * @param pitchNo
     */
    public void setPitchNo(String pitchNo) {
        this.pitchNo = pitchNo;
    }

    /**
     * @return SPECIAL_TYPE
     */
    public String getSpecialType() {
        return specialType;
    }

    /**
     * @param specialType
     */
    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }

    /**
     * @return MAJOR
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * @return SEGMENT_UUID
     */
    public String getSegmentUuid() {
        return segmentUuid;
    }

    /**
     * @param segmentUuid
     */
    public void setSegmentUuid(String segmentUuid) {
        this.segmentUuid = segmentUuid;
    }
}