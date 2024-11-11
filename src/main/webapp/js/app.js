function fetchStats() {
    fetch('/DashboardServlet')
        .then(response => response.json())
        .then(data => {
            document.getElementById('totalAttendance').innerText = data.total_attendance;
            document.getElementById('totalPayroll').innerText = data.total_payroll;
        })
        .catch(err => console.error('Error fetching dashboard data:', err));
}

function fetchPayroll() {
    fetch('/PayrollServlet')
        .then(response => response.text())
        .then(data => {
            document.getElementById('payrollData').innerHTML = data;
        })
        .catch(err => console.error('Error fetching payroll data:', err));
}
document.addEventListener("DOMContentLoaded", function() {
    // Simulate fetching data from server
    document.getElementById("totalStaff").innerText = "25"; // Example data
    document.getElementById("attendanceRate").innerText = "85%"; // Example data
    document.getElementById("totalPayroll").innerText = "$12,000.00"; // Example data
});
