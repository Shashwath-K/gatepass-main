// src/Pages/GatepassList.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';

function GatepassList() {
    const [gatepasses, setGatepasses] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        axios.get('/api/gatepass') // Relative path to hit proxy
            .then((response) => {
                setGatepasses(response.data.data || []);
                setLoading(false);
            })
            .catch((err) => {
                console.error('Error fetching gatepasses:', err);
                if (err.response) {
                    setError(`Backend error: ${err.response.status} - ${err.response.statusText}`);
                } else if (err.request) {
                    setError('Network error. Please check your connection.');
                } else {
                    setError('An unexpected error occurred.');
                }
                setLoading(false);
            });
    }, []);

    if (loading) return <div className="text-center mt-5">Loading gatepasses...</div>;
    if (error) return <div className="alert alert-danger text-center mt-4">{error}</div>;

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Gatepass List</h2>
            {gatepasses.length === 0 ? (
                <div className="text-center">No gatepasses found.</div>
            ) : (
                <div className="row">
                    {gatepasses.map((gatepass) => (
                        <div key={gatepass.id} className="col-md-4 mb-4">
                            <div className="card shadow-sm">
                                <div className="card-body">
                                    <h5 className="card-title">Gatepass #{gatepass.id}</h5>
                                    <p className="card-text"><strong>Status:</strong> {gatepass.status}</p>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default GatepassList;
