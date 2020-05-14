package com.sano.toystorycollection;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Relatives extends Fragment {
// ==============================================================================================//
// ==============================================================================================//


    MediaPlayer fmMediaPlayer;
    private  AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mOnCompletionListener =   new  MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

// ==============================================================================================//
// ==============================================================================================//


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// ==============================================================================================//
                    /* Main View */
            View Rview = inflater.inflate(R.layout.fragment_relatives, container, false);
// ==============================================================================================//


        ListView FM_listView = (ListView)  Rview.findViewById(R.id.familyM_list);

        final ArrayList<FWordsDT> FM_list = new ArrayList<>();

        FM_list.add(new FWordsDT("TOY STORY","1995",R.drawable.toystory1 ,R.raw.ninthone));
        FM_list.add(new FWordsDT("TOY STORY 2 ","1999",R.drawable.toystory2,R.raw.tenthone));
        FM_list.add(new FWordsDT("TOY STORY 3","2010",R.drawable.toystory3,R.raw.newfour));
        FM_list.add(new FWordsDT("TOY STORY 4","2019",R.drawable.toystory4,R.raw.newthrree));


        FwordsAdapter FMadapter = new FwordsAdapter(getContext(),R.layout.list_item_layout,FM_list,R.color.reorg);

        FM_listView.setAdapter(FMadapter);



        ///    ON CLick   ListVIew Item

        FM_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if(fmMediaPlayer != null){
                    fmMediaPlayer.release();}

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
                    fmMediaPlayer = MediaPlayer.create(getContext(),FM_list.get(position).getAudioTrack());
                    // ICON CHANGE ON GAIN FOCUS
                    view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_pause_white_24dp);
                    fmMediaPlayer.start();
                }

                //============================================================//
                //============================================================//


               /* fmMediaPlayer = MediaPlayer.create(getActivity().getBaseContext(),FM_list.get(position).getAudioTrack());
                fmMediaPlayer.start();*/
                fmMediaPlayer.setOnCompletionListener(mOnCompletionListener);

                // ======================================================
                ///  on COmplition change the icon back
                fmMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_play_arrow_white_24dp);
                    }
                });
                // ======================================================

            }
        });


// ==============================================================================================//
// ==============================================================================================//




        // Inflate the layout for this fragment
        return Rview;
    }
// ==============================================================================================//
// ==============================================================================================//

    private  void releaseMediaPlayer() {
        fmMediaPlayer.release();
    }
// ==============================================================================================//
// ==============================================================================================//





}
