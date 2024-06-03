package wide_spring_test.resful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import wide_spring_test.resful.entity.OrderCart;
import wide_spring_test.resful.entity.Product;
import wide_spring_test.resful.model.*;
import wide_spring_test.resful.repository.OrderCartRepository;
import wide_spring_test.resful.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderCartService {

    @Autowired
    private OrderCartRepository orderCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public OrderCartResponse create(CreateOrderCartRequest request) {
        Product product = productRepository.findById(request.getProduct_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));

        OrderCart orderCart = new OrderCart();
        double sub_total = 0;
        if (orderCartRepository.findProductInOrderCart(product.getId()) != null){
            orderCart = orderCartRepository.findProductInOrderCart(product.getId());
            request.setQuantity(orderCart.getQuantity()+request.getQuantity());
        }

        sub_total = request.getQuantity() * product.getPrice();

        orderCart.setProduct_id(request.getProduct_id());
        orderCart.setQuantity(request.getQuantity());
        orderCart.setSub_total(sub_total);
        orderCart.setProduct(product);

        orderCartRepository.save(orderCart);

        return toOrderCartResponse(orderCart);
    }

    private OrderCartResponse toOrderCartResponse(OrderCart orderCart) {
        ProductResponse productResponse = new ProductResponse();

        if (orderCart.getProduct() == null){
            Product product = productRepository.findById(orderCart.getProduct_id())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
            orderCart.setProduct(product);
        }

        productResponse.setId(orderCart.getProduct().getId());
        productResponse.setName(orderCart.getProduct().getName());
        productResponse.setType(orderCart.getProduct().getType());
        productResponse.setPrice(orderCart.getProduct().getPrice());

        return OrderCartResponse.builder()
                .id(orderCart.getId())
                .quantity(orderCart.getQuantity())
                .sub_total(orderCart.getSub_total())
                .product(productResponse)
                .build();
    }

    public List<OrderCartResponse> getAll() {
        List<OrderCart> orderCarts = orderCartRepository.findAll();

        List<OrderCartResponse> orderCartResponses = orderCarts.stream()
                .map(this::toOrderCartResponse)
                .toList();

        return orderCartResponses;
    }

    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {

        double total_price = 0.0;
        for (int i=0;i<request.getId().size();i++){
            OrderCart orderCarts = orderCartRepository.findById(request.getId().get(i))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "order cart not found"));

            orderCartRepository.deleteById(request.getId().get(i));

            total_price += orderCarts.getSub_total();
        }

        java.util.Date date = new java.util.Date();
        PlaceOrderResponse placeOrderResponse = new PlaceOrderResponse();
        placeOrderResponse.setOrder_date(date);
        placeOrderResponse.setTotal_price(total_price);

        return placeOrderResponse;
    }

}
