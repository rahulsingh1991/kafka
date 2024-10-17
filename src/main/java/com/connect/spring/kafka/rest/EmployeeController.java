package com.connect.spring.kafka.rest;

import com.connect.spring.kafka.producer.EmployeeEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeEventProducer employeeEventProducer;

    @Autowired
    public EmployeeController(EmployeeEventProducer employeeEventProducer) {
        this.employeeEventProducer = employeeEventProducer;
    }

    @PostMapping("/send")
    public String sendEmployeeEvent(@RequestBody String employeeEvent) {
        employeeEventProducer.sendEmployeeEvent(employeeEvent);
        return "Employee event sent successfully!";
    }
}
