package com.eastcom.social.tinkerdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

@DefaultLifeCycle(
        application = ".MyApplication", flags = ShareConstants.TINKER_ENABLE_ALL
)
public class MyApplicationLike extends ApplicationLike {


    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        TinkerInstaller.install(this);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
//        TinkerInstaller.install(this);
//        TinkerInstaller.install(this, new DefaultLoadReporter(getApplication())
//                , new DefaultPatchReporter(getApplication()), new DefaultPatchListener(getApplication()), MyTinkerResultService.class, new UpgradePatch());
        TinkerInstaller.install(this, new MyLoadReporter(getApplication())
                , new DefaultPatchReporter(getApplication()), new DefaultPatchListener(getApplication()), MyTinkerResultService.class, new UpgradePatch());
        Tinker tinker = Tinker.with(getApplication());
    }
}