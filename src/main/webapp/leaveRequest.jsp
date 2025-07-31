<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Request Leave</title>
    <script>
        function calculateLeaveDays() {
            const fromDateInput = document.getElementById("fromDate");
            const toDateInput = document.getElementById("toDate");
            const output = document.getElementById("noOfDays");

            const fromDate = new Date(fromDateInput.value);
            const toDate = new Date(toDateInput.value);

            // set min attribute on toDate
            if (!isNaN(fromDate)) {
                toDateInput.min = fromDateInput.value;
            }

            if (isNaN(fromDate) || isNaN(toDate) || fromDate > toDate) {
                output.value = "";
                return;
            }

            let count = 0;
            let date = new Date(fromDate);
            while (date <= toDate) {
                const day = date.getDay();
                if (day !== 0 && day !== 6) {
                    count++;
                }
                date.setDate(date.getDate() + 1);
            }

            output.value = count;
        }

        function validateForm() {
            const fromDate = new Date(document.getElementById("fromDate").value);
            const toDate = new Date(document.getElementById("toDate").value);

            if (fromDate > toDate) {
                alert("To Date cannot be before From Date.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h1>Leave Request Form</h1>
    <form action="LeaveRequest" method="post" onsubmit="return validateForm()">
        <label for="fromDate">From Date:</label>
        <input type="date" id="fromDate" name="fromDate" onchange="calculateLeaveDays()" required><br><br>

        <label for="toDate">To Date:</label>
        <input type="date" id="toDate" name="toDate" onchange="calculateLeaveDays()" required><br><br>

        <label for="noOfDays">Number of Days (excluding weekends):</label>
        <input type="text" id="noOfDays" name="noOfDays" readonly><br><br>

        <label for="reason">Reason for Leave:</label><br>
        <textarea id="reason" name="reason" rows="4" cols="30" required></textarea><br><br>

        <input type="submit" value="Submit Leave Request">
    </form>
</body>
</html>
