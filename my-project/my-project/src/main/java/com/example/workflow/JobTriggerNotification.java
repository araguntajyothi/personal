package com.example.workflow;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class JobTriggerNotification implements JavaDelegate {
  @Override
  public void execute(DelegateExecution execution) throws Exception {
   System.out.println("job triggered");
   
  }
}