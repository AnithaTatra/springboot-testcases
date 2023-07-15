package com.springboot.testing.junit;

import com.springboot.testing.junit.entity.Employee;
import com.springboot.testing.junit.exception.ResourceNotFoundException;
import com.springboot.testing.junit.repository.EmployeeRepository;
import com.springboot.testing.junit.serviceimpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest
class JunitApplicationTests {

	@Test
	void contextLoads() {


	}
}
