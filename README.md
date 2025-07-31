#  Employee Leave Management System (ELMS)

## Overview

The **Employee Leave Management System** is a Java-based web app designed to streamline the process of managing employee leave requests within an organization.  
It's built using **Java Servlets**, **JSP**, **JDBC**, and an **Oracle Database**, and it follows the **MVC (Model-View-Controller)** pattern throughout the project.

The system is designed with three roles in mind:

- **Employee** – Can apply for leave and keep track of its status.  
- **Manager** – Reviews leave applications and either approves or rejects them.  
- **Admin** – Manages employee records, with full control to add, update, view, or remove users.

---------

##  Core Functionality

### Employee Features

- Secure login/logout  
- Apply for leave (with proper validation)  
- View a history of leave applications  
- Automatically calculates leave duration, skipping weekends  

###  Manager Features

- See all pending leave requests  
- Approve or reject requests  
- Review previously handled applications  

### Admin Features

- Admin login/logout  
- Add new employee details (name, email, password, dept ID, role)  
- Update or delete employee information  
- View a list of all employees (grouped by department and ID)

---------

##  Technology Stack

- **Programming Language**: Java  
- **Backend**: Java Servlets, JSP  
- **Frontend**: HTML & JSP (unstyled)  
- **Database**: Oracle SQL  
- **Server**: Apache Tomcat  
- **Architecture**: MVC

----------

## How MVC Is Applied

This project sticks closely to the **MVC architecture**, splitting the application into:

- **Model**: JavaBeans like `EmployeeBean`, `LeaveRequestBean`, and DAO classes such as `EmployeeDAO`, `LeaveRequestDAO`. These handle business data and database operations.  
- **View**: JSP files are used for user interfaces. They display forms, outputs, and results—no business logic is mixed in.  
- **Controller**: Servlets (like `LeaveRequestServlet`, `AdminLoginServlet`, etc.) receive input from the user, interact with the model layer, and forward results to the relevant JSP views.

----------

##  Database Structure (Simplified)

### `Employee`
- Fields: `emp_id`, `name`, `email`, `password`, `dept_id`, `role`

### `LeaveRequest`
- Fields: `leave_id`, `emp_id`, `from_date`, `to_date`, `num_days`, `reason`, `status`, `manager_id`, `applied_on`, `decision_date`

### `AdminUser`
- Fields: `username`, `password`

**Other constraints include:**
- Foreign keys on `emp_id` and `manager_id`  
- Status limited to: `Pending`, `Accepted`, or `Rejected`  
- Prevention of duplicate leave applications for overlapping dates

---

##  Key Business Logic

- Leaves can't be applied for past dates  
- `To Date` must not be earlier than `From Date`  
- Weekends (Saturday & Sunday) are automatically excluded when calculating leave days  
- Only one pending leave request is allowed per employee at any time  
- Validations are enforced server-side to prevent invalid data entries

---

## Final Notes

This is a fully working backend-driven web application that demonstrates:

- How to handle requests using Java Servlets  
- Session handling for login-based flow  
- Connecting and interacting with an Oracle Database using JDBC  
- Practical leave management logic, suitable for real-world enterprise applications
