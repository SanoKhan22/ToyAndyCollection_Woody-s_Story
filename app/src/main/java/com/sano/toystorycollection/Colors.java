package com.sano.toystorycollection;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;



public class Colors extends Fragment {
        // Initilization of Varibales

         MediaPlayer colorsMediaPlayer;
         private AudioManager  mAudioManager;

            /// ACTION BAR




        private MediaPlayer.OnCompletionListener mOnCompletionListener =   new  MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
        };


    public Colors() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ///////////////=======================///////////////////




//  Negative Character

        //=============================================================//
        View view = inflater.inflate(R.layout.fragment_colors, container, false);



        final ListView colorListView =  view.findViewById(R.id.colors_ListView);

        final ArrayList<FWordsDT> colorsList = new ArrayList<>();
        colorsList.add(new FWordsDT("Lots-o'-Huggin'" , "Ned Beatty" , R.drawable.bear, R.raw.firstone));
        colorsList.add(new FWordsDT("Benson" , "Steve Purcell" , R.drawable.benson, R.raw.secondone));
        colorsList.add(new FWordsDT("Evil E. Zurg" , "Andrew Stanton" ,R.drawable.zurg, R.raw.thirdone));
        colorsList.add(new FWordsDT("Stinky Pete" , "Stephen Stanton" , R.drawable.pete, R.raw.fouthone));
        colorsList.add(new FWordsDT("Ken" , "Michael Keaton" ,R.drawable.ken, R.raw.fifthone));
        colorsList.add(new FWordsDT("Big Baby" , "Woody Smith" ,R.drawable.bb, R.raw.sixthone));
        colorsList.add(new FWordsDT("Al McWhiggin" , " Wayne Knight" ,R.drawable.sales, R.raw.seventhone));


        FwordsAdapter colorListAdapter = new FwordsAdapter( getContext(),R.layout.list_item_layout,colorsList,R.color.grue);

        colorListView.setAdapter(colorListAdapter);

        colorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                if(colorsMediaPlayer != null){
                    colorsMediaPlayer.release();}
                //============================================================//
//                            Audio get Focus  and icon change
                //============================================================//

                // Geting Service through audioManage
                mAudioManager = (AudioManager) getActivity().getSystemService(getContext().AUDIO_SERVICE);

                //====-----------------------------------------------------------======//


                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(new AudioManager.OnAudioFocusChangeListener() {
                                                                 @Override
                                                                 public void onAudioFocusChange(int focusChange) {
                                                                     ///   AUdio Focus States
                                                                     if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                                                                         // Permanent loss of audio focus
                                                                         // Pause playback immediately
                                                                         view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_play_arrow_white_24dp);


                                                                         //   releaseMediaPlayer();
                                                                     }
                                                                     else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                                                                         // Pause playback
                                                                         //  numMediaPlayer.pause();
                                                                         view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_play_arrow_white_24dp);


                                                                         //    numMediaPlayer.seekTo(0);
                                                                     }
                                                                     else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                                                                         // Your app has been granted audio focus again
                                                                         // Raise volume to normal, restart playback if necessary
                                                                         ///    numMediaPlayer = MediaPlayer.create(getContext(),numberList.get(position).getAudioTrack());

                                                                         //     numMediaPlayer.start();
                                                                     }
                                                                 }
                                                             },
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //  ================================================================ //
//                            On Result
                //============================================================//

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    colorsMediaPlayer = MediaPlayer.create(getContext(),colorsList.get(position).getAudioTrack());
                            // ICON CHANGE ON GAIN FOCUS
                    view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_pause_white_24dp);
                    colorsMediaPlayer.start();
                }

                //============================================================//
                //============================================================//

                //colorsMediaPlayer = MediaPlayer.create(Colors.this,colorsList.get(position).getAudioTrack());
         //       colorsMediaPlayer = MediaPlayer.create(getContext(),colorsList.get(position).getAudioTrack());
                        // When click and media player is created change the ICOn
             //   view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_pause_white_24dp);
            ////    colorsMediaPlayer.start();
                colorsMediaPlayer.setOnCompletionListener(mOnCompletionListener);

                // ======================================================
                ///  on COmplition change the icon back
                    colorsMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_play_arrow_white_24dp);
                    }
                });
                // ======================================================

            }
        });




        //============================================================//
        return view;
    }
    private  void releaseMediaPlayer(){
        colorsMediaPlayer.release();
    }
}
