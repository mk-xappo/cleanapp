package de.xappo.test_android_med_unlimited101.main;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
    void inject(MainPresenter mainPresenter);
}
