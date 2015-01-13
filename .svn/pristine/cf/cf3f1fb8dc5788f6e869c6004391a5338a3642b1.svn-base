package com.huawei.service;

import java.util.List;

import com.huawei.domain.WeiBo;

public interface IWeiBoService<T extends WeiBo> extends IService<T> {
	
	void increaseComtimes(WeiBo weibo);
    void increaseResendTimes(WeiBo weibo);
	void add(WeiBo weiBo, WeiBo oldWeiBo,WeiBo originalWeiBo,long[] friendIds,int type);
}
