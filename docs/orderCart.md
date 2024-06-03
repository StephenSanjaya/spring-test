# OrderCart API Spec

## Create OrderCart

Endpoint : POST /api/carts

Request Body :
```json
{
  "product_id": 1,
  "quantity": 2
}
```

Response Body (Success) :
```json
{
  "data": {
    "id": 1,
    "quantity": 2,
    "sub_total": 800000.0,
    "product": {
      "id": 1,
      "name": "Book A",
      "type": "Book",
      "price": 400000.0
    }
  },
  "message": "Success add product to cart"
}
```

Response Body (Failed) :
```json
{
  "message": "Products not found"
}
```

## Get Products From OrderCart

Endpoint : GET /api/carts

Response Body (Success) :
```json
{
  "datas": [
    {
      "id": 1,
      "quantity": 5,
      "sub_total": 2000000.0,
      "product": {
        "id": 1,
        "name": "Book A",
        "type": "Book",
        "price": 400000.0
      }
    }
  ],
  "message": "Success get all product from cart"
}
```

Response Body (Failed) :
```json
{
  "message": "Failed to get all products form order cart"
}
```

## Place Order

Endpoint : POST /api/carts/place-orders

Request Body :
```json
{
  "id": [
    1
  ]
}
```

Response Body (Success) :
```json
{
  "data": {
    "total_price": 300000.0,
    "order_date": "2024-06-02T14:43:47.354+00:00"
  },
  "message": "Success place order"
}
```

Response Body (Failed) :
```json
{
  "message": "order cart not found"
}
```