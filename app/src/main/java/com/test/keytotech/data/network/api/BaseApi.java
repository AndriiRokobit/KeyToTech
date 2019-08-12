package com.test.keytotech.data.network.api;

import com.test.keytotech.data.network.constant.Endpoints;
import com.test.keytotech.data.network.constant.Params;
import com.test.keytotech.model.CommentsResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BaseApi {
    @GET(Endpoints.COMMENTS)
    Single<List<CommentsResponse>> loadComments(@Path(Params.POST_ID) int postId);
}
