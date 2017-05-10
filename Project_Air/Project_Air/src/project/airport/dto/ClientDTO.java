package project.airport.dto;

public class ClientDTO {

	/**
	 * ���ϸ��� ���� �������� ��� private String���� ����ϴ� �� 
	 * (��� �ƴϸ� private StringŸ���� ���� �Ͱ��Ƽ���~) 
	 * ������ ���� �����մϴ�~~ 
	 * -�̱������� �����Ͽ����ϴ�- 
	 * ���� : ���� ��ü������ �ʿ� ����,
	 * ClientDTO dto = ClientDTO.getInstance(); �� ȣ���Ͽ� ���ø�˴ϴ�.
	 * 
	 *  * toString() �����, �������̵��ؼ� ����Ͻø� �˴ϴ�.
	 */

	private static ClientDTO dto = new ClientDTO();

	private ClientDTO() {
		
	}

	public static ClientDTO getInstance() {
		return dto;
	}

	private String pNum;
	// ���ǹ�ȣ
	private String pName;
	// �̸�
	private String pAddr;
	// �ּ�
	private String pAge;
	// ����
	private String pCountry;
	// ����
	private String pYesOrNot;
	// ��ݻ���
	private String pSex;
	// ����
	private String pJob;
	// ����
	private int pMileage;
	// ���ϸ���
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
