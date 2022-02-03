package com.learnwithmohit.service.impl;

import com.learnwithmohit.dto.SolutionDto;
import com.learnwithmohit.exception.ResourceNotFoundException;
import com.learnwithmohit.entity.Solution;
import com.learnwithmohit.entity.User;
import com.learnwithmohit.repository.SolutionRepository;
import com.learnwithmohit.repository.UserRepository;
import com.learnwithmohit.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.*;

@Service
public class SolutionServiceImpl implements SolutionService {

	@Autowired
	SolutionRepository solutionRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public SolutionDto saveSolution(Long userId, SolutionDto solutionDto) {
		Solution solution = null;
		if(solutionDto.getId() != null){
			solution = findById(solutionDto.getId());
		} else {
			solution = new Solution();
			User user = userRepository.getById(userId);
			solution.setUser(user);
		}
		solution.setCode(solutionDto.getCode());
		solution = solutionRepository.save(solution);
		return SolutionDto.dto(solution);
	}

	@Override
	public SolutionDto getSolutionById(Long userId, Long solutionId) {
		Solution solution = findById(solutionId);
		return SolutionDto.dto(solution);
	}

	private Solution findById(long id) {
		return solutionRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Solution", "Id", id));
	}

	@Override
	public SolutionDto updateSolution(Long userId, SolutionDto solutionDto, Long solutionId) {
		Solution existingSolution = findById(solutionId);
		existingSolution.setCode(solutionDto.getCode());
		solutionRepository.save(existingSolution);
		return SolutionDto.dto(existingSolution);
	}

	@Override
	public void deleteSolution(Long userId, Long solutionId) {
		findById(solutionId);
		solutionRepository.deleteById(solutionId);
	}

	@Override
	public String solve(Long userId, SolutionDto solutionDto) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");

		//Binding script and Define scope of script
		Bindings bind = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		bind.put("name", "Mohit");

		// Load and execute a script from the solution
		String result = null;
		try {
//			engine.eval(new FileReader("src/main/resources/myjs.js"));
			engine.eval(solutionDto.getCode());
			result = (String) engine.get("result");
		}
//		catch (FileNotFoundException ex) {
//			ex.printStackTrace();
//		}
		catch (ScriptException ex) {
			// This is the generic Exception subclass for the Scripting API
			ex.printStackTrace();
		}

		return result;
	}

}
