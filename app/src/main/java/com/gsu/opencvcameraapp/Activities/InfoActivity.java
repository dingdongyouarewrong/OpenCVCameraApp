package com.gsu.opencvcameraapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.gsu.opencvcameraapp.Constants;
import com.gsu.opencvcameraapp.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link1));
                startActivity(browserIntent);
            }
            case R.id.button2: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link2));
                startActivity(browserIntent);
            }
            case R.id.button3: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link3));
                startActivity(browserIntent);
            }
            case R.id.button4: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link4));
                startActivity(browserIntent);
            }
            case R.id.button5: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link5));
                startActivity(browserIntent);
            }
            case R.id.button6: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link6));
                startActivity(browserIntent);
            }
            case R.id.button7: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link7));
                startActivity(browserIntent);
            }
            case R.id.button8: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link8));
                startActivity(browserIntent);
            }
            case R.id.button9: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link9));
                startActivity(browserIntent);
            }
            case R.id.button10: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link10));
                startActivity(browserIntent);
            }
            case R.id.button11: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link11));
                startActivity(browserIntent);
            }
            case R.id.button12: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link12));
                startActivity(browserIntent);
            }
            case R.id.button13: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link13));
                startActivity(browserIntent);
            }
            case R.id.button14: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link14));
                startActivity(browserIntent);
            }
            case R.id.button15: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link15));
                startActivity(browserIntent);
            }
            case R.id.button16: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link16));
                startActivity(browserIntent);
            }
            case R.id.button17: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link17));
                startActivity(browserIntent);
            }
            case R.id.button18: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link18));
                startActivity(browserIntent);
            }
            case R.id.button19: {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Constants.link19));
                startActivity(browserIntent);
            }
        }
    }
}
