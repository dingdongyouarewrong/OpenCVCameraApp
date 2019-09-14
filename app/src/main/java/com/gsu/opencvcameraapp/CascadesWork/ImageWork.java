package com.gsu.opencvcameraapp.CascadesWork;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.gsu.opencvcameraapp.Activities.MainActivity;
import com.gsu.opencvcameraapp.Constants;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageWork {
    private Context context;

    private Cascade cascade;
    private Mat grayscaleImage;
    private int absoluteSignSize;
    public int number;
    private ImageView notificationSignImage;
    public ImageWork(Context context) {
        cascade = new Cascade(context);
        this.context = context;

    }

    public Mat modifyFrame(Mat inputFrame, Mat grayscaleImage, int absoluteSignSize, ImageView notificationSignImage) {

        this.grayscaleImage = grayscaleImage;
        this.absoluteSignSize = absoluteSignSize;
        this.notificationSignImage = notificationSignImage;

        //highlight about the main road sign
        inputFrame = mainRoadSignCheckLBP(inputFrame);
        //highlight about the no way sign
        inputFrame = noWaySignCheckLBP(inputFrame);
        //highlight about the give way sign
        inputFrame = giveWaySignCheckLBP(inputFrame);
        //highlight about the speedlimit 100 sign
        //inputFrame = speedlimit100SignCheckLBP(inputFrame);
        //highlight about the stop sign
        inputFrame = stopSignCheck(inputFrame);

        //highlight about the speedlimit 60 sign
        //inputFrame = speedlimit60SignCheckLBP(inputFrame);


        return inputFrame;
    }

    private Mat mainRoadSignCheckLBP(Mat inputFrame) {

        MatOfRect signs = new MatOfRect();

        if (cascade.LBPmainRoadClassifier != null) {
            cascade.LBPmainRoadClassifier.detectMultiScale(grayscaleImage, signs, 1.1, 2, 2,
                    new Size(absoluteSignSize, absoluteSignSize), new Size());
        }

        Rect[] signsArray = signs.toArray();
        for (Rect i:signsArray) {
            Imgproc.rectangle(inputFrame, i.tl(),
                    i.br(),
                    new Scalar(0, 255, 255, 255), 3);

                    callNotify(Constants.main_road_number);
        }
        return inputFrame;
    }

    private Mat noWaySignCheckLBP(Mat inputFrame) {

        MatOfRect signs = new MatOfRect();

        if (cascade.LBPnoWayClassifier != null) {
            cascade.LBPnoWayClassifier.detectMultiScale(grayscaleImage, signs, 1.1, 2, 2,
                    new Size(absoluteSignSize, absoluteSignSize), new Size());
        }

        Rect[] signsArray = signs.toArray();
        for (Rect i:signsArray) {
            Imgproc.rectangle(inputFrame, i.tl(),
                    i.br(),
                    new Scalar(255, 0, 0, 255), 3);
            callNotify(Constants.no_way_number);
        }
        return inputFrame;
    }

    private Mat speedlimit100SignCheckLBP(Mat inputFrame) {

        MatOfRect signs = new MatOfRect();

        if (cascade.LBPSpeedlimit100Classifier != null) {
            cascade.LBPSpeedlimit100Classifier.detectMultiScale(grayscaleImage, signs, 1.1, 2, 2,
                    new Size(absoluteSignSize, absoluteSignSize), new Size());
        }

        Rect[] signsArray = signs.toArray();
        for (Rect i:signsArray) {
            Imgproc.rectangle(inputFrame, i.tl(),
                    i.br(),
                    new Scalar(0, 0, 255, 255), 3);
            callNotify(Constants.speed_limit100);
        }
        return inputFrame;
    }

    private Mat speedlimit60SignCheckLBP(Mat inputFrame) {

        MatOfRect signs = new MatOfRect();

        if (cascade.LBPSpeedlimit100Classifier != null) {
            cascade.LBPSpeedlimit100Classifier.detectMultiScale(grayscaleImage, signs, 1.1, 2, 2,
                    new Size(absoluteSignSize, absoluteSignSize), new Size());
        }

        Rect[] signsArray = signs.toArray();
        for (Rect i:signsArray) {
            Imgproc.rectangle(inputFrame, i.tl(),
                    i.br(),
                    new Scalar(255, 0, 255, 255), 3);
            callNotify(Constants.speed_limit60);
        }
        return inputFrame;
    }

    private Mat giveWaySignCheckLBP(Mat inputFrame) {

        MatOfRect signs = new MatOfRect();

        if (cascade.LBPgiveWaySignClassifier != null) {
            cascade.LBPgiveWaySignClassifier.detectMultiScale(grayscaleImage, signs, 1.1, 2, 2,
                    new Size(absoluteSignSize, absoluteSignSize), new Size());
        }
        Rect[] signsArray = signs.toArray();
        for (Rect i:signsArray) {
            Imgproc.rectangle(inputFrame, i.tl(),
                    i.br(),
                    new Scalar(0,255 , 0, 255), 3);
            callNotify(Constants.give_way_number);
        }
        return inputFrame;
    }

    private Mat stopSignCheck(Mat inputFrame) {

        MatOfRect signs = new MatOfRect();

        if (cascade.stopSignClassifier != null) {
            cascade.stopSignClassifier.detectMultiScale(grayscaleImage, signs, 1.1, 2, 2,
                    new Size(absoluteSignSize, absoluteSignSize), new Size());
        }
        Rect[] signsArray = signs.toArray();
        for (Rect i:signsArray) {
            Imgproc.rectangle(inputFrame, i.tl(),
                    i.br(),
                    new Scalar(255,20, 100, 255), 3);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    image.setVisibility(View.VISIBLE);
//                    image.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            image.setVisibility(View.GONE);
//                        }
//                    },2000);
//                }
//            });
        }
        return inputFrame;
    }




    public void callNotify(final int number) {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                MainActivity mainActivity = new MainActivity();
                mainActivity.notifySign(number, context, notificationSignImage);

            }
        });
    }



}
