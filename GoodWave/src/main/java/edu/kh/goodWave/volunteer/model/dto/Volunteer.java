package edu.kh.goodWave.volunteer.model.dto;

import java.time.LocalDate;

import edu.kh.goodWave.member.model.dto.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Volunteer {
	
	private int memberNo;
	private String yeontanName;
	private String yeontanTel;
	private String yeontanDate;

	
	private int volunteerNo;
	private int actNo;
	private String actDate;
	private String volunteerDelFl;
	private String volunteerTitle;
	private String volunteerContent;
	private String registryDate;
	private String memberName;
	private String memberTel;
	private String field;
	private int orderNo;
	
	private String actName;
	
}
