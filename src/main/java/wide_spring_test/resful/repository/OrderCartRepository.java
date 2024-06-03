package wide_spring_test.resful.repository;

import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wide_spring_test.resful.entity.OrderCart;

public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {
    @Query(
            value = "SELECT * FROM order_carts WHERE product_id = ?1",
            nativeQuery = true)
    OrderCart findProductInOrderCart(Long product_id);
}
