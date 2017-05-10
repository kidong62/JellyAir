package project.airport.dto;

//좌석DTO
public class CountryDTO {

	/**
	 * -싱글톤으로 구성하였습니다- 사용법 : 따로 객체생성이 필요 없이, CountryDTO dto =
	 * CountryDTO.getInstance(); 로 호출하여 쓰시면됩니다. toString() 사용은, 오버라이드해서 사용하시면
	 * 됩니다.
	 */

	private static CountryDTO dto = new CountryDTO();

	private CountryDTO() {

	}

	private int cNum;
	// 리스트 명
	private String bDestination;
	// 도착지
	private String bStartSite;
	// 출발지
	private String bStartTime;
	// 탑승 시작 시간
	private String bDepartTime;
	// 탑승 도착 시간
	private int cSitCount;
	// 좌석 개수 (default 값 120)

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
