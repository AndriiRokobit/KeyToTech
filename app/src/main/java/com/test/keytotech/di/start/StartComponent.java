package com.test.keytotech.di.start;

import com.test.keytotech.mvp.start.StartPresenter;

import dagger.Subcomponent;


@Subcomponent(modules = {StartModule.class})
public interface StartComponent {
    StartPresenter provideStartPresenter();
}
