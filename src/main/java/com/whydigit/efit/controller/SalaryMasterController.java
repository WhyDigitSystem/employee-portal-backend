package com.whydigit.efit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whydigit.efit.entity.SalaryMasterVO;
import com.whydigit.efit.service.SalaryMasterService;

@RestController
@CrossOrigin
@RequestMapping("/api/salaryMaster")
public class SalaryMasterController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(SalaryMasterController.class);

	@Autowired
	SalaryMasterService edService;

	@GetMapping("/getAllSalaryMaster")
	public List<SalaryMasterVO> getAllSalaryMaster() {
		return edService.getAllSalaryMaster();
	}

	@GetMapping("/getAllSalaryMasterById")
	public SalaryMasterVO getAllSalaryMasterById(@RequestParam Long id) {
		return edService.getAllSalaryMasterById(id);
	}

	@PostMapping("/createSalaryMaster")
	public SalaryMasterVO createSalaryMaster(@RequestBody SalaryMasterVO salaryMaster) {
		return edService.createSalaryMaster(salaryMaster);
	}

	@PutMapping("/updateSalaryMaster")
	public SalaryMasterVO updateSalaryMaster(@RequestBody SalaryMasterVO salaryMaster, @RequestParam Long id) {
		return edService.updateSalaryMaster(salaryMaster, id);
	}
}