package cart.domain;

public interface Coupon {
    boolean isAvailable(final Integer totalPrice);

    Integer calculateDiscount(final Integer totalPrice);
}
