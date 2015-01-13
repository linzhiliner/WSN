package com.huawei.service;

import com.huawei.domain.Coupon;
import com.huawei.domain.ExchangedCou;

public interface ICouponService<T extends Coupon> extends IService<T> {
	void exchanged(ExchangedCou exchangedCou);
}
