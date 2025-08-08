package com.example.ecommerce.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ecommerce.model.AuthModel;
import com.example.ecommerce.model.InstructorEarning;

public interface InstructorEarningRepo extends JpaRepository<InstructorEarning, Long>{
	Optional<InstructorEarning> findByInstructor(AuthModel instructor);

    @Query("SELECT SUM(e.earning) FROM InstructorEarning e WHERE e.instructor = :instructor")
    Double findTotalEarningsByInstructor(@Param("instructor") AuthModel instructor);
    
    
}
