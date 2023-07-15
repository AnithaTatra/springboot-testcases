package com.springboot.testing.junit.service;
import com.springboot.testing.junit.entity.Employee;
import com.springboot.testing.junit.exception.ResourceNotFoundException;
import com.springboot.testing.junit.repository.EmployeeRepository;
import com.springboot.testing.junit.serviceimpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .id(1L)
                .firstName("Soubhagya")
                .lastName("Rama")
                .email("soubhagya@gmail.com")
                .build();
    }


    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void saveEmployeeObject(){

        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());

        given(employeeRepository.save(employee)).willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);


        Employee savedEmployee = employeeService.saveEmployee(employee);

        System.out.println(savedEmployee);

        assertThat(savedEmployee).isNotNull();
    }


    @DisplayName("JUnit test for saveEmployee method which throws exception")
    @Test
    public void saveEmployeeEmailObject(){

        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        System.out.println(employeeRepository);
        System.out.println(employeeService);


        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.saveEmployee(employee);
        });


        verify(employeeRepository, never()).save(any(Employee.class));
    }


    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void allEmployeesObject(){

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Alice")
                .lastName("Stark")
                .email("alice@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));


        List<Employee> employeeList = employeeService.getAllEmployees();


        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }


    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
    @Test
    public void emptyEmployeesListObject(){


        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Shruthi")
                .lastName("Narayanan")
                .email("shruthi@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());


        List<Employee> employeeList = employeeService.getAllEmployees();


        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);
    }


    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void getEmployeeById(){

        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));


        Employee savedEmployee = employeeService.getEmployeeById(employee.getId()).get();


        assertThat(savedEmployee).isNotNull();

    }


    @DisplayName("JUnit test for updateEmployee method")
    @Test
    public void modifiedEmployeeListObject(){

        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmail("ram@gmail.com");
        employee.setFirstName("Ram");

        Employee updatedEmployee = employeeService.updateEmployee(employee);


        assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ram");
    }


    @DisplayName("JUnit test for deleteEmployee method")
    @Test
    public void getDeletedEmployeeObject(){

        long employeeId = 1L;

        willDoNothing().given(employeeRepository).deleteById(employeeId);


        employeeService.deleteEmployee(employeeId);


        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}
