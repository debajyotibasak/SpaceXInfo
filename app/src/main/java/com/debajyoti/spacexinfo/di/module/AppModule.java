package com.debajyoti.spacexinfo.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.debajyoti.spacexinfo.api.ApiInterface;
import com.debajyoti.spacexinfo.api.model.PastLaunch;
import com.debajyoti.spacexinfo.db.SpacexDb;
import com.debajyoti.spacexinfo.db.dao.InfoDao;
import com.debajyoti.spacexinfo.db.dao.LatestLaunchDao;
import com.debajyoti.spacexinfo.db.dao.NextLaunchDao;
import com.debajyoti.spacexinfo.db.dao.PastLaunchesDao;
import com.debajyoti.spacexinfo.db.dao.RocketsDao;
import com.debajyoti.spacexinfo.repo.PreferencesRepo;
import com.debajyoti.spacexinfo.repo.SpacexRepo;
import com.debajyoti.spacexinfo.utils.AppConstants;
import com.debajyoti.spacexinfo.utils.AppExecutor;
import com.debajyoti.spacexinfo.utils.MainThreadExecutor;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {ViewModelModule.class})
public class AppModule {

    @Provides
    @Singleton
    FirebaseAnalytics providesFirebaseAnalytics(Application application) {
        return FirebaseAnalytics.getInstance(application);
    }

    @Provides
    @Singleton
    PreferencesRepo providePreferencesRepository(SharedPreferences preferences, Gson gson, Type type) {
        return new PreferencesRepo(preferences, gson, type);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    SpacexRepo provideRepository(ApiInterface apiInterface,
                                 InfoDao infoDao,
                                 RocketsDao rocketsDao,
                                 PastLaunchesDao pastLaunchesDao,
                                 NextLaunchDao nextLaunchDao,
                                 LatestLaunchDao latestLaunchDao,
                                 AppExecutor executor) {

        return new SpacexRepo(apiInterface, infoDao, rocketsDao, pastLaunchesDao, nextLaunchDao, latestLaunchDao, executor);
    }

    @Provides
    AppExecutor provideAppExecutor() {
        return new AppExecutor(Executors.newSingleThreadExecutor(), new MainThreadExecutor());
    }

    @Provides
    @Singleton
    SpacexDb provideDatabase(Application application) {
        return Room.databaseBuilder(application, SpacexDb.class, AppConstants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    InfoDao provideInfoDao(SpacexDb database) {
        return database.infoDao();
    }

    @Provides
    @Singleton
    RocketsDao provideRocketsDao(SpacexDb database) {
        return database.rocketsDao();
    }

    @Provides
    @Singleton
    PastLaunchesDao providePastLaunchesDao(SpacexDb database) {
        return database.pastLaunchesDao();
    }

    @Provides
    @Singleton
    LatestLaunchDao provideLatestLaunchDao(SpacexDb database) {
        return database.latestLaunchDao();
    }

    @Provides
    @Singleton
    NextLaunchDao provideNextLaunchDao(SpacexDb database) {
        return database.nextLaunchDao();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Type provideType() {
        return new TypeToken<List<PastLaunch>>() {}.getType();
    }

    @Provides
    OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
