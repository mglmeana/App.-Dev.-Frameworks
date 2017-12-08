CREATE TABLE FLOWERS (  
  fType varchar(100) NOT NULL,
  fStock int(11) NOT NULL,
  fPrice double(4) NOT NULL,
  PRIMARY KEY (fType) 
);

CREATE TABLE ORDERS (
  oId varchar(50) NOT NULL,
  oShop varchar(100) NOT NULL,
  oPayed boolean NOT NULL,
  PRIMARY KEY (oId)
);

CREATE TABLE BELONGING (  
  packageId varchar(100) NOT NULL,
  flowerId int(11) NOT NULL,
  flowerAmount int(11) NOT NULL,
  PRIMARY KEY (packageId, flowerId),
  FOREIGN KEY (flowerId) REFERENCES FLOWERS(fType),
  FOREIGN KEY (packageId) REFERENCES ORDERS(oId)
);