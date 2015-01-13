package com.huawei.service;

import com.huawei.domain.Faq;

public interface IFaqService<T extends Faq> extends IService<T> {
	void modifyHandle(String ids);
}
