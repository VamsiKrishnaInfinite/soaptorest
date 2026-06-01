# SoapToRest UI - Usage Guide

## Accessing the Dashboard

Once the application is running on `http://localhost:8080`, open your browser and navigate to:

```
http://localhost:8080/index.html
```

## Features

### Dashboard
- View summary statistics (total customers, products, orders)
- Refresh stats with one click

### Customers
- **Create Customer**: Fill in first name, last name, email, and phone to register a new customer
- **Search Customers**: View all customers with pagination
- **View Details**: Click "View" to see customer ID

### Products
- **Create Product**: Add a new product with name, brand, type, prices, and description
- **Search Products**: Browse all products with pagination
- **Filter**: Search by keyword or brand

### Orders
- **Create Order**: Place an order by entering a customer ID
- **View Orders**: List all orders with details including order number, amount, status, and dates
- **Track Status**: See current order status (PENDING_PAYMENT, etc.)

### Inventory
- **Check Inventory**: Get stock levels for a specific variant and warehouse
- **Adjust Stock**: Add or remove inventory with quantity delta
- **View Levels**: See quantity, reserved, and available stock

## Validations

- All required fields must be filled before submission
- Email must be valid format
- Prices must be numeric values
- Customer ID, Variant ID, and Warehouse ID must be valid UUIDs

## Response Handling

- **Success**: Green alert message confirms the operation
- **Error**: Red alert message displays error details
- **Info**: Blue message for informational alerts

Auto-dismiss: Messages automatically disappear after 5 seconds

## Tips

1. Create a customer first - you'll need the Customer ID to create orders
2. Create products before managing inventory
3. All data is persisted in MongoDB (ensure MongoDB is running on localhost:27017)
4. Timestamps are shown in your browser's local timezone

## Browser Support

- Chrome (recommended)
- Firefox
- Safari
- Edge
- Any modern browser with ES6 support

## Keyboard Shortcuts

- Tab: Navigate between form fields
- Enter: Submit form (in text inputs)

