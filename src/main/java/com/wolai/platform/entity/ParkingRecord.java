package com.wolai.platform.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 停车记录
 * @author Ethan
 *
 */
@Entity
@Table(name="wo_parking_record")
public class ParkingRecord extends idEntity {

	/**
	 * uuid
	 */
	private static final long serialVersionUID = 6012441198587216743L;

	/**
	 * 车牌号
	 */
	private String carNo;
	
	@Column(name="car_no_id")
	private String carNoId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_no_id", insertable = false, updatable = false)
	private LicensePlate car;
	
	/**
	 * 停车场
	 */
	@Column(name="parking_lot_id")
	private String parkingLotId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_lot_id", insertable = false, updatable = false)
	private ParkingLot parkingLot;
	
	/**
	 * 入口编号
	 */
	private String entranceNo;
	
	/**
	 * 出口编号
	 */
	private String exportNo;
	
	/**
	 * 车头照
	 */
	private String carPicPath;
	
	/**
	 * 进入时刻
	 */
	private Date driveInTime;
	
	/**
	 * 出场时刻
	 */
	private Date driveOutTime;
	
	/**
	 * 应付金额
	 */
	private BigDecimal money;
	
	/**
	 * 实付金额
	 */
	private BigDecimal paidMoney;

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarNoId() {
		return carNoId;
	}

	public void setCarNoId(String carNoId) {
		this.carNoId = carNoId;
	}

	public LicensePlate getCar() {
		return car;
	}

	public void setCar(LicensePlate car) {
		this.car = car;
	}

	public String getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(String parkingLotId) {
		this.parkingLotId = parkingLotId;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	public String getEntranceNo() {
		return entranceNo;
	}

	public void setEntranceNo(String entranceNo) {
		this.entranceNo = entranceNo;
	}

	public String getExportNo() {
		return exportNo;
	}

	public void setExportNo(String exportNo) {
		this.exportNo = exportNo;
	}

	public String getCarPicPath() {
		return carPicPath;
	}

	public void setCarPicPath(String carPicPath) {
		this.carPicPath = carPicPath;
	}

	public Date getDriveInTime() {
		return driveInTime;
	}

	public void setDriveInTime(Date driveInTime) {
		this.driveInTime = driveInTime;
	}

	public Date getDriveOutTime() {
		return driveOutTime;
	}

	public void setDriveOutTime(Date driveOutTime) {
		this.driveOutTime = driveOutTime;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}
}