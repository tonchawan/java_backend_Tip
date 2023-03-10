package com.example.tda.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tda.entity.Agent;
import com.example.tda.repository.AgentRepository;
import com.example.tda.service.EmailService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RegisterController {
    @Autowired
    private AgentRepository agentRepository;
    
    @Autowired
    private EmailService emailService;
    @PostMapping("/agent/register")
    public ResponseEntity<String> register(@RequestBody Agent agent) {
        agentRepository.save(agent);
        emailService.sendEmail(agent.getEmail(), "TIP", "Your password is " + agent.getPhone());
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @PostMapping("/agent/login")
    public ResponseEntity<Object> login(@RequestBody Agent agent) {
        try {
            Optional<Agent> opt = Optional.ofNullable(agentRepository.findByEmail(agent.getEmail()));
            if (opt.isPresent()) {
                return new ResponseEntity<>(opt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
        }
    }
}
