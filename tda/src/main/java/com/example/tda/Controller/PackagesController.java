package com.example.tda.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tda.entity.Packages;
import com.example.tda.repository.PackagesRepository;
import com.example.tda.service.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PackagesController {

    @Autowired
    private PackagesRepository packageRepository;  
    @GetMapping("/packages")
    public ResponseEntity<List<Packages>> getPackages() {
        List<Packages> packages = packageRepository.findAll();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

    
    @PostMapping("/packages")
    public ResponseEntity<Packages> packages(@RequestBody Packages pack) {
        // Save the order to the database

        Packages packages =  packageRepository.save(pack);
        // Return a response with a success status code
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

         // Get order by id
         @GetMapping("/packages/{id}")
         public ResponseEntity<Object> getUserById(@PathVariable("id") Integer id) {
             try {
                 Optional<Packages> order = packageRepository.findById(id);
                 return new ResponseEntity<>(order.get(), HttpStatus.OK);
             } catch (Exception e) {
                 return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
             }
         }



    // private final PdfGeneratorService pdfGeneratorService;

    // public PackagesController(PdfGeneratorService pdfGeneratorService) {
    //     this.pdfGeneratorService = pdfGeneratorService;
    // }
    // @GetMapping("/report/{id}")
    // public void generatePDF(HttpServletResponse response, @PathVariable Long id) throws IOException {
    //     response.setContentType("application/pdf");
    //     // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
    //     // String currentDateTime = dateFormat.format(new Date());
    //     String headerKey = "Content-Disposition";
    //     String headerValue = "attachment; filename=Insurance-Policy(A4).pdf";
    //     response.setHeader(headerKey, headerValue);
    //     Order order = orderRepository.findById(id).get();
    //     this.pdfGeneratorService.export(response, order);
    // }
}
