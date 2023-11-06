package com.example.demopockanchana.repository;

import com.example.demopockanchana.domain.Freelancer;
import com.example.demopockanchana.domain.FreelancerApplicantStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerApplicantStatusRepository extends JpaRepository<FreelancerApplicantStatus,Long> {

    FreelancerApplicantStatus findByProcessIdAndUserName(Integer processId, String userName);
}
