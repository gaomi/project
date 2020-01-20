package com.company.project.module.data.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "J302_METRO_FAULT_LL")
public class MetroFaultLl {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "LINE_ID")
    private String lineId;

    @Column(name = "INTERNAL_CODE")
    private String internalCode;

    @Column(name = "START_STATION")
    private String startStation;

    @Column(name = "START_STATIONNAME")
    private String startStationname;

    @Column(name = "END_STATION")
    private String endStation;

    @Column(name = "END_STATIONNAME")
    private String endStationname;

    @Column(name = "DIRECTION")
    private String direction;

    @Column(name = "DEVICE_ID")
    private String deviceId;

    @Column(name = "DEVICE_NAME")
    private String deviceName;

    @Column(name = "DEVICE_TYPE")
    private String deviceType;

    @Column(name = "FAULT_NO")
    private String faultNo;

    @Column(name = "BIG_TYPE_ID")
    private String bigTypeId;

    @Column(name = "BIG_TYPE_NAME")
    private String bigTypeName;

    @Column(name = "SMALL_TYPE_ID")
    private String smallTypeId;

    @Column(name = "SMALL_TYPE_NAME")
    private String smallTypeName;

    @Column(name = "FAULT_TYPE")
    private String faultType;

    @Column(name = "EXCEPTION_TYPE_NAME")
    private String exceptionTypeName;

    @Column(name = "FAULT_LEVEL")
    private String faultLevel;

    @Column(name = "FAULT_DESC")
    private String faultDesc;

    @Column(name = "REC_CREATE_ID")
    private String recCreateId;

    @Column(name = "REC_CREATE_NAME")
    private String recCreateName;

    @Column(name = "REC_CREATE_TIME")
    private String recCreateTime;

    @Column(name = "REC_REVISE_ID")
    private String recReviseId;

    @Column(name = "REC_REVISE_NAME")
    private String recReviseName;

    @Column(name = "REC_REVISE_TIME")
    private String recReviseTime;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "LEFT_RIGHT")
    private String leftRight;

    @Column(name = "TRACK_NUM")
    private String trackNum;

    @Column(name = "STATION_FLAG")
    private String stationFlag;

    @Column(name = "STATION_KI_NUM")
    private String stationKiNum;

    @Column(name = "STATION_HUN_NUM")
    private String stationHunNum;

    @Column(name = "REPAIR_DESC")
    private String repairDesc;

    @Column(name = "IS_FINISH")
    private String isFinish;

    @Column(name = "MAJOR")
    private String major;

    @Column(name = "DUCT_CODE")
    private String ductCode;

    @Column(name = "DUCT_NUM")
    private String ductNum;

    @Column(name = "AFFECTE_EQUIPMENT")
    private String affecteEquipment;

    @Column(name = "DETAIL_LOC")
    private String detailLoc;

    @Column(name = "FAULT_QUANTITY")
    private String faultQuantity;

    @Column(name = "FAULT_UNIT")
    private String faultUnit;

    @Column(name = "DUCT_CODE2")
    private String ductCode2;

    @Column(name = "STATION_FLAG_END")
    private String stationFlagEnd;

    @Column(name = "STATION_KI_NUM_END")
    private String stationKiNumEnd;

    @Column(name = "STATION_HUN_NUM_END")
    private String stationHunNumEnd;

    @Column(name = "MATERIAL_CONSUMPTION")
    private String materialConsumption;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "SAFE_COMPANY")
    private String safeCompany;

    @Column(name = "DEAL_NAME")
    private String dealName;

    @Column(name = "DEAL_TIME")
    private String dealTime;

    @Column(name = "BUILD_NUMBER")
    private String buildNumber;

    @Column(name = "CORRECT_STEP")
    private String correctStep;

    @Column(name = "DISE_FROM")
    private String diseFrom;

    @Column(name = "DEVICE_LOC")
    private String deviceLoc;

    @Column(name = "DEAL_ID")
    private String dealId;

    @Column(name = "DEAL_POST")
    private String dealPost;

    @Column(name = "REC_CREATE_POST")
    private String recCreatePost;

    @Column(name = "REC_REVISE_POST")
    private String recRevisePost;

    @Column(name = "AUDIT_OPINIONS")
    private String auditOpinions;

    @Column(name = "AUDIT_RESUME")
    private String auditResume;

    @Column(name = "DOC_ID")
    private String docId;

    @Column(name = "WORK_EXAMPLE_ID")
    private String workExampleId;

    @Column(name = "DISE_INPUT_TYPE")
    private String diseInputType;

    @Column(name = "OPERATION_TYPE")
    private String operationType;

    @Column(name = "MEDIA_DOC_LIST")
    private String mediaDocList;

    @Column(name = "TASK_ID")
    private String taskId;

    @Column(name = "CHECK_ID_LIST")
    private String checkIdList;

    @Column(name = "LINE_NAME")
    private String lineName;

    @Column(name = "TYPE_ID")
    private String typeId;

    @Column(name = "PIER_NUMBER")
    private String pierNumber;

    @Column(name = "CHECK_NAME_LIST")
    private String checkNameList;

    @Column(name = "TRAIN_NUM")
    private String trainNum;

    @Column(name = "CARRIAGE_NUM")
    private String carriageNum;

    @Column(name = "MILEAGE_NUM")
    private String mileageNum;

    @Column(name = "FIRST_DOC_ID")
    private String firstDocId;

    @Column(name = "FIRST_DOC_NAME")
    private String firstDocName;

    @Column(name = "SECOND_DOC_ID")
    private String secondDocId;

    @Column(name = "SECOND_DOC_NAME")
    private String secondDocName;

    @Column(name = "DOOR_NUM")
    private String doorNum;

    @Column(name = "CARRIAGE_PART")
    private String carriagePart;

    @Column(name = "CAR_ORDER")
    private String carOrder;

    @Column(name = "LEFT_RIGHT_ENDS")
    private String leftRightEnds;

    @Column(name = "BIT_END")
    private String bitEnd;

    @Column(name = "OPERATOR_ID")
    private String operatorId;

    @Column(name = "FAULT_ID")
    private String faultId;

    @Column(name = "OPERATOR_NAME")
    private String operatorName;

    @Column(name = "DEAL_CODE")
    private String dealCode;

    @Column(name = "DEAL_TYPE")
    private String dealType;

    @Column(name = "CON_RECORD")
    private String conRecord;

    @Column(name = "RE_INNER_CODE")
    private String reInnerCode;

    @Column(name = "IS_SHAXING")
    private String isShaxing;

    @Column(name = "IMG_CODE")
    private String imgCode;

    @Column(name = "INIT_FAULT_TYPE")
    private String initFaultType;

    @Column(name = "INIT_FAULT_COUNT")
    private String initFaultCount;

    @Column(name = "IS_CHANGE")
    private String isChange;

    @Column(name = "INIT_FAULT_UNITS")
    private String initFaultUnits;

    @Column(name = "EAM_FAULT_ID")
    private String eamFaultId;

    @Column(name = "EAM_FAULT_NAME")
    private String eamFaultName;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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
     * @return START_STATION
     */
    public String getStartStation() {
        return startStation;
    }

    /**
     * @param startStation
     */
    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    /**
     * @return START_STATIONNAME
     */
    public String getStartStationname() {
        return startStationname;
    }

    /**
     * @param startStationname
     */
    public void setStartStationname(String startStationname) {
        this.startStationname = startStationname;
    }

    /**
     * @return END_STATION
     */
    public String getEndStation() {
        return endStation;
    }

    /**
     * @param endStation
     */
    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    /**
     * @return END_STATIONNAME
     */
    public String getEndStationname() {
        return endStationname;
    }

    /**
     * @param endStationname
     */
    public void setEndStationname(String endStationname) {
        this.endStationname = endStationname;
    }

    /**
     * @return DIRECTION
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
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
     * @return FAULT_NO
     */
    public String getFaultNo() {
        return faultNo;
    }

    /**
     * @param faultNo
     */
    public void setFaultNo(String faultNo) {
        this.faultNo = faultNo;
    }

    /**
     * @return BIG_TYPE_ID
     */
    public String getBigTypeId() {
        return bigTypeId;
    }

    /**
     * @param bigTypeId
     */
    public void setBigTypeId(String bigTypeId) {
        this.bigTypeId = bigTypeId;
    }

    /**
     * @return BIG_TYPE_NAME
     */
    public String getBigTypeName() {
        return bigTypeName;
    }

    /**
     * @param bigTypeName
     */
    public void setBigTypeName(String bigTypeName) {
        this.bigTypeName = bigTypeName;
    }

    /**
     * @return SMALL_TYPE_ID
     */
    public String getSmallTypeId() {
        return smallTypeId;
    }

    /**
     * @param smallTypeId
     */
    public void setSmallTypeId(String smallTypeId) {
        this.smallTypeId = smallTypeId;
    }

    /**
     * @return SMALL_TYPE_NAME
     */
    public String getSmallTypeName() {
        return smallTypeName;
    }

    /**
     * @param smallTypeName
     */
    public void setSmallTypeName(String smallTypeName) {
        this.smallTypeName = smallTypeName;
    }

    /**
     * @return FAULT_TYPE
     */
    public String getFaultType() {
        return faultType;
    }

    /**
     * @param faultType
     */
    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    /**
     * @return EXCEPTION_TYPE_NAME
     */
    public String getExceptionTypeName() {
        return exceptionTypeName;
    }

    /**
     * @param exceptionTypeName
     */
    public void setExceptionTypeName(String exceptionTypeName) {
        this.exceptionTypeName = exceptionTypeName;
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
     * @return REC_CREATE_ID
     */
    public String getRecCreateId() {
        return recCreateId;
    }

    /**
     * @param recCreateId
     */
    public void setRecCreateId(String recCreateId) {
        this.recCreateId = recCreateId;
    }

    /**
     * @return REC_CREATE_NAME
     */
    public String getRecCreateName() {
        return recCreateName;
    }

    /**
     * @param recCreateName
     */
    public void setRecCreateName(String recCreateName) {
        this.recCreateName = recCreateName;
    }

    /**
     * @return REC_CREATE_TIME
     */
    public String getRecCreateTime() {
        return recCreateTime;
    }

    /**
     * @param recCreateTime
     */
    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    /**
     * @return REC_REVISE_ID
     */
    public String getRecReviseId() {
        return recReviseId;
    }

    /**
     * @param recReviseId
     */
    public void setRecReviseId(String recReviseId) {
        this.recReviseId = recReviseId;
    }

    /**
     * @return REC_REVISE_NAME
     */
    public String getRecReviseName() {
        return recReviseName;
    }

    /**
     * @param recReviseName
     */
    public void setRecReviseName(String recReviseName) {
        this.recReviseName = recReviseName;
    }

    /**
     * @return REC_REVISE_TIME
     */
    public String getRecReviseTime() {
        return recReviseTime;
    }

    /**
     * @param recReviseTime
     */
    public void setRecReviseTime(String recReviseTime) {
        this.recReviseTime = recReviseTime;
    }

    /**
     * @return STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return LEFT_RIGHT
     */
    public String getLeftRight() {
        return leftRight;
    }

    /**
     * @param leftRight
     */
    public void setLeftRight(String leftRight) {
        this.leftRight = leftRight;
    }

    /**
     * @return TRACK_NUM
     */
    public String getTrackNum() {
        return trackNum;
    }

    /**
     * @param trackNum
     */
    public void setTrackNum(String trackNum) {
        this.trackNum = trackNum;
    }

    /**
     * @return STATION_FLAG
     */
    public String getStationFlag() {
        return stationFlag;
    }

    /**
     * @param stationFlag
     */
    public void setStationFlag(String stationFlag) {
        this.stationFlag = stationFlag;
    }

    /**
     * @return STATION_KI_NUM
     */
    public String getStationKiNum() {
        return stationKiNum;
    }

    /**
     * @param stationKiNum
     */
    public void setStationKiNum(String stationKiNum) {
        this.stationKiNum = stationKiNum;
    }

    /**
     * @return STATION_HUN_NUM
     */
    public String getStationHunNum() {
        return stationHunNum;
    }

    /**
     * @param stationHunNum
     */
    public void setStationHunNum(String stationHunNum) {
        this.stationHunNum = stationHunNum;
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
     * @return DUCT_CODE
     */
    public String getDuctCode() {
        return ductCode;
    }

    /**
     * @param ductCode
     */
    public void setDuctCode(String ductCode) {
        this.ductCode = ductCode;
    }

    /**
     * @return DUCT_NUM
     */
    public String getDuctNum() {
        return ductNum;
    }

    /**
     * @param ductNum
     */
    public void setDuctNum(String ductNum) {
        this.ductNum = ductNum;
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
     * @return DETAIL_LOC
     */
    public String getDetailLoc() {
        return detailLoc;
    }

    /**
     * @param detailLoc
     */
    public void setDetailLoc(String detailLoc) {
        this.detailLoc = detailLoc;
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
     * @return DUCT_CODE2
     */
    public String getDuctCode2() {
        return ductCode2;
    }

    /**
     * @param ductCode2
     */
    public void setDuctCode2(String ductCode2) {
        this.ductCode2 = ductCode2;
    }

    /**
     * @return STATION_FLAG_END
     */
    public String getStationFlagEnd() {
        return stationFlagEnd;
    }

    /**
     * @param stationFlagEnd
     */
    public void setStationFlagEnd(String stationFlagEnd) {
        this.stationFlagEnd = stationFlagEnd;
    }

    /**
     * @return STATION_KI_NUM_END
     */
    public String getStationKiNumEnd() {
        return stationKiNumEnd;
    }

    /**
     * @param stationKiNumEnd
     */
    public void setStationKiNumEnd(String stationKiNumEnd) {
        this.stationKiNumEnd = stationKiNumEnd;
    }

    /**
     * @return STATION_HUN_NUM_END
     */
    public String getStationHunNumEnd() {
        return stationHunNumEnd;
    }

    /**
     * @param stationHunNumEnd
     */
    public void setStationHunNumEnd(String stationHunNumEnd) {
        this.stationHunNumEnd = stationHunNumEnd;
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
     * @return REMARKS
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return SAFE_COMPANY
     */
    public String getSafeCompany() {
        return safeCompany;
    }

    /**
     * @param safeCompany
     */
    public void setSafeCompany(String safeCompany) {
        this.safeCompany = safeCompany;
    }

    /**
     * @return DEAL_NAME
     */
    public String getDealName() {
        return dealName;
    }

    /**
     * @param dealName
     */
    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    /**
     * @return DEAL_TIME
     */
    public String getDealTime() {
        return dealTime;
    }

    /**
     * @param dealTime
     */
    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    /**
     * @return BUILD_NUMBER
     */
    public String getBuildNumber() {
        return buildNumber;
    }

    /**
     * @param buildNumber
     */
    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    /**
     * @return CORRECT_STEP
     */
    public String getCorrectStep() {
        return correctStep;
    }

    /**
     * @param correctStep
     */
    public void setCorrectStep(String correctStep) {
        this.correctStep = correctStep;
    }

    /**
     * @return DISE_FROM
     */
    public String getDiseFrom() {
        return diseFrom;
    }

    /**
     * @param diseFrom
     */
    public void setDiseFrom(String diseFrom) {
        this.diseFrom = diseFrom;
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
     * @return DEAL_ID
     */
    public String getDealId() {
        return dealId;
    }

    /**
     * @param dealId
     */
    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    /**
     * @return DEAL_POST
     */
    public String getDealPost() {
        return dealPost;
    }

    /**
     * @param dealPost
     */
    public void setDealPost(String dealPost) {
        this.dealPost = dealPost;
    }

    /**
     * @return REC_CREATE_POST
     */
    public String getRecCreatePost() {
        return recCreatePost;
    }

    /**
     * @param recCreatePost
     */
    public void setRecCreatePost(String recCreatePost) {
        this.recCreatePost = recCreatePost;
    }

    /**
     * @return REC_REVISE_POST
     */
    public String getRecRevisePost() {
        return recRevisePost;
    }

    /**
     * @param recRevisePost
     */
    public void setRecRevisePost(String recRevisePost) {
        this.recRevisePost = recRevisePost;
    }

    /**
     * @return AUDIT_OPINIONS
     */
    public String getAuditOpinions() {
        return auditOpinions;
    }

    /**
     * @param auditOpinions
     */
    public void setAuditOpinions(String auditOpinions) {
        this.auditOpinions = auditOpinions;
    }

    /**
     * @return AUDIT_RESUME
     */
    public String getAuditResume() {
        return auditResume;
    }

    /**
     * @param auditResume
     */
    public void setAuditResume(String auditResume) {
        this.auditResume = auditResume;
    }

    /**
     * @return DOC_ID
     */
    public String getDocId() {
        return docId;
    }

    /**
     * @param docId
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
     * @return WORK_EXAMPLE_ID
     */
    public String getWorkExampleId() {
        return workExampleId;
    }

    /**
     * @param workExampleId
     */
    public void setWorkExampleId(String workExampleId) {
        this.workExampleId = workExampleId;
    }

    /**
     * @return DISE_INPUT_TYPE
     */
    public String getDiseInputType() {
        return diseInputType;
    }

    /**
     * @param diseInputType
     */
    public void setDiseInputType(String diseInputType) {
        this.diseInputType = diseInputType;
    }

    /**
     * @return OPERATION_TYPE
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * @param operationType
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     * @return MEDIA_DOC_LIST
     */
    public String getMediaDocList() {
        return mediaDocList;
    }

    /**
     * @param mediaDocList
     */
    public void setMediaDocList(String mediaDocList) {
        this.mediaDocList = mediaDocList;
    }

    /**
     * @return TASK_ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return CHECK_ID_LIST
     */
    public String getCheckIdList() {
        return checkIdList;
    }

    /**
     * @param checkIdList
     */
    public void setCheckIdList(String checkIdList) {
        this.checkIdList = checkIdList;
    }

    /**
     * @return LINE_NAME
     */
    public String getLineName() {
        return lineName;
    }

    /**
     * @param lineName
     */
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    /**
     * @return TYPE_ID
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * @return PIER_NUMBER
     */
    public String getPierNumber() {
        return pierNumber;
    }

    /**
     * @param pierNumber
     */
    public void setPierNumber(String pierNumber) {
        this.pierNumber = pierNumber;
    }

    /**
     * @return CHECK_NAME_LIST
     */
    public String getCheckNameList() {
        return checkNameList;
    }

    /**
     * @param checkNameList
     */
    public void setCheckNameList(String checkNameList) {
        this.checkNameList = checkNameList;
    }

    /**
     * @return TRAIN_NUM
     */
    public String getTrainNum() {
        return trainNum;
    }

    /**
     * @param trainNum
     */
    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    /**
     * @return CARRIAGE_NUM
     */
    public String getCarriageNum() {
        return carriageNum;
    }

    /**
     * @param carriageNum
     */
    public void setCarriageNum(String carriageNum) {
        this.carriageNum = carriageNum;
    }

    /**
     * @return MILEAGE_NUM
     */
    public String getMileageNum() {
        return mileageNum;
    }

    /**
     * @param mileageNum
     */
    public void setMileageNum(String mileageNum) {
        this.mileageNum = mileageNum;
    }

    /**
     * @return FIRST_DOC_ID
     */
    public String getFirstDocId() {
        return firstDocId;
    }

    /**
     * @param firstDocId
     */
    public void setFirstDocId(String firstDocId) {
        this.firstDocId = firstDocId;
    }

    /**
     * @return FIRST_DOC_NAME
     */
    public String getFirstDocName() {
        return firstDocName;
    }

    /**
     * @param firstDocName
     */
    public void setFirstDocName(String firstDocName) {
        this.firstDocName = firstDocName;
    }

    /**
     * @return SECOND_DOC_ID
     */
    public String getSecondDocId() {
        return secondDocId;
    }

    /**
     * @param secondDocId
     */
    public void setSecondDocId(String secondDocId) {
        this.secondDocId = secondDocId;
    }

    /**
     * @return SECOND_DOC_NAME
     */
    public String getSecondDocName() {
        return secondDocName;
    }

    /**
     * @param secondDocName
     */
    public void setSecondDocName(String secondDocName) {
        this.secondDocName = secondDocName;
    }

    /**
     * @return DOOR_NUM
     */
    public String getDoorNum() {
        return doorNum;
    }

    /**
     * @param doorNum
     */
    public void setDoorNum(String doorNum) {
        this.doorNum = doorNum;
    }

    /**
     * @return CARRIAGE_PART
     */
    public String getCarriagePart() {
        return carriagePart;
    }

    /**
     * @param carriagePart
     */
    public void setCarriagePart(String carriagePart) {
        this.carriagePart = carriagePart;
    }

    /**
     * @return CAR_ORDER
     */
    public String getCarOrder() {
        return carOrder;
    }

    /**
     * @param carOrder
     */
    public void setCarOrder(String carOrder) {
        this.carOrder = carOrder;
    }

    /**
     * @return LEFT_RIGHT_ENDS
     */
    public String getLeftRightEnds() {
        return leftRightEnds;
    }

    /**
     * @param leftRightEnds
     */
    public void setLeftRightEnds(String leftRightEnds) {
        this.leftRightEnds = leftRightEnds;
    }

    /**
     * @return BIT_END
     */
    public String getBitEnd() {
        return bitEnd;
    }

    /**
     * @param bitEnd
     */
    public void setBitEnd(String bitEnd) {
        this.bitEnd = bitEnd;
    }

    /**
     * @return OPERATOR_ID
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * @return FAULT_ID
     */
    public String getFaultId() {
        return faultId;
    }

    /**
     * @param faultId
     */
    public void setFaultId(String faultId) {
        this.faultId = faultId;
    }

    /**
     * @return OPERATOR_NAME
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * @param operatorName
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * @return DEAL_CODE
     */
    public String getDealCode() {
        return dealCode;
    }

    /**
     * @param dealCode
     */
    public void setDealCode(String dealCode) {
        this.dealCode = dealCode;
    }

    /**
     * @return DEAL_TYPE
     */
    public String getDealType() {
        return dealType;
    }

    /**
     * @param dealType
     */
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    /**
     * @return CON_RECORD
     */
    public String getConRecord() {
        return conRecord;
    }

    /**
     * @param conRecord
     */
    public void setConRecord(String conRecord) {
        this.conRecord = conRecord;
    }

    /**
     * @return RE_INNER_CODE
     */
    public String getReInnerCode() {
        return reInnerCode;
    }

    /**
     * @param reInnerCode
     */
    public void setReInnerCode(String reInnerCode) {
        this.reInnerCode = reInnerCode;
    }

    /**
     * @return IS_SHAXING
     */
    public String getIsShaxing() {
        return isShaxing;
    }

    /**
     * @param isShaxing
     */
    public void setIsShaxing(String isShaxing) {
        this.isShaxing = isShaxing;
    }

    /**
     * @return IMG_CODE
     */
    public String getImgCode() {
        return imgCode;
    }

    /**
     * @param imgCode
     */
    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    /**
     * @return INIT_FAULT_TYPE
     */
    public String getInitFaultType() {
        return initFaultType;
    }

    /**
     * @param initFaultType
     */
    public void setInitFaultType(String initFaultType) {
        this.initFaultType = initFaultType;
    }

    /**
     * @return INIT_FAULT_COUNT
     */
    public String getInitFaultCount() {
        return initFaultCount;
    }

    /**
     * @param initFaultCount
     */
    public void setInitFaultCount(String initFaultCount) {
        this.initFaultCount = initFaultCount;
    }

    /**
     * @return IS_CHANGE
     */
    public String getIsChange() {
        return isChange;
    }

    /**
     * @param isChange
     */
    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    /**
     * @return INIT_FAULT_UNITS
     */
    public String getInitFaultUnits() {
        return initFaultUnits;
    }

    /**
     * @param initFaultUnits
     */
    public void setInitFaultUnits(String initFaultUnits) {
        this.initFaultUnits = initFaultUnits;
    }

    /**
     * @return EAM_FAULT_ID
     */
    public String getEamFaultId() {
        return eamFaultId;
    }

    /**
     * @param eamFaultId
     */
    public void setEamFaultId(String eamFaultId) {
        this.eamFaultId = eamFaultId;
    }

    /**
     * @return EAM_FAULT_NAME
     */
    public String getEamFaultName() {
        return eamFaultName;
    }

    /**
     * @param eamFaultName
     */
    public void setEamFaultName(String eamFaultName) {
        this.eamFaultName = eamFaultName;
    }
}