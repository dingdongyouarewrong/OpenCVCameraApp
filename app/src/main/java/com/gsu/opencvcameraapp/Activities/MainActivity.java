package com.gsu.opencvcameraapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gsu.opencvcameraapp.CascadesWork.Cascade;
import com.gsu.opencvcameraapp.CascadesWork.ImageWork;
import com.gsu.opencvcameraapp.Constants;
import com.gsu.opencvcameraapp.R;
import com.gsu.opencvcameraapp.VideoWork;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoWriter;




public class MainActivity extends AppCompatActivity
        implements CameraBridgeViewBase.CvCameraViewListener {

    private CameraBridgeViewBase mOpenCvCameraView;

    private ImageWork imageWork;
    private int absoluteSignSize;
    private Mat grayscaleImage;
    protected Size size;
    VideoWork videoWork;
    private ImageView notificationSignImage;


    private boolean onAir = false;
    //start record if onAir false, and pause if true
    private ImageView startPauseRecordButton;

    private SharedPreferences prefs;

    private int cameraIndex;
    TextView image;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            super.onManagerConnected(status);

            if (status == LoaderCallbackInterface
                    .SUCCESS) {
                initializeOpenCVDependencies();
            } else {
                super.onManagerConnected(status);
            }
        }
    };
    private void initializeOpenCVDependencies() {
        checkFirstRun();
        imageWork = new ImageWork(getApplicationContext());

        mOpenCvCameraView.enableView();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OpenCVLoader.initDebug();

        //window flags
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);


        image = findViewById(R.id.image);
        startPauseRecordButton = findViewById(R.id.recordingPauseStartButton);
        notificationSignImage = findViewById(R.id.signNotification);


        //opencv camera view init
        mOpenCvCameraView = findViewById(R.id.cameraView);
        mOpenCvCameraView.setVisibility(View.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        if (mOpenCvCameraView!=null) {
            mOpenCvCameraView.disableView();
        }
    }
    @Override
    public void onResume() {

        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0,
                this, mLoaderCallback);


    }
    @Override
    public void onDestroy() {

        super.onDestroy();
        if (mOpenCvCameraView!=null) {
            mOpenCvCameraView.disableView();
        }
    }



    @Override
    public void onCameraViewStarted(int width, int height) {
        grayscaleImage = new Mat(height,width, CvType.CV_8UC4);
        absoluteSignSize = (int)(height*0.1);
        size = new Size(width, height);
    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(Mat inputFrame) {

        // Create a grayscale image
        Imgproc.cvtColor(inputFrame, grayscaleImage, Imgproc.COLOR_RGBA2RGB);

        inputFrame = imageWork.modifyFrame(inputFrame, grayscaleImage, absoluteSignSize, notificationSignImage);

        if (onAir) {
            videoWork.writeFrame(inputFrame);
        }

        return inputFrame;
    }



    public void startAnimation(final ImageView notificationSignImage, Context context) {
        notificationSignImage.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.moving_animation);
        notificationSignImage.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                notificationSignImage.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    public void swapCamera(View view) {
        cameraIndex = cameraIndex^1;
        mOpenCvCameraView.disableView();
        mOpenCvCameraView.setCameraIndex(cameraIndex);
        mOpenCvCameraView.enableView();
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.callButton: {
                //start phone app
                startPhone();
                break;

            }

            case R.id.settingsButton: {
                //start settings activity
                Intent intentSettings = new Intent(this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            }

            case R.id.infoButton: {
                //start info activity
                Intent infoIntent = new Intent(this, InfoActivity.class);
                startActivity(infoIntent);
                break;

            }

            case R.id.recordingPauseStartButton: {
                //if onAir(recording now) change icon to pause icon and toggle onAir variable
                if (!onAir) {
                    Toast.makeText(this, "запись началась", Toast.LENGTH_SHORT).show();
                    startPauseRecordButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop));
                    onAir = true;
                    record();
                } else {
                    Toast.makeText(this, "запись остановлена и сохранена", Toast.LENGTH_SHORT).show();
                    startPauseRecordButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_recording));
                    onAir = false;
                    videoWork.stopRecording();
                }
                break;

            }


        }
    }

    private void startPhone() {
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, null);
        Intent chooser = Intent.createChooser(phoneIntent, "wd");
        startActivity(chooser);
    }

    public void notifySign(int signNumber, Context contxt, ImageView notificationSignImage) {
        playSound(contxt);
        switch (signNumber) {
            case Constants.give_way_number: {
                notificationSignImage.setImageDrawable(contxt.getResources().getDrawable(R.drawable.ic_give_way_sign));
                startAnimation(notificationSignImage, contxt);
                break;
            }
            case Constants.main_road_number: {
                notificationSignImage.setImageDrawable(contxt.getResources().getDrawable(R.drawable.ic_main_road));
                startAnimation(notificationSignImage, contxt);

                break;

            }
            case Constants.no_way_number: {
                notificationSignImage.setImageDrawable(contxt.getResources().getDrawable(R.drawable.ic_no_way));
                startAnimation(notificationSignImage, contxt);
                break;

            }
            case Constants.stop_number: {
                notificationSignImage.setImageDrawable(contxt.getResources().getDrawable(R.drawable.ic_stop_sign));
                startAnimation(notificationSignImage, contxt);
                break;
            }
            case Constants.speed_limit100: {
                notificationSignImage.setImageDrawable(contxt.getResources().getDrawable(R.drawable.ic_speedlimit100));
                startAnimation(notificationSignImage, contxt);
                break;
            }
            case Constants.speed_limit60: {
                this.notificationSignImage.setImageDrawable(contxt.getResources().getDrawable(R.drawable.ic_launcher_background));
                startAnimation(notificationSignImage, contxt);
            }
        }
    }



    private void checkFirstRun() { //if this is first run - "firstrun" cant find value and became default(true)
        prefs = getSharedPreferences("com.kakatontest.opencvcameraapp",MODE_PRIVATE);

        if (prefs.getBoolean("firstrun", true)) {
            Cascade cascade = new Cascade(getApplicationContext());
            cascade.init();
            prefs.edit().putBoolean("firstrun", false).apply();
        }

    }

    private void record() {
        videoWork = new VideoWork(size);
    }

    protected void playSound(Context contxt) {
        MediaPlayer sound = MediaPlayer.create(contxt, R.raw.soundeffect);
        sound.start();
    }

}
