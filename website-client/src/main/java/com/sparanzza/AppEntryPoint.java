package com.sparanzza;

import static com.intendia.reactivity.client.PlaceNavigator.PlaceNavigation.noop;
import static com.intendia.reactivity.client.PlaceRequest.of;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.intendia.reactivity.client.ParameterTokenFormatter;
import com.intendia.reactivity.client.Place;
import com.intendia.reactivity.client.PlaceManager;
import com.intendia.reactivity.client.PlaceManager.DefaultHistorian;
import com.intendia.reactivity.client.PlaceManager.Historian;
import com.intendia.reactivity.client.PlaceNavigator;
import com.intendia.reactivity.client.TokenFormatter;
import com.sparanzza.application.AboutUsPresenter;
import com.sparanzza.application.ContactPresenter;
import com.sparanzza.application.EmptyPresenter;
import com.sparanzza.application.HomePresenter;
import com.sparanzza.resources.Resources;
import dagger.Binds;
import dagger.Provides;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.multibindings.IntoSet;
import io.reactivex.Single;
import javax.inject.Singleton;
import java.util.function.Supplier;





public class AppEntryPoint implements EntryPoint {


    @Override public void onModuleLoad() {
        Resources.inject();
        DaggerAppEntryPoint_ClientComponent.create().router().revealCurrentPlace();
    }

    @Component(modules = ClientModule.class) @Singleton interface ClientComponent {
        PlaceManager router();
    }

    @Module(includes = DefaultModule.class, subcomponents = ClientModule.Presenters.class)
    public interface ClientModule {
        // place navigator; required by to handle default and error
        static @Provides
        PlaceNavigator providePlaceNavigator() {
            return new PlaceNavigator() {
                @Override public PlaceNavigation defaultNavigation() { return noop(of(NameTokens.homePage).build()); }
                @Override public PlaceNavigation errorNavigation(Throwable throwable) {
                    return noop(of(NameTokens.emptyPage).build());
                }
            };
        }

        // included in initial bundle
        @Binds @IntoSet Place bindEmptyPlace(EmptyPresenter.MyPlace o);

        // loaded using code splitting when any of this presenters gets visited
        @Binds @IntoSet Place bindHomePlace(HomePresenter.MyPlace o);
        @Binds @IntoSet Place bindAboutUsPlace(AboutUsPresenter.MyPlace o);
        @Binds @IntoSet Place bindContactPlace(ContactPresenter.MyPlace o);

        // we group and hide presenters to encourage code-splitting
        @Subcomponent
        interface Presenters {
            HomePresenter home();
            AboutUsPresenter aboutUs();
            ContactPresenter contact();
            @Subcomponent.Builder interface Builder extends Supplier<Presenters> {}
        }

        // and we use a async lazy Presenters component to access to all the presenter in the split
        @Provides @Singleton static Single<Presenters> presenters(Presenters.Builder builder) {
            return Single.create(s -> GWT.runAsync(new RunAsyncCallback() {
                @Override public void onFailure(Throwable reason) { s.onError(reason); }
                @Override public void onSuccess() { s.onSuccess(builder.get()); }
            }));
        }
    }

    @Module
    public interface DefaultModule {
        @Provides @Singleton static EventBus provideEventBus() { return new SimpleEventBus(); }
        @Provides @Singleton static Historian provideHistorian() { return new DefaultHistorian(); }
        @Binds @Singleton
        TokenFormatter provideTokenFormatter(ParameterTokenFormatter o);
    }
}
