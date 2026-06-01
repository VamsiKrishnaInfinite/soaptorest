package org.infinite.soaptorest.soaptorest.service;

import org.infinite.soaptorest.soaptorest.dto.customer.*;
import org.infinite.soaptorest.soaptorest.dto.common.ResponseHeader;
import org.infinite.soaptorest.soaptorest.dto.common.StatusCode;
import org.infinite.soaptorest.soaptorest.util.ResponseHeaderUtil;
import org.infinite.soaptorest.soaptorest.exception.ResourceNotFoundException;
import org.infinite.soaptorest.soaptorest.exception.ValidationException;
import org.infinite.soaptorest.soaptorest.model.Customer;
import org.infinite.soaptorest.soaptorest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Customer Service - handles all customer-related operations
 * Maps to SOAP CustomerPortType
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Create a new customer - maps to SOAP CreateCustomer
     */
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        // Validate email is unique
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ValidationException("Email already exists", "email");
        }

        CustomerDTO customer = CustomerDTO.builder()
                .customerId(UUID.randomUUID())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .status(CustomerStatus.PENDING_VERIFICATION)
                .tier(CustomerTier.BRONZE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Customer mongoCustomer = Customer.builder()
                .customerId(customer.getCustomerId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .tier(customer.getTier())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();

        customerRepository.save(mongoCustomer);

        return CreateCustomerResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .customer(customer)
                .build();
    }

    /**
     * Get customer by ID - maps to SOAP GetCustomer
     */
    public GetCustomerResponse getCustomer(GetCustomerRequest request) {
        Customer mongoCustomer = customerRepository.findByCustomerId(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", request.getCustomerId().toString()));

        CustomerDTO customer = CustomerDTO.builder()
                .customerId(mongoCustomer.getCustomerId())
                .email(mongoCustomer.getEmail())
                .firstName(mongoCustomer.getFirstName())
                .lastName(mongoCustomer.getLastName())
                .phone(mongoCustomer.getPhone())
                .status(mongoCustomer.getStatus())
                .tier(mongoCustomer.getTier())
                .createdAt(mongoCustomer.getCreatedAt())
                .updatedAt(mongoCustomer.getUpdatedAt())
                .build();

        return GetCustomerResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .customer(customer)
                .build();
    }

    /**
     * Update customer - maps to SOAP UpdateCustomer
     */
    public UpdateCustomerResponse updateCustomer(UUID customerId, UpdateCustomerRequest request) {
        Customer mongoCustomer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", customerId.toString()));

        if (request.getEmail() != null) {
            mongoCustomer.setEmail(request.getEmail());
        }
        if (request.getFirstName() != null) {
            mongoCustomer.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            mongoCustomer.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            mongoCustomer.setPhone(request.getPhone());
        }
        if (request.getStatus() != null) {
            mongoCustomer.setStatus(request.getStatus());
        }

        mongoCustomer.setUpdatedAt(LocalDateTime.now());
        customerRepository.save(mongoCustomer);

        CustomerDTO customer = CustomerDTO.builder()
                .customerId(mongoCustomer.getCustomerId())
                .email(mongoCustomer.getEmail())
                .firstName(mongoCustomer.getFirstName())
                .lastName(mongoCustomer.getLastName())
                .phone(mongoCustomer.getPhone())
                .status(mongoCustomer.getStatus())
                .tier(mongoCustomer.getTier())
                .createdAt(mongoCustomer.getCreatedAt())
                .updatedAt(mongoCustomer.getUpdatedAt())
                .build();

        return UpdateCustomerResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .customer(customer)
                .build();
    }

    /**
     * Search customers with pagination - maps to SOAP SearchCustomers
     */
    public SearchCustomersResponse searchCustomers(SearchCustomersRequest request) {
        List<Customer> mongoCustomers = customerRepository.findAll();

        List<Customer> results = mongoCustomers.stream()
                .filter(c -> request.getEmail() == null || c.getEmail().contains(request.getEmail()))
                .filter(c -> request.getStatus() == null || c.getStatus() == request.getStatus())
                .filter(c -> request.getTier() == null || c.getTier() == request.getTier())
                .toList();

        int totalRecords = results.size();
        int pageSize = request.getPageSize();
        int pageNumber = request.getPageNumber();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalRecords);

        List<Customer> pageResults = results.subList(startIndex, endIndex);

        List<CustomerDTO> customers = pageResults.stream()
                .map(c -> CustomerDTO.builder()
                        .customerId(c.getCustomerId())
                        .email(c.getEmail())
                        .firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .phone(c.getPhone())
                        .status(c.getStatus())
                        .tier(c.getTier())
                        .createdAt(c.getCreatedAt())
                        .updatedAt(c.getUpdatedAt())
                        .build())
                .toList();

        return SearchCustomersResponse.builder()
                .header(ResponseHeaderUtil.createSuccessHeader())
                .customers(new org.infinite.soaptorest.soaptorest.dto.common.ListWrapper<>(customers))
                .pageMeta(org.infinite.soaptorest.soaptorest.dto.common.PageMeta.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .totalRecords(totalRecords)
                        .totalPages(totalPages)
                        .hasNext(pageNumber < totalPages)
                        .hasPrevious(pageNumber > 1)
                        .build())
                .build();
    }
}

