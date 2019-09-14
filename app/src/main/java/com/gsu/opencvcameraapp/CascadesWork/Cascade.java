package com.gsu.opencvcameraapp.CascadesWork;

import android.content.Context;
import android.util.Log;

import com.gsu.opencvcameraapp.R;

import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Cascade {

    private Context context;

    public CascadeClassifier LBPSpeedlimit100Classifier, LBPspeedlimit60Classifier,
            LBPgiveWaySignClassifier, mainRoadSignClassifier, LBPmainRoadClassifier, LBPnoWayClassifier, stopSignClassifier;

    public Cascade(Context context) {
        this.context = context;
        //init();
        loadCascades();
    }

    public void init() {
        try {
            //copy cascades to storage
            File LBPspeedlimit100CascadeFile = copySpeedlimit100SignCascadeLBP();
            //File LBPspeedlimit60CascadeFile = copySpeedlimit60SignCascadeLBP();

            File stopCascadeFile = copyStopCascadeLBP();
            File LBPgiveWayCascadeFile = copyGiveWayCascadeLBP();
            File LBPmainRoadCascadeFile = copyMainRoadCascadeLBP();
            File LBPnoWayCascadeFile = copyNoWayCascadeLBP();

            //Load the classifier from cascade
            LBPSpeedlimit100Classifier = new CascadeClassifier(LBPspeedlimit100CascadeFile.getAbsolutePath());
            stopSignClassifier = new CascadeClassifier(stopCascadeFile.getAbsolutePath());
            LBPgiveWaySignClassifier = new CascadeClassifier(LBPgiveWayCascadeFile.getAbsolutePath());
            LBPmainRoadClassifier = new CascadeClassifier(LBPmainRoadCascadeFile.getAbsolutePath());
            LBPnoWayClassifier = new CascadeClassifier(LBPnoWayCascadeFile.getAbsolutePath());
            //LBPspeedlimit60Classifier = new CascadeClassifier(LBPspeedlimit60CascadeFile.getAbsolutePath());

        } catch (IOException IOException) {
            Log.e("EXCEPTION IN CASCADE", "IOException");
        }

    }

    private void loadCascades() {
        LBPSpeedlimit100Classifier = new CascadeClassifier("//sdcard/"+context.getPackageName()+"/"+"app_cascade/"+"speedlimit100_cascade_lbp.xml");
        LBPmainRoadClassifier = new CascadeClassifier("//data/data/"+context.getPackageName()+"/"+"app_cascade/"+"main_road_cascade_lbp.xml");
        LBPgiveWaySignClassifier = new CascadeClassifier("//data/data/"+context.getPackageName()+"/"+"app_cascade/"+"give_way_cascade_lbp.xml");
        LBPnoWayClassifier = new CascadeClassifier("//data/data/"+context.getPackageName()+"/"+"app_cascade/"+"no_way_cascade_lbp.xml");
        stopSignClassifier = new CascadeClassifier("//data/data/"+context.getPackageName()+"/"+"app_cascade/"+"stop_sign.xml");

    }

    private File copySpeedlimit100SignCascadeLBP() throws IOException {
        //copy stop_sign_cascade
        InputStream is = context.getResources().openRawResource(R.raw.speedlimit100_cascade_lbp_trash);
        File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
        File speedlimit100SignCascadeFile = new File(cascadeDir, "speedlimit100_cascade_lbp.xml");
        FileOutputStream os = new FileOutputStream(speedlimit100SignCascadeFile);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        is.close();
        os.close();

        return speedlimit100SignCascadeFile;
    }

    private File copySpeedlimit60SignCascadeLBP() throws IOException {
        //copy stop_sign_cascade
        InputStream is = context.getResources().openRawResource(R.raw.speedlimit60_cascade_lbp);
        File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
        File speedlimit60SignCascadeFile = new File(cascadeDir, "speedlimit60_cascade_lbp.xml.xml");
        FileOutputStream os = new FileOutputStream(speedlimit60SignCascadeFile);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        is.close();
        os.close();

        return speedlimit60SignCascadeFile;
    }

    private File copyNoWayCascadeLBP() throws IOException {
        //copy no_way_cascade
        InputStream is = context.getResources().openRawResource(R.raw.lbp_kirpich);
        File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
        File noWayCascadeFile = new File(cascadeDir, "no_way_cascade_lbp.xml");
        FileOutputStream os = new FileOutputStream(noWayCascadeFile);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        is.close();
        os.close();

        return noWayCascadeFile;
    }

    private File copyGiveWayCascadeLBP() throws IOException {
        //copy give_way_cascade
        InputStream is = context.getResources().openRawResource(R.raw.ustupi_dorogu_lbp);
        File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
        File giveWayCascadeFile = new File(cascadeDir, "give_way_cascade_lbp.xml");
        FileOutputStream os = new FileOutputStream(giveWayCascadeFile);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        is.close();
        os.close();

        return giveWayCascadeFile;
    }

    private File copyMainRoadCascadeLBP() throws IOException {

        //copy give_way_cascade
        InputStream is = context.getResources().openRawResource(R.raw.main_road_lbp);
        File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
        File giveWayCascadeFile = new File(cascadeDir, "main_road_cascade_lbp.xml");
        FileOutputStream os = new FileOutputStream(giveWayCascadeFile);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        is.close();
        os.close();

        return giveWayCascadeFile;

    }


    private File copyStopCascadeLBP() throws IOException {
        //copy stop cascade
        InputStream is = context.getResources().openRawResource(R.raw.stop_sign);
        File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
        File stopSignFile = new File(cascadeDir, "stop_sign.xml");
        FileOutputStream os = new FileOutputStream(stopSignFile);

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        is.close();
        os.close();

        return stopSignFile;
    }

}
