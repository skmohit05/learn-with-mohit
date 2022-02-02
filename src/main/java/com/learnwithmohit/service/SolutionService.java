package com.learnwithmohit.service;

import com.learnwithmohit.dto.SolutionDto;

public interface SolutionService {
	SolutionDto saveSolution(Long userId, SolutionDto solutionDto);
	SolutionDto getSolutionById(Long userId, Long solutionId);
	SolutionDto updateSolution(Long userId, SolutionDto solutionDto, Long solutionId);
	void deleteSolution(Long userId, Long solutionId);
	String solve(Long userId, SolutionDto solution);
}
