package com.test.keytotech.mvp.start;

import com.test.keytotech.model.CommentsResponse;
import com.test.keytotech.mvp.base.BaseView;

import java.util.List;

import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface StartView extends BaseView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void successLoadingComments(List<CommentsResponse> list);
}
