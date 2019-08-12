package com.test.keytotech.repository;

import com.test.keytotech.data.network.api.BaseApi;
import com.test.keytotech.di.scopes.Start;
import com.test.keytotech.model.CommentsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class StartRepository {

    private BaseApi baseApi;

    @Inject
    public StartRepository(BaseApi baseApi) {
        this.baseApi = baseApi;
    }

    public Single<List<CommentsResponse>> loadComments(int postId){
        return baseApi.loadComments(postId);
    }
}
