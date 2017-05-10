package project.airport.dto;

import java.util.Date;

// 탑승 DTO
public class BoardingDTO {
	
	/**
	 * -싱글톤으로 구성하였습니다- 
	 * 사용법 : 따로 객체생성이 필요 없이,
	 * BoardingDTO dto = BoardingDTO.getInstance(); 로 호출하여 쓰시면됩니다.
	 *  toString() 사용은, 오버라이드해서 사용하시면 됩니다.
	 */
	
	private static BoardingDTO dto = new BoardingDTO();

	private BoardingDTO() {
	}
	
	public static BoardingDTO getInstance(){
		return dto;
	}
	
	private String pNum; 
	// 여권번호
	private String pSit; 
	// 좌석번호
	private int pMileage;
	// 마일리지
	private String bStartTime; 
	// 탑승 시작 시간
	private String bDepartTime; 
	// 탑승 도착 시간
	private String bTime; 
	// 소요시간
	private String bDestination; 
	// 도착지
	private String bStartSite; 
	// 출발지

//	private Date bstartT;
//	private Date bdeaprtT;
	
	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	public String getpSit() {
		return pSit;
	}

	public void setpSit(String pSit) {
		this.pSit = pSit;
	}

	public int getpMileage() {
		return pMileage;
	}

	public void setpMileage(int pMileage) {
		this.pMileage = pMileage;
	}

	public String getbStartTime() {
		return bStartTime;
	}

	public void setbStartTime(String bStartTime) {
		this.bStartTime = bStartTime;
	}

	public String getbDepartTime() {
		return bDepartTime;
	}

	public void setbDepartTime(String bDepartTime) {
		this.bDepartTime = bDepartTime;
	}

	public String getbTime() {
		return bTime;
	}

	public void setbTime(String bTime) {
		this.bTime = bTime;
	}

	public String getbDestination() {
		return bDestination;
	}

	public void setbDestination(String bDestination) {
		this.bDestination = bDestination;
	}

	public String getbStartSite() {
		return bStartSite;
	}

	public void setbStartSite(String bStartSite) {
		this.bStartSite = bStartSite;
	}

//	public Date getBstartT() {
//		return bstartT;
//	}
//
//	public void setBstartT(Date bstartT) {
//		this.bstartT = bstartT;
//	}
//
//	public Date getBdeaprtT() {
//		return bdeaprtT;
//	}
//
//	public void setBdeaprtT(Date bdeaprtT) {
//		this.bdeaprtT = bdeaprtT;
//	}
	
	

}
