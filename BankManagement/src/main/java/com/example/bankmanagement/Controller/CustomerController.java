package com.example.bankmanagement.Controller;

import com.example.bankmanagement.ApiResponse.ApiResponse;
import com.example.bankmanagement.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class CustomerController {

    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return new ApiResponse("Customer added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index,@RequestBody Customer customer) {
        customers.set(index, customer);
        return new ApiResponse("Customer updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        customers.remove(index);
        return new ApiResponse("Customer deleted successfully");
    }

    @PostMapping("/deposit/{index}/{amount}")
    public ApiResponse deposit(@PathVariable int index,@PathVariable double amount) {
        Customer customer = customers.get(index);
        customer.setBalance(customer.getBalance() + amount);
        return new ApiResponse("Customer deposit successfully");
    }

    @PostMapping("withdraw/{index}/{amount}")
    public ApiResponse withdrawal(@PathVariable int index, @PathVariable double amount) {
        Customer customer = customers.get(index);
        if (customer.getBalance() >= amount) {
            customer.setBalance(customer.getBalance() - amount);
            return new ApiResponse("Customer withdrawal successfully");
        } else return new ApiResponse("Not enough balance");
    }

}
