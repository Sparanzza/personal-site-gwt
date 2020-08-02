package com.sparanzza.website.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.intendia.reactivity.client.*;
import com.intendia.reactivity.client.PlaceManager.DefaultHistorian;
import com.intendia.reactivity.client.PlaceManager.Historian;
import com.sparanzza.website.client.application.*;
import com.sparanzza.website.client.resources.Resources;
import dagger.Component;
import dagger.Module;
import dagger.*;
import dagger.multibindings.IntoSet;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.function.Supplier;

import static com.intendia.reactivity.client.PlaceNavigator.PlaceNavigation.noop;
import static com.intendia.reactivity.client.PlaceRequest.of;



public class ApplicationEntryPoint implements EntryPoint {


    @Override
    public void onModuleLoad() {
        Resources.inject();
        DaggerApplicationEntryPoint_ClientComponent.create().router().revealCurrentPlace();
    }

    @Component(modules = ClientModule.class)
    @Singleton
    interface ClientComponent {
        PlaceManager router();
    }

    @Module(includes = DefaultModule.class, subcomponents = ClientModule.Presenters.class)
    public interface ClientModule {
        // place navigator; required by to handle default and error
        static @Provides
        PlaceNavigator providePlaceNavigator() {
            return new PlaceNavigator() {
                @Override
                public PlaceNavigation defaultNavigation() {
                    return noop(of(NameTokens.HOMEPAGE.getPath()).build());
                }

                @Override
                public PlaceNavigation errorNavigation(Throwable throwable) {
                    return noop(of(NameTokens.ERRORPAGE.getPath()).build());
                }
            };
        }

        //
        @Binds @IntoSet Place bindEmptyPlace(ErrorPresenter.MyPlace o); // included in initial bundle
        @Binds @IntoSet Place bindHomePlace(HomePresenter.MyPlace o); // loaded using code splitting when any of this presenters gets visited
        @Binds @IntoSet Place bindSayHelloPlace(SayHelloPresenter.MyPlace o);
        @Binds @IntoSet Place bindCurriculumVitaePlace(CurriculumVitaePresenter.MyPlace o);
        @Binds @IntoSet Place bindSkillsPlace(SkillsPresenter.MyPlace o);
        @Binds @IntoSet Place bindLastStepsPlace(LastStepsPresenter.MyPlace o);

        // we group and hide presenters to encourage code-splitting
        @Subcomponent
        interface Presenters {
            HomePresenter home();
            SayHelloPresenter sayHello();
            SkillsPresenter skills();
            ErrorPresenter error();
            LastStepsPresenter lastSteps();
            CurriculumVitaePresenter curriculumVitae();
            @Subcomponent.Builder
            interface Builder extends Supplier<Presenters> {
            }
        }

        // and we use a async lazy Presenters component to access to all the presenter in the split
        @Provides
        @Singleton
        static Single<Presenters> presenters(Presenters.Builder builder) {
            return Single.create(s -> GWT.runAsync(new RunAsyncCallback() {
                @Override
                public void onFailure(Throwable reason) {
                    s.onError(reason);
                }

                @Override
                public void onSuccess() {
                    s.onSuccess(builder.get());
                }
            }));
        }
    }

    @Module
    public interface DefaultModule {
        @Provides @Singleton static EventBus provideEventBus() { return new SimpleEventBus(); }
        @Provides @Singleton static Historian provideHistorian() { return new DefaultHistorian(); }
        @Binds @Singleton TokenFormatter provideTokenFormatter(ParameterTokenFormatter o);
    }
}
