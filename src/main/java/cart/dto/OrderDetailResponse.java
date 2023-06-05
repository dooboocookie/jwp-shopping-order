package cart.dto;

import cart.domain.Order;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderDetailResponse {
    private Long id;
    private List<OrderItemResponse> products;
    private Integer totalPrice;
    private Integer discountPrice;
    private Integer shippingFee;

    public OrderDetailResponse() {
    }

    public OrderDetailResponse(final Long id, final List<OrderItemResponse> products, final Integer totalPrice, final Integer discountPrice, final Integer shippingFee) {
        this.id = id;
        this.products = products;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
        this.shippingFee = shippingFee;
    }

    public static OrderDetailResponse from(final Order order) {
        List<OrderItemResponse> orderItemResponses = order.getOrderItems()
                .stream()
                .map(OrderItemResponse::from)
                .collect(Collectors.toList());
        return new OrderDetailResponse(
                order.getId(),
                orderItemResponses,
                order.calculateTotalPrice(),
                order.calculateDiscountPrice(),
                order.getShippingFee().getCharge()
        );
    }

    public Long getId() {
        return id;
    }

    public List<OrderItemResponse> getProducts() {
        return products;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public Integer getShippingFee() {
        return shippingFee;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderDetailResponse that = (OrderDetailResponse) o;
        return Objects.equals(id, that.id)
                && Objects.equals(products, that.products)
                && Objects.equals(totalPrice, that.totalPrice)
                && Objects.equals(discountPrice, that.discountPrice)
                && Objects.equals(shippingFee, that.shippingFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, totalPrice, discountPrice, shippingFee);
    }

    @Override
    public String toString() {
        return "OrderDetailResponse{" +
                "orderId=" + id +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                ", discountPrice=" + discountPrice +
                ", shippingFee=" + shippingFee +
                '}';
    }
}
