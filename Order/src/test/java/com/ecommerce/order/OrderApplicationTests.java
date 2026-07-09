package com.ecommerce.order;

import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.repository.CartItemRepository;
import com.ecommerce.order.service.OrderService;
import com.ecommerce.order.dto.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderApplicationTests {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Test
	void testCreateOrderTotalPriceCalculation() {
		// Set up cart items
		CartItem item1 = new CartItem();
		item1.setUserId(1L);
		item1.setProductId(101L);
		item1.setQuantity(2);
		item1.setPrice(BigDecimal.valueOf(100.00));
		cartItemRepository.save(item1);

		CartItem item2 = new CartItem();
		item2.setUserId(1L);
		item2.setProductId(102L);
		item2.setQuantity(3);
		item2.setPrice(BigDecimal.valueOf(50.00));
		cartItemRepository.save(item2);

		// Call createOrder
		Optional<OrderResponse> responseOpt = orderService.createOrder(1L);

		assertTrue(responseOpt.isPresent());
		OrderResponse response = responseOpt.get();

		// Total price should be (100 * 2) + (50 * 3) = 350.00
		assertEquals(BigDecimal.valueOf(350.00).stripTrailingZeros(), response.getTotalAmount().stripTrailingZeros());
	}

}
