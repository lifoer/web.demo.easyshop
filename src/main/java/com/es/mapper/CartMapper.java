package com.es.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.es.pojo.Cart;

public interface CartMapper {

	public void insertCart(Cart cart);

	public List<Map<?, ?>> queryCart(String userId);

	public Cart checkCart(@Param(value = "userId") String userId, @Param(value = "goodsId") String goodsId);

	public void updateCart(@Param(value = "userId") String userId, @Param(value = "goodsId") String goodsId,
			@Param(value = "num") Integer num);

	public void deleteCart(@Param(value = "userId") String userId, @Param(value = "goodsId") String goodsId);

	public void deleteAllCart(String userId);

}
