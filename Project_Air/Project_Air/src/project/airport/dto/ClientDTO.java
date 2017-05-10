package project.airport.dto;

public class ClientDTO {

	/**
	 * 마일리지 빼고 귀찮으니 모두 private String으로 줬습니다 ㅎ 
	 * (계산 아니면 private String타입이 편할 것같아서요~) 
	 * 언제든 수정 가능합니다~~ 
	 * -싱글톤으로 구성하였습니다- 
	 * 사용법 : 따로 객체생성이 필요 없이,
	 * ClientDTO dto = ClientDTO.getInstance(); 로 호출하여 쓰시면됩니다.
	 * 
	 *  * toString() 사용은, 오버라이드해서 사용하시면 됩니다.
	 */

	private static ClientDTO dto = new ClientDTO();

	private ClientDTO() {
		
	}

	public static ClientDTO getInstance() {
		return dto;
	}

	private String pNum;
	// 여권번호
	private String pName;
	// 이름
	private String pAddr;
	// 주소
	private String pAge;
	// 나이
	private String pCountry;
	// 국적
	private String pYesOrNot;
	// 결격사유
	private String pSex;
	// 성별
	private String pJob;
	// 직업
	private int pMileage;
	// 마일리지
	private String pass;

	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpAddr() {
		return pAddr;
	}

	public void setpAddr(String pAddr) {
		this.pAddr = pAddr;
	}

	public String getpAge() {
		return pAge;
	}

	public void setpAge(String pAge) {
		this.pAge = pAge;
	}

	public String getpCountry() {
		return pCountry;
	}

	public void setpCountry(String pCountry) {
		this.pCountry = pCountry;
	}

	public String getpYesOrNot() {
		return pYesOrNot;
	}

	public void setpYesOrNot(String pYesOrNot) {
		this.pYesOrNot = pYesOrNot;
	}

	public String getpSex() {
		return pSex;
	}

	public void setpSex(String pSex) {
		this.pSex = pSex;
	}

	public String getpJob() {
		return pJob;
	}

	public void setpJob(String pJob) {
		this.pJob = pJob;
	}

	public int getpMileage() {
		return pMileage;
	}

	public void setpMileage(int pMileage) {
		this.pMileage = pMileage;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

}
