-- Create the database
CREATE DATABASE IF NOT EXISTS gatepass_db;
USE gatepass_db;

-- Create the gatepass table
CREATE TABLE IF NOT EXISTS gatepass (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gatepass_number VARCHAR(50) NOT NULL UNIQUE,
    issue_date DATE NOT NULL,
    block VARCHAR(100),
    site_address VARCHAR(255),

    -- Building Manager
    building_manager_name VARCHAR(100),
    building_manager_contact VARCHAR(20),

    -- Vendor Info
    vendor_name VARCHAR(100),
    vendor_contact VARCHAR(20),
    vendor_address VARCHAR(255),

    -- Approval
    requisitioner VARCHAR(100),
    pm_representative VARCHAR(100),
    reit_representative VARCHAR(100),
    approval_datetime DATETIME,

    -- Movement
    outward_date DATE,
    inward_date DATE,

    -- Status
    category ENUM('Returnable', 'Non-Returnable') NOT NULL,
    status ENUM('Pending', 'Approved', 'Closed') NOT NULL,
    return_acceptance_date DATE,

    -- Timestamps
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create the asset_detail table
CREATE TABLE IF NOT EXISTS asset_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gatepass_id BIGINT NOT NULL,
    asset_name VARCHAR(100),
    material_description VARCHAR(255),
    quantity INT,
    value DECIMAL(10,2),
    remarks VARCHAR(255),
    FOREIGN KEY (gatepass_id) REFERENCES gatepass(id) ON DELETE CASCADE
);
