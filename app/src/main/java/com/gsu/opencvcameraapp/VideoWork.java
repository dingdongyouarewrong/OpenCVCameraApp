package com.gsu.opencvcameraapp;

import android.os.Environment;
import android.util.Log;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoWriter;

import java.io.File;
import java.util.Calendar;

import static java.lang.String.valueOf;

public class VideoWork {

    VideoWriter writer;

    public VideoWork(Size size) {
        startRecord(size);

    }

    private void startRecord(Size size) {
        //create writer
            writer = new VideoWriter();

            //create directory for the video
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    + File.separator+"SignsRecorder");
            directory.mkdirs();

            //Create name of video
            String filename = createNameOfVideo();

            //create videofile
            File video = new File(directory,filename+".avi" );

            //open videofile and start record
            writer.open(video.getAbsolutePath(), VideoWriter.fourcc('M', 'J', 'P', 'G'), 10.0D, size);
            Log.e("WRITER is opened?", valueOf(writer.isOpened()));

    }

    public void stopRecording() {
        //stop and save
        writer.release();
    }

    public void writeFrame(Mat inputFrame) {
        //adding frame by frame to the video
        writer.write(inputFrame);
    }

    private String createNameOfVideo() {

        String filename = valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))+":"+
                valueOf(Calendar.getInstance().get(Calendar.MINUTE))
                + "_"+valueOf(Calendar.getInstance().get(Calendar.DATE))
                +"."+valueOf(Calendar.getInstance().get(Calendar.MONTH)+1)
                +"."+valueOf(Calendar.getInstance().get(Calendar.YEAR));

        return filename;
    }

}
