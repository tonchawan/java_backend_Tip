package com.example.tda.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Any;
import org.hibernate.sql.Update;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.tda.service.PdfGeneratorService;
import com.example.tda.entity.Order;
import com.example.tda.repository.OrderRepository;
import com.example.tda.service.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OrdersController {

    @Autowired 
    private EmailService emailService;

    @Autowired
    private OrderRepository orderRepository;

    // Get all order
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrder() {
        List<Order> ord = orderRepository.findAll();
        return new ResponseEntity<>(ord, HttpStatus.OK);
    }

     // Get order by id
    @GetMapping("/order/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Integer id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
        }
    }

    // Get order by agentId
    @GetMapping("/agent/{id}/order")
    public ResponseEntity<Object> getorderBycitizenID(@PathVariable("id") Integer id) {

        List<Order> opt = orderRepository.findByAgentId(id);
        return new ResponseEntity<>(opt, HttpStatus.OK);

    }

////////////////////////////////Buy Order/////////////////////////////////////////////////////


    // Create Order
    @PostMapping("/buy")
    public ResponseEntity<Order> order(@RequestBody Order order) {
        order.setOrderStatus(1); // Set the order status to 1
        Order ord =  orderRepository.save(order); // Save the order to the database
        return new ResponseEntity<>(ord, HttpStatus.OK);  // Return a response with a success status code
    }

    // Update order by id
    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> putOrder(
            @RequestBody HashMap<String, Object> data,
            @PathVariable("id") Integer id) {
        try {
            Optional<Order> opt = orderRepository.findById(id);
            Order user = opt.get();
            if (data.get("agentId") != null) {
                user.setAgentId((Integer) data.get("agentId"));
            }
            if (data.get("packageId") != null) {
                user.setPackageId((Integer) data.get("packageId"));
            }
            if (data.get("prefix") != null) {
                user.setPrefix((String) data.get("prefix"));
            }
            if (data.get("firstName") != null) {
                user.setFirstName((String) data.get("firstName"));
            }
            if (data.get("lastName") != null) {
                user.setLastName((String) data.get("lastName"));
            }
            if (data.get("identity") != null) {
                user.setIdentity((String) data.get("identity"));
            }
            if (data.get("address") != null) {
                user.setAddress((String) data.get("address"));
            }
            if (data.get("subDistrict") != null) {
                user.setSubDistrict((String) data.get("subDistrict"));
            }
            if (data.get("district") != null) {
                user.setDistrict((String) data.get("district"));
            }
            if (data.get("province") != null) {
                user.setProvince((String) data.get("province"));
            }
            if (data.get("province") != null) {
                user.setProvince((String) data.get("province"));
            }
            if (data.get("zipCode") != null) {
                user.setZipCode((String) data.get("zipCode"));
            }
            if (data.get("phone") != null) {
                user.setPhone((String) data.get("phone"));
            }
            if (data.get("email") != null) {
                user.setEmail((String) data.get("email"));
            }
            if (data.get("dob") != null) {
                user.setDob((String) data.get("dob"));
            }
            if (data.get("startDate") != null) {
                user.setStartDate((String) data.get("startDate"));
            }
            if (data.get("endDate") != null) {
                user.setEndDate((String) data.get("endDate"));
            }
            if (data.get("benefiaial") != null) {
                user.setBenefiaial((String) data.get("benefiaial"));
            }
            if (data.get("orderStatus") != null) {
                user.setOrderStatus((Integer) 1);
            }
            orderRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
///////////////////////////////////////////////Save draft order////////////////////////////////////

     // Create Order
     @PostMapping("/draft")
     public ResponseEntity<Order> draft(@RequestBody Order order) {
        order.setOrderStatus(0); // Set the order status to 1
         Order ord =  orderRepository.save(order); // Save the order to the database        
         return new ResponseEntity<>(ord, HttpStatus.OK); // Return a response with a success status code
            
     }

    // Update draft (user the same method as update order )
    @RequestMapping(value = "/draft/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> putDraf(
        @RequestBody HashMap<String, Object> data,
        @PathVariable("id") Integer id) {
    try {
        Optional<Order> opt = orderRepository.findById(id);
        Order user = opt.get();

        user.setAgentId(getInteger(data, "agentId", user.getAgentId()));
        user.setPackageId(getInteger(data, "packageId", user.getPackageId()));
        user.setPrefix(getString(data, "prefix", user.getPrefix()));
        user.setFirstName(getString(data, "firstName", user.getFirstName()));
        user.setLastName(getString(data, "lastName", user.getLastName()));
        user.setIdentity(getString(data, "identity", user.getIdentity()));
        user.setAddress(getString(data, "address", user.getAddress()));
        user.setSubDistrict(getString(data, "subDistrict", user.getSubDistrict()));
        user.setDistrict(getString(data, "district", user.getDistrict()));
        user.setProvince(getString(data, "province", user.getProvince()));
        user.setZipCode(getString(data, "zipCode", user.getZipCode()));
        user.setPhone(getString(data, "phone", user.getPhone()));
        user.setEmail(getString(data, "email", user.getEmail()));
        user.setDob(getString(data, "dob", user.getDob()));
        user.setStartDate(getString(data, "startDate", user.getStartDate()));
        user.setEndDate(getString(data, "endDate", user.getEndDate()));
        user.setBenefiaial(getString(data, "benefiaial", user.getBenefiaial()));
        if (data.get("orderStatus") != null) {
            user.setOrderStatus((Integer) 0);
        }

        orderRepository.save(user);
        return ResponseEntity.ok(user);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.toString());
    }
}

private Integer getInteger(HashMap<String, Object> data, String key, Integer defaultValue) {
    return data.containsKey(key) ? (Integer) data.get(key) : defaultValue;
}

private String getString(HashMap<String, Object> data, String key, String defaultValue) {
    return data.containsKey(key) ? (String) data.get(key) : defaultValue;
}

private final PdfGeneratorService pdfGeneratorService;

public OrdersController(PdfGeneratorService pdfGeneratorService) {
    this.pdfGeneratorService = pdfGeneratorService;
}

@GetMapping("/loadPdf/{id}")
public void generatePDF(HttpServletResponse response, @PathVariable Integer id) throws IOException {
    response.setContentType("application/pdf");
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=Insurance-Policy(A4).pdf";
    response.setHeader(headerKey, headerValue);
    Order order = orderRepository.findById(id).get();
    this.pdfGeneratorService.export(response, order);

    }

@GetMapping("/mailPDf")
public void sendEmailPdf()throws IOException {
    String status= emailService.sendMailWithAttachment("tonchawan@hotmail.com", "test", "Tester", "2");

}

}
