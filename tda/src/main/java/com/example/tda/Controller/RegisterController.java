package com.example.tda.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        emailService.sendEmail(agent.getEmail(), "TIP","Your username is " + agent.getUsername()+ "\nYour password is " + agent.getPassword());
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @PostMapping("/agent/login")
    public ResponseEntity<Object> login(@RequestBody Agent agent) {
        try {
            Optional<Agent> opt = Optional.ofNullable(agentRepository.findByUsername(agent.getUsername()));
            if (opt.isPresent() && opt.get().getPassword().equals(agent.getPassword()) ) {
                return new ResponseEntity<>(opt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/agent/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Integer id) {
        try {
            Optional<Agent> opt = agentRepository.findById(id);
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/agent/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> putAgent(
            @RequestBody HashMap<String, String> data,
            @PathVariable("id") Integer id) {
        try {
            Optional<Agent> opt = agentRepository.findById(id);
            Agent user = opt.get();
            if (data.get("username") != null) {
                user.setUsername(data.get("username"));
            }
            if (data.get("password") != null) {
                user.setPassword(data.get("password"));
            }
            if (data.get("type") != null) {
                user.setType(data.get("type"));
            }
            if (data.get("prefix") != null) {
                user.setPrefix(data.get("prefix"));
            }
            if (data.get("name") != null) {
                user.setFirstName(data.get("name"));
            }
            if (data.get("lastname") != null) {
                user.setLastName(data.get("lastname"));
            }
            if (data.get("sub_district") != null) {
                user.setSubDistrict(data.get("sub_district"));
            }
            if (data.get("district") != null) {
                user.setDistrict(data.get("district"));
            }
            if (data.get("province") != null) {
                user.setProvince(data.get("province"));
            }
            if (data.get("phone") != null) {
                user.setPhone(data.get("phone"));
            }
            if (data.get("email") != null) {
                user.setEmail(data.get("email"));
            }
            if (data.get("licenseId") != null) {
                user.setLicenseId(data.get("licenseId"));
            }
            if (data.get("licenseExpire") != null) {
          
                user.setLicenseExpire(data.get("licenseExpire"));
            }
            agentRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
