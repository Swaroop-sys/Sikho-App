package com.example.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.Repository.AuthRepository;
import com.example.ecommerce.Repository.InstructorEarningRepo;
import com.example.ecommerce.model.AuthModel;
import com.example.ecommerce.model.InstructorEarning;

@Service
public class InstructorEarningService {

	@Autowired
	private InstructorEarningRepo instructorEarningRepo;
	
	@Autowired
    private AuthRepository authRepository;

    public InstructorEarning addEarning(Long instructorId, Double amount) {
        AuthModel instructor = authRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        InstructorEarning earning = new InstructorEarning();
        earning.setInstructor(instructor);
        earning.setEarning(amount);

        return instructorEarningRepo.save(earning);
    }
    

    public Double getTotalEarnings(Long instructorId) {
        AuthModel instructor = authRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + instructorId));
        Double total = instructorEarningRepo.findTotalEarningsByInstructor(instructor);
        return total != null ? total : 0.0;
    }
}
