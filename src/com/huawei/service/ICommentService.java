package com.huawei.service;

import com.huawei.domain.Comment;

public interface ICommentService <T extends Comment> extends IService<T>{
	void addComment(Comment comment,long registerUserId);
	void addComment(Comment comment,long[] ids);
	
}