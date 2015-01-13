package com.huawei.service;

import com.huawei.domain.ShopComment;

public interface IShopCommentService <T extends ShopComment> extends IService<T>{
	void add(ShopComment shopComment,long sellerId);
}