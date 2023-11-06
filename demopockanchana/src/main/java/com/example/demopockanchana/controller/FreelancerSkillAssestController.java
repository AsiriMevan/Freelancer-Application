package com.example.demopockanchana.controller;

import com.example.demopockanchana.domain.EvaluationMetadata;
import com.example.demopockanchana.domain.Freelancer;
import com.example.demopockanchana.domain.FreelancerApplicantStatus;
import com.example.demopockanchana.domain.FreelancerDetails;
import com.example.demopockanchana.dto.DetailsDto;
import com.example.demopockanchana.exception.ControlException;
import com.example.demopockanchana.model.ProcessDefinitionResponse;
import com.example.demopockanchana.repository.EvaluationMetadataRepository;
import com.example.demopockanchana.repository.FreelancerRepository;
import com.example.demopockanchana.repository.FreelancerApplicantStatusRepository;
import com.example.demopockanchana.repository.FreelancerDetailsRepository;
import com.example.demopockanchana.service.CompleteProcess;
import com.example.demopockanchana.service.ProcessPay;
import com.example.demopockanchana.service.WorkflowEngineGetTasksByInstanceIdService;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/freelance")
public class FreelancerSkillAssestController {

    public static final String TRACE_ID = "TRC1234";
    @Autowired
    private CompleteProcess employeeCompleteTaskService;

    @Autowired
    private FreelancerRepository freeLancerRepository;

    @Autowired
    private FreelancerApplicantStatusRepository freelancerApplicantStatusRepository;

    @Autowired
    private WorkflowEngineGetTasksByInstanceIdService workflowEngineGetTasksByInstanceIdService;

    @Autowired
    private FreelancerDetailsRepository freelancerDetailsRepository;

    @Autowired
    private EvaluationMetadataRepository evaluationMetadataRepository;

    @Autowired
    private ProcessPay service;


    private static final Logger LOGGER = LoggerFactory.getLogger(FreelancerSkillAssestController.class);

    @GetMapping("/completeQuestion")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @ApiImplicitParams({
    })
    public DetailsDto generateQuestions(@RequestParam("question-number") Integer questionNumber, @RequestParam(required = false, name="answer") String answer, @RequestParam("task-id") String taskId, @RequestParam("processId") Integer processId, @RequestParam("type") String type, @RequestParam("user-name") String userName) throws ControlException {

        LOGGER.info("getDetailsRequest : questionNumber={}", questionNumber);

        Long returnQuestionNumber = Long.valueOf(questionNumber + 1);
        List<Freelancer> freelancerList = null;
        DetailsDto detailsDto = new DetailsDto();
        Freelancer freelancer = new Freelancer();
        String traceId = TRACE_ID;

        if(questionNumber == 9){

            EvaluationMetadata currentQuestionData = evaluationMetadataRepository.findById(questionNumber);

            freelancer.setAnswer(answer);
            freelancer.setQuestion(currentQuestionData.getQuestion());
            freelancer.setQuestionNumber(questionNumber);
            freelancer.setProcessId(processId);
            freelancer.setUserName(userName);
            freeLancerRepository.save(freelancer);
            freelancerList = freeLancerRepository.findByProcessId(processId);
            detailsDto.setFreelancerList(freelancerList);

            employeeCompleteTaskService.completeTaskById(taskId, traceId);

        }else{

            EvaluationMetadata evaluationMetadata = evaluationMetadataRepository.findByTypeAndId(type, returnQuestionNumber);
            EvaluationMetadata currentQuestionData = evaluationMetadataRepository.findById(questionNumber);

            detailsDto.setNextQuestion(evaluationMetadata.getQuestion());

            if(questionNumber != 0){
                freelancer.setAnswer(answer);
                freelancer.setQuestion(currentQuestionData.getQuestion());
                freelancer.setQuestionNumber(questionNumber);
                freelancer.setProcessId(processId);
                freelancer.setUserName(userName);
                freeLancerRepository.save(freelancer);
            }else{
                employeeCompleteTaskService.completeTaskById(taskId, traceId);

                FreelancerApplicantStatus freelancerApplicantStatus = new FreelancerApplicantStatus();
                freelancerApplicantStatus.setProcessId(processId);
                freelancerApplicantStatus.setUserName(userName);
                freelancerApplicantStatusRepository.save(freelancerApplicantStatus);
            }

            if(questionNumber != 0 && (questionNumber%3)== 0){
                employeeCompleteTaskService.completeTaskById(taskId, traceId);
                freelancerList = freeLancerRepository.findByProcessId(processId);
                detailsDto.setFreelancerList(freelancerList);
            }
        }

        LOGGER.info("createInOneCrmResponse : questionNumber={}", questionNumber);
        return detailsDto;
    }



    @PostMapping("registerApplicant/key/{process-definition-key}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @ApiImplicitParams({})
    public ProcessDefinitionResponse registerApplicants(@PathVariable("process-definition-key") String processDefinitionKey, @RequestParam("user-name") String userName, @RequestParam(name="user-email") String userEmail, @RequestParam("preferred-language") String preferredLanguage) throws ControlException {

        ProcessDefinitionResponse definitionResponse =  service.startProcessByDefinitionKey(processDefinitionKey);
        FreelancerDetails freelancerDetails = new FreelancerDetails();
        freelancerDetails.setUserName(userName);
        freelancerDetails.setUserEmail(userEmail);
        freelancerDetails.setLanguage(preferredLanguage);
        freelancerDetailsRepository.save(freelancerDetails);

        return definitionResponse;
    }

    @GetMapping("getResults/registerUser/{registeredUser}/processId/{processId}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @ApiImplicitParams({})
    public List<Freelancer> getRegisterdApplicant(@PathVariable("registeredUser") String registeredUser, @PathVariable("processId") Integer processId) throws ControlException {

        return freeLancerRepository.getResultsByProcessIdAndRegisteredUser(processId, registeredUser);

    }

    @PostMapping("updateApplicantStatus/taskId/{taskId}/processId/{processId}")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @ApiImplicitParams({})
    public FreelancerApplicantStatus updateApplicantStatus(@PathVariable("taskId") String taskId,
                                                           @PathVariable("processId") Integer processId,
                                                           @RequestParam("registerUser") String registerUser,
                                                           @RequestParam("applicantStatus") String applicantStatus) throws ControlException {

        // Saving Applicant status based on Assessor input.
        FreelancerApplicantStatus freelancerApplicantStatus = freelancerApplicantStatusRepository.findByProcessIdAndUserName(processId, registerUser);
        freelancerApplicantStatus.setStatus(applicantStatus);
        freelancerApplicantStatusRepository.save(freelancerApplicantStatus);

        // complete the task by given taskId.
        employeeCompleteTaskService.completeTaskById(taskId, TRACE_ID);

        return freelancerApplicantStatus;
    }

}
