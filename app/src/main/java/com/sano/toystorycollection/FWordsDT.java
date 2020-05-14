package com.sano.toystorycollection;

public class FWordsDT {
    private String dWord , tWord ;
    // private  int   colorCode ;
    private int  imageview_num  , audioTrack;

    public FWordsDT(String dWord, String tWord, int imageview_num , int audioTrack) {
        this.dWord = dWord;
        this.tWord = tWord;
        this.imageview_num = imageview_num;
        this.audioTrack = audioTrack;
        //  this.colorCode = colorCode;
    }

    public String getdWord() {
        return dWord;
    }

    public String gettWord() {
        return tWord;
    }

    public int getImageview_num() {
        return imageview_num;
    }

    public int getAudioTrack() {
        return audioTrack;
    }
    //    public int getColorCode() {
//        return colorCode;
//    }
}
