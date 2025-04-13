CREATE TABLE user (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(100) NOT NULL
);

CREATE TABLE gatepass (
    gatepass_id SERIAL PRIMARY KEY,
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
    requested_by INT REFERENCES user(user_id),
    approved_by INT REFERENCES user(user_id)
);

CREATE TABLE security_check (
    id SERIAL PRIMARY KEY,
    gatepass_id INT REFERENCES gatepass(gatepass_id),
    security_id INT NOT NULL,
    status VARCHAR(100),
    check_date DATE
);
