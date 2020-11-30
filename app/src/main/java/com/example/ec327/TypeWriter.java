package com.example.ec327;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class TypeWriter extends androidx.appcompat.widget.AppCompatTextView {
    private CharSequence mtext;
    private int mindex;
    private long delay=150; //in miliseconds
    public TypeWriter(Context context){
        super(context);
    }
    public TypeWriter(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    private Handler mhandler=new Handler();
    private Runnable characteradder=new Runnable() {
        @Override
        public void run() {
            setText(mtext.subSequence(0,mindex++));
            if(mindex<=mtext.length()){
                mhandler.postDelayed(characteradder,delay);
            }
        }
    };
    public void animatedText(CharSequence txt){
        mtext=txt;
        mindex=0;
        setText("");
        mhandler.removeCallbacks(characteradder);
        mhandler.postDelayed(characteradder,delay);
    }
    public void setCharacterDelay(long m){
        delay=m;
    }

}