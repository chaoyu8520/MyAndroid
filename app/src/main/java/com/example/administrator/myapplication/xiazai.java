package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.path;
import static android.content.ContentValues.TAG;


/**
 * Create chaoyu on 2017/2/10.
 */
public class xiazai extends Activity {
    private Button button1;
    private static final String URL_STRING = "http://dc.com/ceshi3.apk";
    private static int down = 0;
    File file;

    //通知栏进度条
    private NotificationManager mNotificationManager=null;
    private Notification mNotification;
    private int fileSize = 0;
    int downLoadFileSize = 0;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    button1.setText("点击安装");
                    down = 1;
                    break;
                case 2:
                    down = 2;
                    button1.setText("打开");
                    break;
                case 3:
                    int result = downLoadFileSize * 100 / fileSize;
                    mNotification.contentView.setTextViewText(R.id.content_view_text1, result + "%");
                    mNotification.contentView.setProgressBar(R.id.content_view_progress, fileSize, downLoadFileSize, false);
                    mNotificationManager.notify(0, mNotification);
                    if (result == 100){
                        mNotification.contentView.setTextViewText(R.id.content_view_text1, "下载完成");
                        installApk();
                    }
                    break;
            }
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiazai);
        button1 = (Button) findViewById(R.id.xiazai);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 下载apk
                if (down == 0) {
                    notificationInit();
                    downFile(URL_STRING);
                    button1.setText("正在下载");
                    // 安装APK
                } else if (down == 1) {
                    installApk();
                    // 打开apk
                } else if (down == 2) {
                    openApk(xiazai.this, URL_STRING);
                }
            }
        });
    }
    // 接收到安装完成apk的广播
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            System.out.println("接收到安装完成apk的广播");

            Message message = handler.obtainMessage();
            message.what = 2;
            handler.sendMessage(message);
        }
    };
    /**
     * 后台在下面一个Apk 下载完成后返回下载好的文件
     *
     * @param httpUrl
     * @return
     */
    private File downFile(final String httpUrl) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL(httpUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
//                    String filename = httpUrl.substring(httpUrl.lastIndexOf("/") + 1);
                    FileOutputStream fileOutputStream;
                    InputStream inputStream;
                    if (connection.getResponseCode() == 200) {
                        inputStream = connection.getInputStream();

                        fileSize = connection.getContentLength();// 根据响应获取文件大小
                        if (inputStream != null) {
                            file = getFile(httpUrl);
                            fileOutputStream = new FileOutputStream(file);
                            byte[] buffer = new byte[1024];
                            int length = 0;
                            int loadNum = 0;
                            while ((length = inputStream.read(buffer)) > 0) {
                                Log.d(TAG, "length:"+length);
                                fileOutputStream.write(buffer, 0, length);
                                downLoadFileSize += length;
                                int result = downLoadFileSize * 100 / fileSize;
                                if (result%10 == 0 && loadNum != result){
                                    Log.e(TAG, "推送:"+result);
                                    sendMsg(3);
                                }
                                loadNum = result;
                            }
                            fileOutputStream.close();
                            fileOutputStream.flush();
                            inputStream.close();
                        }
                    }

                    System.out.println("已经下载完成");
                    // 往handler发送一条消息 更改button的text属性
                    sendMsg(1);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return file;
    }
    private void sendMsg(int flag) {
        Log.d(TAG, "case:"+flag);
        Message msg = handler.obtainMessage();
        msg.what = flag;
        handler.sendMessage(msg);
    }
    private void notificationInit(){
        //通知栏内显示下载进度条
        Intent intent=new Intent(this,xiazai.class);//点击进度条，进入程序
        PendingIntent pIntent= PendingIntent.getActivity(this, 0, intent, 0);
        mNotificationManager=(NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        mNotification=new Notification();
        mNotification.icon=R.mipmap.ic_launcher;
        mNotification.tickerText="开始下载";
        mNotification.contentView=new RemoteViews(getPackageName(),R.layout.content_view);//通知栏中进度布局
        mNotification.contentIntent=pIntent;
//        mNotificationManager.notify(0,mNotification);
    }

    /**
     * 安装APK
     */
    private void installApk() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addDataScheme("package");

        // 注册一个广播
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解除广播
        unregisterReceiver(broadcastReceiver);
    }

    /**
     * 打开已经安装好的apk
     */
    private void openApk(Context context, String url) {
        PackageManager manager = context.getPackageManager();
        // 这里的是你下载好的文件路径
        PackageInfo info = manager.getPackageArchiveInfo(Environment.getExternalStorageDirectory().getAbsolutePath()
                + getFilePath(url), PackageManager.GET_ACTIVITIES);
        if (info != null) {
            Intent intent = manager.getLaunchIntentForPackage(info.applicationInfo.packageName);
            startActivity(intent);
        }
    }

    /**
     * 根据传过来url创建文件
     *
     */
    private File getFile(String url) {
        File files = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), getFilePath(url));
        return files;
    }

    /**
     * 截取出url后面的apk的文件名
     *
     * @param url
     * @return
     */
    private String getFilePath(String url) {
        return url.substring(url.lastIndexOf("/"), url.length());
    }
}