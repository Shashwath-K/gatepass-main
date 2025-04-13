CREATE TABLE USER (
    user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(100) NOT NULL
);

CREATE TABLE GATEPASS (
    gatepass_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    gatepass_no VARCHAR(50) NOT NULL,
    park VARCHAR(255),
    building VARCHAR(255),
    request_date DATE,
    vendor_name VARCHAR(255),
    material_description TEXT,
    category VARCHAR(255),
    return_acceptance_date DATE,
    outward_receipt VARCHAR(255),
    inward_receipt VARCHAR(255),
    image_attachment VARCHAR(255),
    status VARCHAR(100),
    requested_by INT,
    approved_by INT,
    FOREIGN KEY (requested_by) REFERENCES USER(user_id),
    FOREIGN KEY (approved_by) REFERENCES USER(user_id)
);

CREATE TABLE SECURITY_CHECK (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    gatepass_id INT NOT NULL,
    security_id INT NOT NULL,
    status VARCHAR(100),
    check_date DATE,
    FOREIGN KEY (gatepass_id) REFERENCES GATEPASS(gatepass_id)
);