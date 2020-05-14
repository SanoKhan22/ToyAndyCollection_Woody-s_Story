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


public class Phrases extends Fragment {

    // =======================================================================//
    // =======================================================================//

    MediaPlayer pMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener =   new  MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

        //  AUdioManager Initilation

    private AudioManager  mAudioManager;

    // =======================================================================//
    // =======================================================================//



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // =======================================================================//
                    // main View to returen
        View Pview = inflater.inflate(R.layout.fragment_phrases, container, false);


        // Geting Service through audioManage
        mAudioManager = (AudioManager) getActivity().getSystemService(getContext().AUDIO_SERVICE);

        // =======================================================================//


        ListView phrasesListView = (ListView) Pview.findViewById(R.id.PhrasesListView);

        final ArrayList<FWordsDT> P_list = new ArrayList<>();

        P_list.add(new FWordsDT("Be carefull" , "First" ,-1,R.raw.becarefull));
        P_list.add(new FWordsDT("how-dare-you!" , "Second" ,-1,R.raw.howdare));
        P_list.add(new FWordsDT("i-know-andy's" , "Third" ,-1,R.raw.iknonandy));
        P_list.add(new FWordsDT("no!-no-no" , "Forth" ,-1,R.raw.nono));
        P_list.add(new FWordsDT("oh-all-this-time" , "Fifth" ,-1,R.raw.ohallthe));
        P_list.add(new FWordsDT("that-wasn't-flying!" , "Sixth" ,-1,R.raw.thatwannot));
        P_list.add(new FWordsDT("wait-a-minute" , "Seventh" ,-1,R.raw.waitaminute));
        P_list.add(new FWordsDT("YOU'RE A TOY " , "Eight" ,-1,R.raw.youarea));
        P_list.add(new FWordsDT("Youre mocking me" , "Ninth" ,-1,R.raw.youremocking));
        P_list.add(new FWordsDT("He Has Been" , "Tenth" ,-1,R.raw.hehasbeen));

        FwordsAdapter Padapter = new FwordsAdapter(getContext(),R.layout.list_item_layout,P_list,R.color.dkule);

        phrasesListView.setAdapter(Padapter);

        phrasesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if(pMediaPlayer != null)
                    pMediaPlayer.release();
//              ====================================
//              /////////////////////////////////////

                //============================================================//
//                            Audio get Focus  and icon change
                //============================================================//

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
                    pMediaPlayer = MediaPlayer.create(getContext(),P_list.get(position).getAudioTrack());
                    //                              // ICON CHANGE ON GAIN FOCUS
                    view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_pause_white_24dp);
                    pMediaPlayer.start();
                }

                //============================================================//
                //============================================================//


                ////////////////////////////////////////////////////////
//                =======================================================
//                pMediaPlayer = MediaPlayer.create(getContext(),P_list.get(position).getAudioTrack());
//                pMediaPlayer.start();
                pMediaPlayer.setOnCompletionListener(mOnCompletionListener);

                // ======================================================
                ///  on COmplition change the icon back
                pMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_play_arrow_white_24dp);
                    }
                });
                // ======================================================

            }
        });



        // =======================================================================//
        // =======================================================================//

        // Inflate the layout for this fragment
        return Pview;

    }


    // =======================================================================//
    // =======================================================================//
            //  End the mediaPlayer Resources for memory

    private  void releaseMediaPlayer(){
        pMediaPlayer.release();
    }


    // =======================================================================//
    // =======================================================================//

}
