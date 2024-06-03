package wide_spring_test.resful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wide_spring_test.resful.model.*;
import wide_spring_test.resful.service.OrderCartService;

import java.util.List;

@RestController
public class OrderCartController {

    @Autowired
    private OrderCartService orderCartService;

    @PostMapping(
        path = "/api/carts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<OrderCartResponse> create(@RequestBody CreateOrderCartRequest request) {
        OrderCartResponse orderCartResponse = orderCartService.create(request);
        return WebResponse.<OrderCartResponse>builder().data(orderCartResponse).message("Success add product to cart").build();
    }

    @GetMapping(
            path = "/api/carts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<OrderCartResponse> getAll() {
        List<OrderCartResponse> orderCartResponses = orderCartService.getAll();
        return WebResponse.<OrderCartResponse>builder().datas(orderCartResponses).message("Success get all product from cart").build();
    }

    @PostMapping(
            path = "/api/carts/place-orders",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest request) {
        PlaceOrderResponse placeOrderResponse = orderCartService.placeOrder(request);
        return WebResponse.<PlaceOrderResponse>builder().data(placeOrderResponse).message("Success place order").build();
    }
}
