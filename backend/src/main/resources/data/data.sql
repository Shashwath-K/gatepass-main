INSERT INTO user (username, password, email, role) VALUES
('admin', 'admin123', 'admin@example.com', 'Admin'),
('security1', 'securepass', 'security1@example.com', 'Security'),
('user1', 'userpass', 'user1@example.com', 'User');

INSERT INTO gatepass (gatepass_no, park, building, request_date, vendor_name, material_description, category, return_acceptance_date, outward_receipt, inward_receipt, image_attachment, status, requested_by, approved_by) VALUES
('GP001', 'Park A', 'Building 1', '2024-04-01', 'Vendor X', 'Description of materials', 'Category A', '2024-04-10', 'Outward-001', 'Inward-001', 'image1.jpg', 'Approved', 1, 2);

INSERT INTO security_check (gatepass_id, security_id, status, check_date) VALUES
(1, 2, 'Verified', '2024-04-02');
