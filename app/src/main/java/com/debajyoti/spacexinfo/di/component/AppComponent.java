package com.debajyoti.spacexinfo.di.component;

import android.app.Application;

import com.debajyoti.spacexinfo.SpacexApp;
import com.debajyoti.spacexinfo.di.module.ActivityModule;
import com.debajyoti.spacexinfo.di.module.AppModule;
import com.debajyoti.spacexinfo.di.module.BroadcastReceiverModule;
import com.debajyoti.spacexinfo.di.module.ServiceModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules =
        {
                ActivityModule.class,
                AppModule.class,
                ServiceModule.class,
                BroadcastReceiverModule.class,
                AndroidSupportInjectionModule.class
        }
)
public interface AppComponent extends AndroidInjector<SpacexApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
