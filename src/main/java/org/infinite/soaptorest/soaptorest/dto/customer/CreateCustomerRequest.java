package org.infinite.soaptorest.soaptorest.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request to create a new customer - maps to SOAP CreateCustomerRequest
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "Customer email address", example = "john@example.com")
    private String email;

    @NotBlank(message = "First name is required")
    @Schema(description = "Customer first name", example = "John")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Schema(description = "Customer last name", example = "Doe")
    private String lastName;

    @Schema(description = "Customer phone number (E.164 format)", example = "+1234567890")
    private String phone;
}

