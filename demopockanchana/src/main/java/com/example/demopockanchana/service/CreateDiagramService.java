package com.example.demopockanchana.service;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;

public interface CreateDiagramService {

    public BpmnModelInstance  createDiagram(String diagramDname);

}
