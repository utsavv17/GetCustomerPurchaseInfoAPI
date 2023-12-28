# GetCustomerPurchaseInfoAPI
an API to get customers with all purchase order



note: if you want to add shipping table coloumn :
1-Address
2-city
3-pincode

change query in 6 question(for flipr test)
        "SELECT " + "c.customer_id, p.purchase_order_id, p.product_name, p.quantity, s.address, s.city, s.pincode " +
                    "FROM customer c " +
                    "JOIN purchase p ON c.customer_id = p.customer_id " +
                    "JOIN shipping s ON p.purchase_order_id = s.purchase_order_id";

and print this using out.println() methon in given format
