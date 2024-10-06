package org.camunda.platform.runtime.example;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("externalTaskB")
public class ExternalTaskB implements ExternalTaskHandler {

  @Override
  public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
      try {
          // Get the job id from the task variables
          String id = (String) externalTask.getActivityId();

          System.out.println("activity Id: " + id);
  
          // Check if the job name is null or empty
          if (id == null || id.isEmpty()) {
              // Throw a data error
              throw new RuntimeException("Data error: Job name is missing");
          }
  
          // Simulate some work
          System.out.println("Processing task " + externalTask.getId());
  
          // Create a map to store variables to pass back to Camunda
          Map<String, Object> variables = new HashMap<>();
  
          // Add some variables to the map (optional)
          variables.put("someVariable", "someValue");
  
          // Complete the task
          externalTaskService.complete(externalTask, variables);
          System.out.println("External Task B Completed");
      } catch (Exception e) {
        int retries = 3;
        long retryTimeout = 60000; // 1 minute in milliseconds
          externalTaskService.handleFailure(externalTask,"ERROR_B",e.getMessage(),retries, retryTimeout); 
      }
  }
}