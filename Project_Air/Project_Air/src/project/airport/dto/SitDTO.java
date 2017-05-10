package project.airport.dto;

import java.util.ArrayList;

public class SitDTO {

	/**
	 * 쓰실분만 쓰시면 됩니다. ~사용법은 다음과 같습니다~ (ex) SitDTO sit = SitDTO.getInstance();
	 * 
	 * String b[] = sit.getbClass();
	 * 
	 */

	private static SitDTO dto = new SitDTO();

//	private static String[] aClass = new String[10];
//	private static String[] bClass = new String[10];
//	private static String[] cClass = new String[10];
//	private static String[] dClass = new String[10];
	private static String[] totalClass = new String[260];
//	private static ArrayList<String[]> everyClass = new ArrayList<String[]>();

	private SitDTO() {

	}

	public static SitDTO getInstance() {
		
//		for (int i = 0; i < aClass.length; i++) {
//			aClass[i] = "A" + (i + 1);
//			bClass[i] = "B" + (i + 1);
//			cClass[i] = "C" + (i + 1);
//			dClass[i] = "D" + (i + 1);
//		}
//		
//		for (int j = 0; j < 4; j++) {
//			for (int i = 1; i <= aClass.length; i++) {
//				totalClass[i-1+(j*10)] = (char) ('A' + j) + "" + i;
//			}
//		}
//		
//		everyClass.add(totalClass);
//		
		return dto;
	}

//	public String[] getaClass() {
//		return aClass;
//	}
//
//	public void setaClass(String[] aClass) {
//		this.aClass = aClass;
//	}
//
//	public String[] getbClass() {
//		return bClass;
//	}
//
//	public void setbClass(String[] bClass) {
//		this.bClass = bClass;
//	}
//
//	public String[] getcClass() {
//		return cClass;
//	}
//
//	public void setcClass(String[] cClass) {
//		this.cClass = cClass;
//	}
//
//	public String[] getdClass() {
//		return dClass;
//	}
//
//	public void setdClass(String[] dClass) {
//		this.dClass = dClass;
//	}

	public String[] getTotalClass() {
		return totalClass;
	}

	public void setTotalClass(String[] totalClass) {
		this.totalClass = totalClass;
	}

//	public ArrayList<String[]> getEveryClass() {
//		return everyClass;
//	}

}
