USE gatepass_db;

-- Insert sample gatepass
INSERT INTO gatepass (
    gatepass_number, issue_date, block, site_address,
    building_manager_name, building_manager_contact,
    vendor_name, vendor_contact, vendor_address,
    requisitioner, pm_representative, reit_representative,
    approval_datetime, outward_date, inward_date,
    category, status, return_acceptance_date
) VALUES (
    'GP-2025-001', '2025-04-15', 'Block A', '123 Tech Park, Bengaluru',
    'Anil Kumar', '9876543210',
    'Tech Supplies Ltd.', '9123456780', '456 Industrial Area, Bengaluru',
    'Ravi Sharma', 'Meena Joshi', 'Suresh Rao',
    '2025-04-14 10:30:00', '2025-04-15', NULL,
    'Returnable', 'Pending', NULL
);

-- Insert sample asset details
INSERT INTO asset_detail (
    gatepass_id, asset_name, material_description, quantity, value, remarks
) VALUES
(
    1, 'Laptop', 'Dell Latitude 5420', 5, 350000.00, 'For software development'
),
(
    1, 'Projector', 'Epson EB-X41', 2, 80000.00, 'For conference rooms'
);
