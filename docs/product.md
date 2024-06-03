# Product API Spec

## Create Product

Endpoint : POST /api/products

Request Body :
```json
{
  "name": "Head First Java",
  "type": "Book",
  "price": 350000
}
```

Response Body (Success) :
```json
{
  "message": "Success create new product",
  "data": {
    "id": 1,
    "name": "Head First Java",
    "type": "Book",
    "price": 350000
  }
}
```

Response Body (Failed) :
```json
{
  "message": "Failed to create new product"
}
```

## Get Products

Endpoint : GET /api/products

Query Param :

- name : String, product name
- type : String, product type
- page : Integer, start from 0, default 0
- size : Integer, default 3

Response Body (Success) :
```json
{
  "datas": [
    {
      "id": 1,
      "name": "Head First Java",
      "type": "Book",
      "price": 350000
    }
  ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" : 3
  }
}
```

Response Body (Failed) :
```json
{
  "message": "Failed to get products"
}
```

## Get Product By ID

Endpoint : GET /api/products/{id}

Response Body (Success) :
```json
{
  "data": {
      "id": 1,
      "name": "Head First Java",
      "type": "Book",
      "price": 350000
    }
}
```

Response Body (Failed) :
```json
{
  "message": "Product is not found"
}
```

## Update Product

Endpoint : PUT /api/products/{id}

Request Body :
```json
{
  "name": "Head First Java",
  "type": "Book",
  "price": 350000
}
```

Response Body (Success) :
```json
{
  "message": "Success to update product",
  "data": {
    "id": 1,
    "name": "Head First Java",
    "type": "Book",
    "price": 350000
  }
}
```

Response Body (Failed) :
```json
{
  "message": "Product is not found"
}
```

## Delete Product

Endpoint : DELETE /api/products/{id}

Response Body (Success) :
```json
{
  "message": "Success to delete product"
}
```

Response Body (Failed) :
```json
{
  "message": "Product is not found"
}
```