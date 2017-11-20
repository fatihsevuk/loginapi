package com.iotrack.loginapi.controller;

import com.iotrack.loginapi.domain.Employee;
import com.iotrack.loginapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addEmployee(HttpServletRequest request, @RequestBody Employee employee) {

        long id = employee.getId();
        Employee employeeFind = employeeService.findOne(id);

        try {
            if (employeeFind == null) {
                employeeService.createEmployee(employee);
                return new ResponseEntity("Employee added susccessfully", HttpStatus.OK);

            } else {
                employeeService.createEmployee(employee);
                return new ResponseEntity("Employee updated susccessfully", HttpStatus.OK);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);

        }


    }


    @RequestMapping("/all")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployee();
    }

    @RequestMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.findOne(id);
        return employee;
    }

    @RequestMapping(value = "/add/image", method = RequestMethod.POST)
    public ResponseEntity upload(@RequestParam("id") Long id, HttpServletResponse response,
                                 HttpServletRequest request) {

        try {

            Employee employee = employeeService.findOne(id);

            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;


            Iterator<String> it = multipartHttpServletRequest.getFileNames();

            MultipartFile multipartFile = multipartHttpServletRequest.getFile(it.next());


            String fileName = id + ".png";

            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(

                    new FileOutputStream(new File("src/main/resources/static/image/product/" + fileName)));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity("Upload Successfully", HttpStatus.OK);


        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Upload failed", HttpStatus.BAD_REQUEST);

        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity deleteEmployee(@RequestBody String id) {

        employeeService.delete(employeeService.findOne(Long.parseLong(id)));

        return new ResponseEntity("Personel kaydı silindi", HttpStatus.OK);
    }

}
