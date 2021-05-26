package net.josephmbuku.springboot.repository;

import net.josephmbuku.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Jpa Repository exposes a lot of Database CRUD operations such as: (Save, findByID, Delete, Etc)
// Pass the Employee Entity and the Type of the Primary Key.
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
