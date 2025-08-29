package uk.codersden.hr.payroll;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePaymentDao extends JpaRepository<EmployeePayment, String> {

}
