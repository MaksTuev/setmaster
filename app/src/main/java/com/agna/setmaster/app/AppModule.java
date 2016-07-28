package com.agna.setmaster.app;

import android.content.Context;

import com.agna.setmaster.module.condition.ConditionModule;
import com.agna.setmaster.module.profile.ProfileModule;
import com.agna.setmaster.module.service.AppServiceInteractor;
import com.agna.setmaster.module.setting.SettingModule;
import com.agna.setmaster.module.storage.db.DataBaseHelper;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        ConditionModule.class,
        SettingModule.class,
        ProfileModule.class})
public class AppModule {
        Context appContext;

        public AppModule(Context appContext) {
                this.appContext = appContext;
        }

        @PerApplication
        @Provides
        Context provideContext(){
                return appContext;
        }

        @PerApplication
        @Provides
        DataBaseHelper provideDataBaseHelper(Context context){
                return new DataBaseHelper(context);
        }

        @PerApplication
        @Provides
        AppServiceInteractor provideAppServiceInteractor(Context context){
                return new AppServiceInteractor(context);
        }
}
