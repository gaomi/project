package com.company.project.module.data.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
public class Newpoints {
    @Id
    @Column(name = "POINTSGUID")
    private String pointsGuild;

    @Column(name = "LINECODE")
    private String lineCode;

    @Column(name = "UPDOWN")
    private int updown;

    @Column(name = "POINTSNAME")
    private String pointsName;

    @Column(name = "POINTSMILEAGE")
    private double pointsMileAge;

    @Column(name = "POINTSRING")
    private String pointsRing;

    @Column(name = "POINTSPOSITION")
    private String pointsPosition;


    @Column(name = "BEGINDATE")
    private Date beginDate;
    
    @Column(name = "POINTSDEMO")
    private String pointsDemo;

    /**
     * @return POINTSGUID
     */
	public String getPointsGuild() {
		return pointsGuild;
	}

	/**
	 * @param pointsGuild
	 */
	public void setPointsGuild(String pointsGuild) {
		this.pointsGuild = pointsGuild;
	}

	/**
	 * @return LINECODE
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
	 * @return UPDOWN
	 */
	public int getUpdown() {
		return updown;
	}

	/**
	 * @param updown
	 */
	public void setUpdown(int updown) {
		this.updown = updown;
	}

	/**
	 * @return POINTSNAME
	 */
	public String getPointsName() {
		return pointsName;
	}

	/**
	 * @param pointsName
	 */
	public void setPointsName(String pointsName) {
		this.pointsName = pointsName;
	}

	/**
	 * @return POINTSMILEAGE
	 */
	public double getPointsMileAge() {
		return pointsMileAge;
	}

	/**
	 * @param pointsMileAge
	 */
	public void setPointsMileAge(double pointsMileAge) {
		this.pointsMileAge = pointsMileAge;
	}

	/**
	 * @return POINTSRING
	 */
	public String getPointsRing() {
		return pointsRing;
	}

	/**
	 * @param pointsRing
	 */
	public void setPointsRing(String pointsRing) {
		this.pointsRing = pointsRing;
	}

	/**
	 * @return POINTSPOSITION
	 */
	public String getPointsPosition() {
		return pointsPosition;
	}

	/**
	 * @param pointsPosition
	 */
	public void setPointsPosition(String pointsPosition) {
		this.pointsPosition = pointsPosition;
	}

	/**
	 * @return BEGINDATE
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * @return POINTSDEMO
	 */
	public String getPointsDemo() {
		return pointsDemo;
	}
	
	/**
	 * @param pointsDemo
	 */
	public void setPointsDemo(String pointsDemo) {
		this.pointsDemo = pointsDemo;
	}
    
    
}