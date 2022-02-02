package com.learnwithmohit.dto;

import com.learnwithmohit.model.Solution;
import lombok.Data;

@Data
public class SolutionDto {

	private Long id;
	private String code;

	public static SolutionDto dto(final Solution solution){
		SolutionDto dto = new SolutionDto();
		dto.setId(solution.getId());
		dto.setCode(solution.getCode());
		return dto;
	}
}
