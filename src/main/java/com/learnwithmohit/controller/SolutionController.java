package com.learnwithmohit.controller;

import com.learnwithmohit.dto.SolutionDto;
import com.learnwithmohit.model.Solution;
import com.learnwithmohit.service.SolutionService;
import com.learnwithmohit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/solutions")
public class SolutionController {

	@Autowired
	private SolutionService solutionService;

	@PostMapping()
	public ResponseEntity<SolutionDto> saveSolution(@PathVariable("userId") Long userId,
													@RequestBody SolutionDto solution){
		return new ResponseEntity<>(solutionService.saveSolution(userId, solution), HttpStatus.CREATED);
	}

	@GetMapping("/{solutionId}")
	public ResponseEntity<SolutionDto> getSolutionById(@PathVariable("userId") Long userId,
												   @PathVariable("solutionId") Long solutionId){
		return new ResponseEntity<>(solutionService.getSolutionById(userId, solutionId), HttpStatus.OK);
	}

	@PutMapping("/{solutionId}")
	public ResponseEntity<SolutionDto> updateSolution(@PathVariable("userId") Long userId,
												  @PathVariable("solutionId") Long solutionId,
												  @RequestBody SolutionDto solution){
		return new ResponseEntity<>(solutionService.updateSolution(userId, solution, solutionId), HttpStatus.OK);
	}

	@PostMapping("/solve")
	public ResponseEntity<String> solve(@PathVariable("userId") Long userId,
													  @RequestBody SolutionDto solution){
		return new ResponseEntity<>(solutionService.solve(userId, solution), HttpStatus.OK);
	}

	@DeleteMapping("/{solutionId}")
	public ResponseEntity<String> deleteSolution(@PathVariable("userId") Long userId,
											 @PathVariable("solutionId") Long solutionId){
		solutionService.deleteSolution(userId, solutionId);
		return new ResponseEntity<>("Solution deleted successfully!.", HttpStatus.OK);
	}
}
