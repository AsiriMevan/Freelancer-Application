package com.example.demopockanchana.repository;

import com.example.demopockanchana.domain.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FreelancerRepository extends JpaRepository<Freelancer,Long> {
    List<Freelancer> findByProcessId(int i);

    @Query(value="SELECT fl.id, fl.question_number, fl.question, fl.answer, fl.process_id, fl.type, fl.user_name FROM public.freelancer fl where fl.process_id = ?1 and fl.user_name = ?2",
            nativeQuery = true)
    List<Freelancer> getResultsByProcessIdAndRegisteredUser(Integer processId, String registerUser);

}
