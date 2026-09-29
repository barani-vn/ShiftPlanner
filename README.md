# ShiftPlanner Demo

This version keeps the project within Question 43 and adds separate demo logins and dashboards so the full swap workflow can be demonstrated.

## Demo logins
Manager: `manager` / `manager123`
Employee Arun: `arun` / `arun123`
Employee Priya: `priya` / `priya123`
Employee Kumar: `kumar` / `kumar123`

## Demo workflow
1. Login as Manager and create shifts/assign roster.
2. Logout and login as Arun.
3. Request a swap with Priya.
4. Logout and login as Priya; Accept or Decline.
5. Logout and login as Manager; Approve or Reject.
6. Open Weekly Roster and show the approved swap reflected.

## Run
Create database `shiftplanner` in MySQL Workbench. Set your MySQL password in `application.properties`. Then run `mvn clean install` and `mvn spring-boot:run`. Open `http://localhost:8080/`.

## Rules enforced
- No overlapping shifts for one employee on the same day.
- Manager cannot approve until colleague accepts.
- Approved swap is applied to roster.
- Swap requester must own the selected shift.
- Swap cannot create an overlapping shift for colleague.

Passwords are plain text only for this academic demo; production systems should use hashing and proper authentication.
