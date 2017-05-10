package project.airport.dto;

//�¼�DTO
public class CountryDTO {

	/**
	 * -�̱������� �����Ͽ����ϴ�- ���� : ���� ��ü������ �ʿ� ����, CountryDTO dto =
	 * CountryDTO.getInstance(); �� ȣ���Ͽ� ���ø�˴ϴ�. toString() �����, �������̵��ؼ� ����Ͻø�
	 * �˴ϴ�.
	 */

	private static CountryDTO dto = new CountryDTO();

	private CountryDTO() {

	}

	private int cNum;
	// ����Ʈ ��
	private String bDestination;
	// ������
	private String bStartSite;
	// �����
	private String bStartTime;
	// ž�� ���� �ð�
	private String bDepartTime;
	// ž�� ���� �ð�
	private int cSitCount;
	// �¼� ���� (default �� 120)

	public static CountryDTO getInstance() {
		return dto;
	}

	public int getcNum() {
		return cNum;
	}

	public void setcNum(int cNum) {
		this.cNum = cNum;
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

	public int getcSitCount() {
		return cSitCount;
	}

	public void setcSitCount(int cSitCount) {
		this.cSitCount = cSitCount;
	}

}
