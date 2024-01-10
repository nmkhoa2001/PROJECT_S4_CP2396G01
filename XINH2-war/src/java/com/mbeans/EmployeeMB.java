package com.mbeans;

import com.cusc.entities.Employees;
import com.cusc.sessions.EmployeesFacadeLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author ngomi
 */
@Named(value = "employeeMB")
@RequestScoped
public class EmployeeMB {

    @EJB
    private EmployeesFacadeLocal employeesFacade;
    private Long empID;
    private String fullname;
    private String username;
    private String password;
    private String address;
    private String phone;
    private String email;
    private String avatar;
    private String status;
    private Employees employees;
    private String oldPass;
    private String newPass;
    private String confirmPass;
    private String messagePass = "";
    private String messageUpdateProfile = "";

    private String messageConfirmOldPass = "";
    private String messageConfirmNewPass = "";

    private String messageAddEmployee = "";
    private String messageDeleteEmployee = "";
    private String fullnameMessage;
    private String usernameMessage;
    private String passwordMessage;
    private String addressMessage;
    private String phoneMessage;
    private String emailMessage;
    private Part file;
    private String imgurl;
    private boolean flag;

    private List<Employees> list;

    public String createEmployee() {
        long checkUsername = employeesFacade.getCountByUsername(employees.getUsername());
        if (checkUsername > 0) {
            usernameMessage = "Username already exists!";
            return "addEmployee";
        } else {
            employees.setStatus(1);
            employeesFacade.create(employees);
        }

        return "employeeList";
    }

    public String saveUpDatePasswordEmployee() {
        int status = 0;
        if (!employees.getOldPass().equals(employees.getPassword())) {
            status++;
            messageConfirmOldPass = "Old password invalid!";
        }
        if (!employees.getNewPass().equals(employees.getConfirmNewPass())) {
            status++;
            messageConfirmNewPass = "Confirm new password invalid!";
        }
        if (status == 0) {
            employees.setPassword(employees.getNewPass());
            employeesFacade.edit(employees);
            messagePass = "Changed password successfully!";
        }

        return "changePass";
    }

    public void confirm(long id) throws IOException {
        Employees employees = employeesFacade.find(id);
        employeesFacade.remove(employees);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/KAYA-war/faces/admin/employeeList.xhtml");
        addMessage("Confirmed", "You have accepted");
    }

    public String reloadPage() {
        return "employeeList";
    }

    public void delete() {
        addMessage("Confirmed", "Record deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public EmployeeMB() {
        list = new ArrayList<>();
        employees = new Employees();
    }

    public String showProfile(String username) {
        employees = employeesFacade.findByUsername(username);
        Long id = employees.getEmployeeID();
        employees = employeesFacade.find(id);
        empID = employees.getEmployeeID();
        return "profile";
    }

    public String saveUpdateEmployee() {
        employeesFacade.edit(employees);
        messageUpdateProfile = "Profile updated successfully!";
        return "profile";
    }

    public List<Employees> showAllEmployee() {
        list = employeesFacade.findAll();
        return list;
    }

    public void showEmployeeDetails(Long id) {
        employees = employeesFacade.find(id);
    }

    public String showEmployeeDetail_ver1(long id) {
        employees = employeesFacade.find(id);
        return "employeeList";
    }

    public String deleteEmployee(Long id) {
        employeesFacade.remove(employeesFacade.find(id));
        messageDeleteEmployee = "Deleted employee successfully!";
        return "employeeList";
    }

    public String showAddForm() {
        employees = null;
        return "addEmployee";
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public EmployeesFacadeLocal getEmployeesFacade() {
        return employeesFacade;
    }

    public void setEmployeesFacade(EmployeesFacadeLocal employeesFacade) {
        this.employeesFacade = employeesFacade;
    }

    public Long getEmployeeID() {
        return empID;
    }

    public void setEmployeeID(Long employeeID) {
        this.empID = employeeID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employees> getList() {
        return list;
    }

    public void setList(List<Employees> list) {
        this.list = list;
    }

    public Long getEmpID() {
        return empID;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getMessagePass() {
        return messagePass;
    }

    public void setMessagePass(String messagePass) {
        this.messagePass = messagePass;
    }

    public String getMessageConfirmOldPass() {
        return messageConfirmOldPass;
    }

    public void setMessageConfirmOldPass(String messageConfirmOldPass) {
        this.messageConfirmOldPass = messageConfirmOldPass;
    }

    public String getMessageConfirmNewPass() {
        return messageConfirmNewPass;
    }

    public void setMessageConfirmNewPass(String messageConfirmNewPass) {
        this.messageConfirmNewPass = messageConfirmNewPass;
    }

    public String getMessageUpdateProfile() {
        return messageUpdateProfile;
    }

    public void setMessageUpdateProfile(String messageUpdateProfile) {
        this.messageUpdateProfile = messageUpdateProfile;
    }

    public String getMessageAddEmployee() {
        return messageAddEmployee;
    }

    public void setMessageAddEmployee(String messageAddEmployee) {
        this.messageAddEmployee = messageAddEmployee;
    }

    public String getMessageDeleteEmployee() {
        return messageDeleteEmployee;
    }

    public void setMessageDeleteEmployee(String messageDeleteEmployee) {
        this.messageDeleteEmployee = messageDeleteEmployee;
    }

    public String getFullnameMessage() {
        return fullnameMessage;
    }

    public void setFullnameMessage(String fullnameMessage) {
        this.fullnameMessage = fullnameMessage;
    }

    public String getUsernameMessage() {
        return usernameMessage;
    }

    public void setUsernameMessage(String usernameMessage) {
        this.usernameMessage = usernameMessage;
    }

    public String getPasswordMessage() {
        return passwordMessage;
    }

    public void setPasswordMessage(String passwordMessage) {
        this.passwordMessage = passwordMessage;
    }

    public String getAddressMessage() {
        return addressMessage;
    }

    public void setAddressMessage(String addressMessage) {
        this.addressMessage = addressMessage;
    }

    public String getPhoneMessage() {
        return phoneMessage;
    }

    public void setPhoneMessage(String phoneMessage) {
        this.phoneMessage = phoneMessage;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
