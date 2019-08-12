package com.test.keytotech.di.app;

import com.test.keytotech.di.start.StartComponent;
import com.test.keytotech.mvp.start.StartActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppContextModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    StartComponent plusStartComponent();

    void inject(StartActivity startActivity);
}
