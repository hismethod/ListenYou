package io.github.hismethod.listenyou.Recorder;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class CallRecorder extends MediaRecorder{

    private String OUTFILE = null;
    private int SOURCE = AudioSource.VOICE_CALL;
    private int FORMAT = OutputFormat.THREE_GPP;
    private int ENCODING = AudioEncoder.AMR_NB;
    public static class Builder{
        private int source = AudioSource.VOICE_CALL;
        private int format = OutputFormat.THREE_GPP;
        private int encoding = AudioEncoder.AMR_NB;
        private String filePath = null;

        public Builder source(int source){
            this.source = source;
            return this;
        }

        public Builder format(int format){
            this.format = format;
            return this;
        }

        public Builder encoding(int encoding){
            this.encoding = encoding;
            return this;
        }

        public Builder file(String filePath){
            this.filePath = filePath;
            return this;
        }

        public CallRecorder build(){
            return new CallRecorder(this);
        }
    }
    private CallRecorder(Builder builder){
        this.SOURCE = builder.source;
        this.FORMAT = builder.format;
        this.ENCODING = builder.encoding;
        this.OUTFILE = builder.filePath;
    }

    public void init(){
        this.setAudioSource(SOURCE);
        this.setOutputFormat(FORMAT);
        this.setAudioEncoder(ENCODING);
        this.setOutputFile(OUTFILE);
        try {
            this.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "init: " + "오류발생");
        }
    }

    public void record(){
        super.start();
    }

    public void stopForReuse(){
        super.stop();
    }
}
