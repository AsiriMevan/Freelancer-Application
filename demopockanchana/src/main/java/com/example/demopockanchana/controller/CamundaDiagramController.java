package com.example.demopockanchana.controller;

import com.example.demopockanchana.service.CreateDiagramService;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/api")
@Configuration
public class CamundaDiagramController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CamundaDiagramController.class);

	@Autowired
	CreateDiagramService createDiagramService;

	@Value("${camunda.deployment.url}")
	private String deployUrl;

	@Autowired
	private RestTemplate restTemplate;



	@PostMapping("/create")
	public String createNewProcessInstance() throws IOException {

		String diagramName = "FreelancerHiringProcess";

		BpmnModelInstance myProcess = createDiagramService.createDiagram(diagramName);
		System.out.println(Bpmn.convertToString(myProcess));

		Bpmn.validateModel(myProcess);

		Bpmn.writeModelToFile(new File("D:\\" + diagramName + ".bpmn"), myProcess);

		return "Successfully created:" + diagramName;

	}

	@PutMapping("/update")
	public String updateDiagram(@RequestParam("fileName") String fileName) {

		BpmnModelInstance modelInstance = Bpmn.readModelFromFile(new File("D:\\" + fileName + ".bpmn"));

		UserTask userTask = (UserTask) modelInstance.getModelElementById("id123");
		SequenceFlow outgoingSequenceFlow = userTask.getOutgoing().iterator().next();

		FlowNode serviceTask = outgoingSequenceFlow.getTarget();

		userTask.getOutgoing().remove(outgoingSequenceFlow);

		userTask.builder().userTask().name("").connectTo(serviceTask.getId());

		Bpmn.validateModel(modelInstance);

		Bpmn.writeModelToFile(new File("D:\\" + fileName + ".bpmn"), modelInstance);

		return "successfully updated";
	}

	@PostMapping("/deploy")
	public String deployDiagram(@RequestParam("data") MultipartFile[] data,
			@RequestParam("deployment-name") String deployName ) throws Exception {

		System.out.print("WORKING!!!");

		String requestUrl = deployUrl;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.setBasicAuth("ZGVtbzpkZW1v");


		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		body.add("data", new ByteArrayResource(data[0].getBytes()) {
			@Override
			public String getFilename() {
				return deployName+".bpmn";
			}
		});

		body.add("deployment-name", deployName);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		try {
			restTemplate.postForEntity(requestUrl, requestEntity, String.class);
		} catch (Exception e) {
			LOGGER.error("Failed to upload", e);
			throw new Exception("Failed to upload the diagram");
		}
		return "Successfully deploy";
	}
}
