package com.huawei.service;

import com.huawei.domain.RegisterUser;

public interface IRegisterUserService <T extends RegisterUser> extends IService<T> {
	void addUser(RegisterUser u);
	void addShop(RegisterUser u);
}
