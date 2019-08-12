package com.test.keytotech.mvp.base;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface BaseView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void toggleLoading(boolean visible, int messageId);
}
