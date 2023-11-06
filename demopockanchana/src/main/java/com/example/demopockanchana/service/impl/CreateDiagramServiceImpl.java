package com.example.demopockanchana.service.impl;

import com.example.demopockanchana.service.CreateDiagramService;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateDiagramServiceImpl implements CreateDiagramService {
    public BpmnModelInstance createDiagram(String diagramName){

        List<String> LinkedList=new ArrayList<String>();
        LinkedList.add("Layanperera");
        LinkedList.add("KeshanChathuranda");
        LinkedList.add("NaminSaranga");
        LinkedList.add("ChalikaMihiran");

        BpmnModelInstance modelInstance = Bpmn.createExecutableProcess()

                .id(diagramName)
                .startEvent()
                .userTask().name("User Profile Upload").id("id123") .camundaAssignee(String.valueOf(LinkedList))
                .userTask().name("Algorithm_Quiz").id("Algorithm")
                .userTask().name("MCQ_Quiz").id("MCQ") .camundaFailedJobRetryTimeCycle("")
                .userTask().name("Coding_Quiz").id("Coding")
                .userTask().name("Assessor").id("Assessor")
                .endEvent()
                .done();

        return modelInstance;
    }
}
