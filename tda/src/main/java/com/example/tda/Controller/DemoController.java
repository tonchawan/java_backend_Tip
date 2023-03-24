package com.example.tda.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.tda.entity.Order;
import com.example.tda.entity.Packages;
import com.example.tda.repository.OrderRepository;
import com.example.tda.repository.PackagesRepository;

@RestController
public class DemoController {


    @Autowired
    private OrderRepository orderRepository;



    @GetMapping("/test/{id}")
    public List<Order> demo(@PathVariable Integer id) throws IOException {
        List<Order> list = orderRepository.findReportByAgentId(id);

        return list;

    }

    
}
