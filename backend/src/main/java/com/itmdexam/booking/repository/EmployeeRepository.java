package com.itmdexam.booking.repository;

import com.itmdexam.booking.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
