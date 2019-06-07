package dopemz.dayssince;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class pracClass{

    private TextView myname;
    private int mynum;
    private Button btn;
    private LinearLayout mylayout;
    //private int mylayout;

    public pracClass(){
        mynum = 100;
        myname = null;

    }

    public pracClass(int nums, LinearLayout layout){
        mynum = nums;
        //btn = mybtn;
        mylayout = layout;
    }

    public int getnum(){
        return mynum;
    }

    public LinearLayout getlayout(){
        return mylayout;
    }

    /*public int getlayout(){
        return mylayout;
    }*/

    public void openlayout(Button button, Button button2){
        mylayout.setMinimumHeight(200);
        btn = button;
        mylayout.addView(button);
        mylayout.addView(button2);
    }
    public void closelayout(){
        mylayout.removeAllViews();
        mylayout.setMinimumHeight(0);
    }


}
