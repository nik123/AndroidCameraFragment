package com.example.camerafragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class CameraActivity extends Activity {
    private static final String CAMERA_FRAGMENT = "camera_fragment";
    private CameraFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_camera);

        FragmentManager fm = getFragmentManager();
        fragment = (CameraFragment) fm.findFragmentByTag(CAMERA_FRAGMENT);
        if (fragment == null) {
            fragment = new CameraFragment();
            fragment.setRetainInstance(true);
            fm.beginTransaction().add(R.id.fragment_container, fragment, CAMERA_FRAGMENT).commit();
        }

        final ImageButton b = (ImageButton)findViewById(R.id.flash_button);
        int resId = fragment.isFlashOn() ? R.drawable.light_on : R.drawable.light;
        b.setImageResource(resId);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment.isFlashOn()) {
                    fragment.flashTurnOff();
                } else {
                    fragment.flashTurnOn();
                }
                int resId = fragment.isFlashOn() ? R.drawable.light_on : R.drawable.light;
                b.setImageResource(resId);
            }
        });
    }
}