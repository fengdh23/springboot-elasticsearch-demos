package springdata.elasticsearch.conference;

import springdata.elasticsearch.StartApplication;
import springdata.elasticsearch.dao.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = StartApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
class ConferenceRepositoryTest {
    @Resource
    private EmployeeRepository employeeRepository;

    @Test
    public void createEmployee(){
        Employee employee = new Employee();
        employee.setId("001");
        employee.setCountry("cn");
        employee.setAge(25);
        employeeRepository.save(employee);
    }

    @Test
    public void getEmployee(){
        employeeRepository.findById("001");
    }

    @Test
    public void updateEmployee(){
        Employee employee = new Employee();
        employee.setId("001");
        employee.setCountry("us");
        employee.setAge(101);
        employeeRepository.save(employee);
    }

    @Test
    public void deleteEmployee(){
        employeeRepository.deleteById("001");
    }

}