package com.haojiu.handguide;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements View.OnClickListener {

    private RelativeLayout mask;
    private ImageView imageView_mask;
    private Button i_know_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
        setContentView(R.layout.activity_main);
        mask = (RelativeLayout)findViewById(R.id.mask);
        imageView_mask = (ImageView)findViewById(R.id.imageView_mask);
        i_know_btn = (Button)findViewById(R.id.i_know_btn);
        i_know_btn.setOnClickListener(this);
        i_know_btn.setOnClickListener(this);
        i_know_btn.setOnClickListener(this);
        setMask();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.i_know_btn:
                mask.setVisibility(View.GONE);
                getApplicationContext().getSharedPreferences("Setting", Context.MODE_PRIVATE).edit().putBoolean("read_share", true).commit();
                break;
            default:
                break;
        }
    }

    /**
     * 设置第一次进入时的蒙版
     */
    private void setMask() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
                "Setting", Context.MODE_PRIVATE);
        boolean isread =  sharedPreferences.getBoolean("read_share", false);
        if(!isread){
            // 调整顶部背景图片的大小，适应不同分辨率的屏幕
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;
            int height = (int) ((float) width / 48 *31);
//            imageView_mask.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
            mask.setVisibility(View.VISIBLE);
        }else{
            mask.setVisibility(View.GONE);
        }
    }
}
