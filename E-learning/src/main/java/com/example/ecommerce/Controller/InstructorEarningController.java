package com.example.ecommerce.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Service.InstructorEarningService;
import com.example.ecommerce.model.InstructorEarning;

@RestController
@RequestMapping("/api/earning")
public class InstructorEarningController {
@Autowired
private InstructorEarningService instructorEarningService;

@PostMapping("/add-earning")
public InstructorEarning addEarning(@RequestBody Map<String, Object> request) {
    Long instructorId = Long.valueOf(request.get("instructorId").toString());
    Double amount = Double.valueOf(request.get("amount").toString());

    return instructorEarningService.addEarning(instructorId, amount);
}
@GetMapping("/total-earning/{instructorId}")
public Double getTotalEarnings(@PathVariable Long instructorId) {
    return instructorEarningService.getTotalEarnings(instructorId);
}

}
