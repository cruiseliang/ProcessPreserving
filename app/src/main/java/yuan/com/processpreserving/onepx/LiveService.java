package yuan.com.processpreserving.onepx;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Liang on 2018/8/29 0029.
 * 在服务中启动广播监听屏幕的开闭，打开liveActivity，使得进程不被关闭
 * 5.0以后，在应用退出后，ActivityManagerService不仅把主进程给杀死，另外把主进程所属的进程组一
 * 并杀死，这样一来，由于子进程和主进程在同一进程组，子进程在做的事情，也就停止了。所以在Android5.0以后的手机应用在进程被杀死后，要采用其他方案。
 */

public class LiveService extends Service {
    public  static void toLiveService(Context pContext){
        Intent intent=new Intent(pContext,LiveService.class);
        pContext.startService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //屏幕关闭的时候启动一个1像素的Activity，开屏的时候关闭Activity
        final ScreenManager screenManager = ScreenManager.getInstance(LiveService.this);
        ScreenBroadcastListener listener = new ScreenBroadcastListener(this);
        listener.registerListener(new ScreenBroadcastListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                screenManager.finishActivity();
            }
            @Override
            public void onScreenOff() {
                screenManager.startActivity();
            }
        });
        return START_REDELIVER_INTENT;
    }
}
