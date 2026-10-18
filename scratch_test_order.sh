#!/bin/bash
curl -v -X POST \
  -H "Content-Type: application/json" \
  -d '{"numeroDocumento":"10604256301","producto":"Laptop ASUS ROG Strix SCAR 16","total":8999.00}' \
  http://localhost:8080/api/orders
