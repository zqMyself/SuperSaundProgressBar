package com.xiangjiale.saunprogressbar;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xiangjiale.saunprogressbar.widget.SaundProgressBar;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements SaundProgressBar.onLoadingCallBackListener {
    private SaundProgressBar mPbar;
    private int progress=0;
    private Message message;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int p=msg.what;
            mPbar.setProgress(p);
        }

    };


    private SaundProgressBar mPbar2;
    private int progress2=0;
    private Message message2;
    private Handler handler2=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int p=msg.what;
            mPbar2.setProgress(p);
        }

    };
    private GifImageView mActivity_gif_giv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity_gif_giv=(GifImageView) findViewById(R.id.activity_gif_giv);

        mPbar = (SaundProgressBar) this.findViewById(R.id.pb_downloading);
        mPbar.setMax(100);
        mPbar.setOnLoadingCallBackListener(this);
        mPbar.setProgress(0);
//		mPbar.setVisibility(View.VISIBLE);
        new Thread(runnable).start();


        mPbar2 = (SaundProgressBar) this.findViewById(R.id.pb_downloading2);
        mPbar2.setMax(100);
        mPbar2.setProgress(0);
        Drawable indicator = getResources().getDrawable(
                R.drawable.progress_indicator);
        Rect bounds = new Rect(0, 0, indicator.getIntrinsicWidth() + 5,
                indicator.getIntrinsicHeight());
        indicator.setBounds(bounds);
        mPbar2.setProgressIndicator(indicator);
//		mPbar.setVisibility(View.VISIBLE);
        new Thread(runnable2).start();
    }

    Runnable runnable=new Runnable() {

        @Override
        public void run() {
            message=handler.obtainMessage();
            // TODO Auto-generated method stub
            try {
                for (int i = 1; i <= 100; i++) {
                    int x=progress++;
                    message.what=x;
                    handler.sendEmptyMessage(message.what);
                    Thread.sleep(100);
                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };
    Runnable runnable2=new Runnable() {

        @Override
        public void run() {
            message2=handler.obtainMessage();
            // TODO Auto-generated method stub
            try {
                for (int i = 1; i <= 100; i++) {
                    int x=progress2++;
                    message2.what=x;
                    handler2.sendEmptyMessage(message2.what);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onLoadingCallBack(ProgressBar progressBar,int dx) {
        if(mActivity_gif_giv!=null){
            mActivity_gif_giv.setTranslationX(dx);
        }
    }

    @Override
    public void onFinish(ProgressBar progressBar) {
        Toast.makeText(getApplicationContext(),"加载完成",Toast.LENGTH_LONG).show();
    }
}
