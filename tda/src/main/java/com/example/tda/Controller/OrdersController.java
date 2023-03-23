package com.example.tda.Controller;

import java.io.IOException;
import java.util.Date;
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
import com.example.tda.entity.*;
import com.example.tda.repository.*;
import com.example.tda.service.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OrdersController {

    @Autowired 
    private EmailService emailService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PackagesRepository packagesRepository;    

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
        Date date1 = new Date();
        order.setUpdatedAt(date1);
        order.setCreatedAt(date1);
        Order ord =  orderRepository.save(order); // Save the order to the database
        Packages packages = packagesRepository.findById(ord.getPackageId()).get();
        emailService.sendMailWithAttachment("tonchawan@hotmail.com", "Your Package", "Thankyou for purchase","y",ord,packages );
        return new ResponseEntity<>(ord, HttpStatus.OK);  // Return a response with a success status code
    }

    // Update order by id
    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> putOrder(
            @RequestBody HashMap<String, String> data,
            @PathVariable("id") Integer id) {
        try {
            Optional<Order> opt = orderRepository.findById(id);
        System.out.println(data);
            Order user = opt.get();
            if (data.get("agentId") != null) {
                user.setAgentId(Integer.parseInt( data.get("agentId")));
            }
            if (data.get("packageId") != null) {
                user.setPackageId(Integer.parseInt( data.get("packageId")));
            }
            if (data.get("prefix") != null) {
                user.setPrefix(data.get("prefix").toString());
            }
            if (data.get("firstName") != null) {
                user.setFirstName(data.get("firstName").toString());
            }
            if (data.get("lastName") != null) {
                user.setLastName(data.get("lastName").toString());
            }
            if (data.get("identity") != null) {
                user.setIdentity(data.get("identity").toString());
            }
            if (data.get("address") != null) {
                user.setAddress(data.get("address").toString());
            }
            if (data.get("subDistrict") != null) {
                user.setSubDistrict(data.get("subDistrict").toString());
            }
            if (data.get("district") != null) {
                user.setDistrict(data.get("district").toString());
            }
            if (data.get("province") != null) {
                user.setProvince(data.get("province").toString());
            }
            if (data.get("province") != null) {
                user.setProvince(data.get("province").toString());
            }
            if (data.get("zipCode") != null) {
                user.setZipCode(data.get("zipCode").toString());
            }
            if (data.get("phone") != null) {
                user.setPhone(data.get("phone").toString());
            }
            if (data.get("email") != null) {
                user.setEmail(data.get("email").toString());
            }
            if (data.get("dob") != null) {
                user.setDob(data.get("dob").toString());
            }
            if (data.get("startDate") != null) {
                user.setStartDate(data.get("startDate").toString());
            }
            if (data.get("endDate") != null) {
                user.setEndDate(data.get("endDate").toString());
            }
            if (data.get("benefiaial") != null) {
                user.setBenefiaial(data.get("benefiaial").toString());
            }
            if (data.get("orderStatus") != null) {
                user.setOrderStatus((int) 1);
            }
            user.setUpdatedAt(new Date());
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
        order.setOrderStatus(0); // Set the order status to 0
        Date date1 = new Date();
        order.setUpdatedAt(date1);
        order.setCreatedAt(date1);
         Order ord =  orderRepository.save(order); // Save the order to the database        
         return new ResponseEntity<>(ord, HttpStatus.OK); // Return a response with a success status code
            
     }

    // Update draft (user the same method as update order )
    @RequestMapping(value = "/draft/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> putDraf(
        @RequestBody HashMap<String, String> data,
        @PathVariable("id") Integer id) {
    try {
        Optional<Order> opt = orderRepository.findById(id);
        Order user = opt.get();
        if (data.get("agentId") != null) {
            user.setAgentId(Integer.parseInt( data.get("agentId")));
        }
        if (data.get("packageId") != null) {
            user.setPackageId(Integer.parseInt( data.get("packageId")));
        }
        if (data.get("prefix") != null) {
            user.setPrefix(data.get("prefix").toString());
        }
        if (data.get("firstName") != null) {
            user.setFirstName(data.get("firstName").toString());
        }
        if (data.get("lastName") != null) {
            user.setLastName(data.get("lastName").toString());
        }
        if (data.get("identity") != null) {
            user.setIdentity(data.get("identity").toString());
        }
        if (data.get("address") != null) {
            user.setAddress(data.get("address").toString());
        }
        if (data.get("subDistrict") != null) {
            user.setSubDistrict(data.get("subDistrict").toString());
        }
        if (data.get("district") != null) {
            user.setDistrict(data.get("district").toString());
        }
        if (data.get("province") != null) {
            user.setProvince(data.get("province").toString());
        }
        if (data.get("province") != null) {
            user.setProvince(data.get("province").toString());
        }
        if (data.get("zipCode") != null) {
            user.setZipCode(data.get("zipCode").toString());
        }
        if (data.get("phone") != null) {
            user.setPhone(data.get("phone").toString());
        }
        if (data.get("email") != null) {
            user.setEmail(data.get("email").toString());
        }
        if (data.get("dob") != null) {
            user.setDob(data.get("dob").toString());
        }
        if (data.get("startDate") != null) {
            user.setStartDate(data.get("startDate").toString());
        }
        if (data.get("endDate") != null) {
            user.setEndDate(data.get("endDate").toString());
        }
        if (data.get("benefiaial") != null) {
            user.setBenefiaial(data.get("benefiaial").toString());
        }
        if (data.get("orderStatus") != null) {
            user.setOrderStatus((int) 0);
        }
        user.setUpdatedAt(new Date());
        orderRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
    }
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
    Packages packages = packagesRepository.findById(order.getPackageId()).get();
    this.pdfGeneratorService.export(response, order, packages);

    }

@GetMapping("/mailPDf/{id}")
public void sendEmailPdf(@PathVariable Integer id)throws IOException {
    Order order = orderRepository.findById(id).get();
    Packages packages = packagesRepository.findById(order.getPackageId()).get();
    emailService.sendMailWithAttachment("tonchawan@hotmail.com", "Your Package", "Thankyou for purchase","y",order,packages );

}

}

