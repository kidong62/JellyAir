package project.airport.dto;

import java.util.Date;

// ž�� DTO
public class BoardingDTO {
	
	/**
	 * -�̱������� �����Ͽ����ϴ�- 
	 * ���� : ���� ��ü������ �ʿ� ����,
	 * BoardingDTO dto = BoardingDTO.getInstance(); �� ȣ���Ͽ� ���ø�˴ϴ�.
	 *  toString() �����, �������̵��ؼ� ����Ͻø� �˴ϴ�.
	 */
	
	private static BoardingDTO dto = new BoardingDTO();

	private BoardingDTO() {
	}
	
	public static BoardingDTO getInstance(){
		return dto;
	}
	
	private String pNum; 
	// ���ǹ�ȣ
	private String pSit; 
	// �¼���ȣ
	private int pMileage;
	// ���ϸ���
	private String bStartTime; 
	// ž�� ���� �ð�
	private String bDepartTime; 
	// ž�� ���� �ð�
	private String bTime; 
	// �ҿ�ð�
	private String bDestination; 
	// ������
	private String bStartSite; 
	// �����

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
