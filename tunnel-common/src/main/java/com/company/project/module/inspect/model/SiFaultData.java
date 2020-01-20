package com.company.project.module.inspect.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "J302_SI_FAULT_DATA")
public class SiFaultData {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "LINE_CODE")
    private String lineCode;

    @Column(name = "INTERNAL_CODE")
    private String internalCode;

    @Column(name = "END_KI_MILEAGE")
    private Integer endKiMileage;

    @Column(name = "DEVICE_TYPE")
    private String deviceType;

    @Column(name = "FAULT_TYPE1")
    private String faultType1;

    @Column(name = "FAULT_TYPE2")
    private String faultType2;

    @Column(name = "FAULT_UNIT")
    private String faultUnit;

    @Column(name = "FAULT_TYPE3")
    private String faultType3;

    @Column(name = "FAULT_QUANTITY")
    private String faultQuantity;

    @Column(name = "FAULT_LEVEL")
    private String faultLevel;

    @Column(name = "AFFECTE_EQUIPMENT")
    private String affecteEquipment;

    @Column(name = "LEVEL3")
    private String level3;

    @Column(name = "START_KI_MILEAGE")
    private Integer startKiMileage;

    @Column(name = "INSERT_DATE")
    private Date insertDate;

    @Column(name = "IS_FINISH")
    private String isFinish;

    @Column(name = "DEVICE_UUID")
    private String deviceUuid;

    @Column(name = "DUCT_COUNT")
    private Integer ductCount;

    @Column(name = "START_DUCT_CODE")
    private Integer startDuctCode;

    @Column(name = "START_HUN_MILEAGE")
    private BigDecimal startHunMileage;

    @Column(name = "START_MILEAGE_FLAG")
    private String startMileageFlag;

    @Column(name = "END_DUCT_CODE")
    private Integer endDuctCode;

    @Column(name = "END_HUN_MILEAGE")
    private BigDecimal endHunMileage;

    @Column(name = "END_MILEAGE_FLAG")
    private String endMileageFlag;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "FAULT_DESC")
    private String faultDesc;

    @Column(name = "OPT_DESC")
    private String optDesc;

    @Column(name = "OPT_DATE")
    private Date optDate;

    @Column(name = "FAULT_STATUS")
    private String faultStatus;

    @Column(name = "LEVEL4")
    private String level4;

    @Column(name = "OPERATOR_NO")
    private String operatorNo;

    @Column(name = "PLAN_TYPE")
    private String planType;

    @Column(name = "PLAN_UUID")
    private String planUuid;

    @Column(name = "UPDOWN")
    private String updown;

    @Column(name = "IS_CHECK")
    private String isCheck;

    @Column(name = "MATERIAL_CONSUMPTION")
    private String materialConsumption;

    @Column(name = "REPAIR_DESC")
    private String repairDesc;

    /**
     * @return UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return LINE_CODE
     */
    public String getLineCode() {
        return lineCode;
    }

    /**
     * @param lineCode
     */
    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    /**
     * @return INTERNAL_CODE
     */
    public String getInternalCode() {
        return internalCode;
    }

    /**
     * @param internalCode
     */
    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    /**
     * @return END_KI_MILEAGE
     */
    public Integer getEndKiMileage() {
        return endKiMileage;
    }

    /**
     * @param endKiMileage
     */
    public void setEndKiMileage(Integer endKiMileage) {
        this.endKiMileage = endKiMileage;
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
     * @return FAULT_TYPE1
     */
    public String getFaultType1() {
        return faultType1;
    }

    /**
     * @param faultType1
     */
    public void setFaultType1(String faultType1) {
        this.faultType1 = faultType1;
    }

    /**
     * @return FAULT_TYPE2
     */
    public String getFaultType2() {
        return faultType2;
    }

    /**
     * @param faultType2
     */
    public void setFaultType2(String faultType2) {
        this.faultType2 = faultType2;
    }

    /**
     * @return FAULT_UNIT
     */
    public String getFaultUnit() {
        return faultUnit;
    }

    /**
     * @param faultUnit
     */
    public void setFaultUnit(String faultUnit) {
        this.faultUnit = faultUnit;
    }

    /**
     * @return FAULT_TYPE3
     */
    public String getFaultType3() {
        return faultType3;
    }

    /**
     * @param faultType3
     */
    public void setFaultType3(String faultType3) {
        this.faultType3 = faultType3;
    }

    /**
     * @return FAULT_QUANTITY
     */
    public String getFaultQuantity() {
        return faultQuantity;
    }

    /**
     * @param faultQuantity
     */
    public void setFaultQuantity(String faultQuantity) {
        this.faultQuantity = faultQuantity;
    }

    /**
     * @return FAULT_LEVEL
     */
    public String getFaultLevel() {
        return faultLevel;
    }

    /**
     * @param faultLevel
     */
    public void setFaultLevel(String faultLevel) {
        this.faultLevel = faultLevel;
    }

    /**
     * @return AFFECTE_EQUIPMENT
     */
    public String getAffecteEquipment() {
        return affecteEquipment;
    }

    /**
     * @param affecteEquipment
     */
    public void setAffecteEquipment(String affecteEquipment) {
        this.affecteEquipment = affecteEquipment;
    }

    /**
     * @return LEVEL3
     */
    public String getLevel3() {
        return level3;
    }

    /**
     * @param level3
     */
    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    /**
     * @return START_KI_MILEAGE
     */
    public Integer getStartKiMileage() {
        return startKiMileage;
    }

    /**
     * @param startKiMileage
     */
    public void setStartKiMileage(Integer startKiMileage) {
        this.startKiMileage = startKiMileage;
    }

    /**
     * @return INSERT_DATE
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * @param insertDate
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * @return IS_FINISH
     */
    public String getIsFinish() {
        return isFinish;
    }

    /**
     * @param isFinish
     */
    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    /**
     * @return DEVICE_UUID
     */
    public String getDeviceUuid() {
        return deviceUuid;
    }

    /**
     * @param deviceUuid
     */
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    /**
     * @return DUCT_COUNT
     */
    public Integer getDuctCount() {
        return ductCount;
    }

    /**
     * @param ductCount
     */
    public void setDuctCount(Integer ductCount) {
        this.ductCount = ductCount;
    }

    /**
     * @return START_DUCT_CODE
     */
    public Integer getStartDuctCode() {
        return startDuctCode;
    }

    /**
     * @param startDuctCode
     */
    public void setStartDuctCode(Integer startDuctCode) {
        this.startDuctCode = startDuctCode;
    }

    /**
     * @return START_HUN_MILEAGE
     */
    public BigDecimal getStartHunMileage() {
        return startHunMileage;
    }

    /**
     * @param startHunMileage
     */
    public void setStartHunMileage(BigDecimal startHunMileage) {
        this.startHunMileage = startHunMileage;
    }

    /**
     * @return START_MILEAGE_FLAG
     */
    public String getStartMileageFlag() {
        return startMileageFlag;
    }

    /**
     * @param startMileageFlag
     */
    public void setStartMileageFlag(String startMileageFlag) {
        this.startMileageFlag = startMileageFlag;
    }

    /**
     * @return END_DUCT_CODE
     */
    public Integer getEndDuctCode() {
        return endDuctCode;
    }

    /**
     * @param endDuctCode
     */
    public void setEndDuctCode(Integer endDuctCode) {
        this.endDuctCode = endDuctCode;
    }

    /**
     * @return END_HUN_MILEAGE
     */
    public BigDecimal getEndHunMileage() {
        return endHunMileage;
    }

    /**
     * @param endHunMileage
     */
    public void setEndHunMileage(BigDecimal endHunMileage) {
        this.endHunMileage = endHunMileage;
    }

    /**
     * @return END_MILEAGE_FLAG
     */
    public String getEndMileageFlag() {
        return endMileageFlag;
    }

    /**
     * @param endMileageFlag
     */
    public void setEndMileageFlag(String endMileageFlag) {
        this.endMileageFlag = endMileageFlag;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return FAULT_DESC
     */
    public String getFaultDesc() {
        return faultDesc;
    }

    /**
     * @param faultDesc
     */
    public void setFaultDesc(String faultDesc) {
        this.faultDesc = faultDesc;
    }

    /**
     * @return OPT_DESC
     */
    public String getOptDesc() {
        return optDesc;
    }

    /**
     * @param optDesc
     */
    public void setOptDesc(String optDesc) {
        this.optDesc = optDesc;
    }

    /**
     * @return OPT_DATE
     */
    public Date getOptDate() {
        return optDate;
    }

    /**
     * @param optDate
     */
    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    /**
     * @return FAULT_STATUS
     */
    public String getFaultStatus() {
        return faultStatus;
    }

    /**
     * @param faultStatus
     */
    public void setFaultStatus(String faultStatus) {
        this.faultStatus = faultStatus;
    }

    /**
     * @return LEVEL4
     */
    public String getLevel4() {
        return level4;
    }

    /**
     * @param level4
     */
    public void setLevel4(String level4) {
        this.level4 = level4;
    }

    /**
     * @return OPERATOR_NO
     */
    public String getOperatorNo() {
        return operatorNo;
    }

    /**
     * @param operatorNo
     */
    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    /**
     * @return PLAN_TYPE
     */
    public String getPlanType() {
        return planType;
    }

    /**
     * @param planType
     */
    public void setPlanType(String planType) {
        this.planType = planType;
    }

    /**
     * @return PLAN_UUID
     */
    public String getPlanUuid() {
        return planUuid;
    }

    /**
     * @param planUuid
     */
    public void setPlanUuid(String planUuid) {
        this.planUuid = planUuid;
    }

    /**
     * @return UPDOWN
     */
    public String getUpdown() {
        return updown;
    }

    /**
     * @param updown
     */
    public void setUpdown(String updown) {
        this.updown = updown;
    }

    /**
     * @return IS_CHECK
     */
    public String getIsCheck() {
        return isCheck;
    }

    /**
     * @param isCheck
     */
    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    /**
     * @return MATERIAL_CONSUMPTION
     */
    public String getMaterialConsumption() {
        return materialConsumption;
    }

    /**
     * @param materialConsumption
     */
    public void setMaterialConsumption(String materialConsumption) {
        this.materialConsumption = materialConsumption;
    }

    /**
     * @return REPAIR_DESC
     */
    public String getRepairDesc() {
        return repairDesc;
    }

    /**
     * @param repairDesc
     */
    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }
}