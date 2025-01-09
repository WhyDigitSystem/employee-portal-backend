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

import com.whydigit.efit.entity.EarningsVO;
import com.whydigit.efit.service.EarningDeductionService;

@CrossOrigin
@RestController
@RequestMapping("/api/edController")
public class EarningsDeductionController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(EarningsDeductionController.class);

	@Autowired
	EarningDeductionService edService;

	@GetMapping("/getAllEarnings")
	public List<EarningsVO> getAllEarnings() {
		return edService.getAllEarnings();
	}

	@GetMapping("/getAllEarningsById")
	public EarningsVO getAllEarningsById(@RequestParam Long id) {
		return edService.getAllEarningsById(id);
	}

	@PostMapping("/createEarnings")
	public EarningsVO createEarnings(@RequestBody EarningsVO earnings) {
		return edService.createEarnings(earnings);
	}

	@PutMapping("/updateEarnings")
	public EarningsVO updateEarnings(@RequestBody EarningsVO earnings, @RequestParam Long id) {
		return edService.updateEarnings(earnings, id);
	}
}
