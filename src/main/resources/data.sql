INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)
 VALUES ('123 Maple St','London','On', 'N1N-1N1','(555)555-5555','Trusted','ABC Supply Co.','abc@supply.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)
 VALUES ('543 Sycamore Ave','Toronto','On', 'N1P-1N1','(999)555-5555','Trusted','Big Bills Depot','bb@depot.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)
 VALUES ('922 Oak St','London','On', 'N1N-1N1','(555)555-5599','Untrusted','Shady Sams','ss@underthetable.com');
INSERT INTO Vendor (Address1,City,Province,PostalCode,Phone,Type,Name,Email)
 VALUES ('123 Rune St','London','On', 'N1N-1N1','(555)555-5599','Untrusted','Edon Mart','er@mart.com');


-- add some expenses to seed the table
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P01',1,'Bronze',1.06,5.00,25,200,1000,400);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P02',1,'Iron',2.20,8.00,25,200,1000,400);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P03',1,'Steel',3.30,9.49,25,200,1000,400);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P04',2,'Mithril',4.54,11.29,25,200,1000,400);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P05',2,'Adamant',6.02,13.75,25,200,1000,400);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P06',2,'Runite',8.18,15.99,25,200,1000,400);
 INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P07',3,'Coal',4.54,11.29,25,200,1000,400);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P08',3,'Gold',6.02,13.75,25,200,1000,400);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P09',3,'Silver',8.18,15.99,25,200,1000,400);
 INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P10',4,'Dragon',11.39,19.99,25,200,1000,400);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P11',4,'Bandos',14.73,24.99,25,200,1000,400);
INSERT INTO Product (Id,VendorId,Name,CostPrice,Msrp,Rop,Eoq,Qoh,Qoo)
 VALUES ('P12',4,'Armadyl',15.70,27.99,25,200,1000,400);

