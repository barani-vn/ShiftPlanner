INSERT INTO employee (name,email,role,username,password) SELECT 'Manager','manager@shiftplanner.com','MANAGER','manager','manager123' WHERE NOT EXISTS (SELECT 1 FROM employee WHERE username='manager');
INSERT INTO employee (name,email,role,username,password) SELECT 'Arun','arun@shiftplanner.com','EMPLOYEE','arun','arun123' WHERE NOT EXISTS (SELECT 1 FROM employee WHERE username='arun');
INSERT INTO employee (name,email,role,username,password) SELECT 'Priya','priya@shiftplanner.com','EMPLOYEE','priya','priya123' WHERE NOT EXISTS (SELECT 1 FROM employee WHERE username='priya');
INSERT INTO employee (name,email,role,username,password) SELECT 'Kumar','kumar@shiftplanner.com','EMPLOYEE','kumar','kumar123' WHERE NOT EXISTS (SELECT 1 FROM employee WHERE username='kumar');
