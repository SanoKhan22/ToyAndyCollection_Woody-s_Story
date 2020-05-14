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


/**
 * A simple {@link Fragment} subclass.
 */
public class Numbers extends Fragment {
//            ==========================================================================



    MediaPlayer numMediaPlayer ;
    // Creating AutioManager FOr managing audioFocus
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mOnCompletionListener =   new  MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

            releaseMediaPlayer();
        }
    };

    ///  On Audio Focus CHange Listner


/*    AudioManager.OnAudioFocusChangeListener mOnAudioFOcusChangeListner =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Permanent loss of audio focus
                        // Pause playback immediately

                        releaseMediaPlayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        numMediaPlayer.pause();
                        numMediaPlayer.seekTo(0);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        numMediaPlayer.start();
                    }
                }
            };*/







//    ============================================================================================ ///


    public Numbers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        ===================================================================================
//                                                MAINviEW
        View view = inflater.inflate(R.layout.fragment_numbers, container, false);
         //   Layout layout ;
//                [][][][][[][][][][][][][][][][][][][][][][][][][][][]]



        // Geting Service through audioManage
        mAudioManager = (AudioManager) getActivity().getSystemService(getContext().AUDIO_SERVICE);

        final ListView numberListView = (ListView) view.findViewById(R.id.Numbers_list);
        final ArrayList<FWordsDT> numberList = new ArrayList<>();

        numberList.add(new FWordsDT("Sheriff Woody" , "Tom Hanks" , R.drawable.woody  , R.raw.newtwo));
        numberList.add(new FWordsDT("Buzz Lightyear" , "Tim Allen" , R.drawable.buzzer , R.raw.sherifican));
        numberList.add(new FWordsDT("Jessie" , "Joan Cusack" , R.drawable.jessie, R.raw.newfifht));
        numberList.add(new FWordsDT("Potato Head" , "John Ratzenber " , R.drawable.patato , R.raw.newsix));
        numberList.add(new FWordsDT("Bo Peep" , "Annie Potts" , R.drawable.bopeep, R.raw.neweigh));
        numberList.add(new FWordsDT("Slinky Dog" , "Jim Varney" , R.drawable.slinky , R.raw.newning));
        numberList.add(new FWordsDT("Hamm" , "John Ratzenber" , R.drawable.hamm , R.raw.newten));
        numberList.add(new FWordsDT("Rex" , "Wallace Shawn" , R.drawable.rex , R.raw.newfort));
        numberList.add(new FWordsDT("Sarge" , "R. Lee Ermey" , R.drawable.sarge, R.raw.newthirteen ));
        numberList.add(new FWordsDT("Bullseye" , "Frank Welker" , R.drawable.bullseye, R.raw.newelev ));




        final FwordsAdapter numberListAdapter = new FwordsAdapter(getContext(),R.layout.list_item_layout , numberList , R.color.rdkin );
        numberListView.setAdapter(numberListAdapter);



        numberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                if(numMediaPlayer != null){

                    numMediaPlayer.release();
                }

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

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    numMediaPlayer = MediaPlayer.create(getContext(),numberList.get(position).getAudioTrack());
                    view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_pause_white_24dp);
                    numMediaPlayer.start();
                }

                numMediaPlayer.setOnCompletionListener(mOnCompletionListener);

                /////




                ///  on COmplition change the icon back
                numMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        view.findViewById(R.id.playIcon).setBackgroundResource(R.drawable.round_play_arrow_white_24dp);
                    }
                });
// ======================================================

            }
        });









        // ==================================================================================//


        // Inflate the layout for this fragment
        return view;
    }

    //   ==============================  //

    private  void releaseMediaPlayer(){
        numMediaPlayer.release();
       // mAudioManager.abandonAudioFocus(mOnAudioFOcusChangeListner);
    }






//    =========================================




}
