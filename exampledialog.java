package dopemz.dayssince;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class exampledialog extends AppCompatDialogFragment {

    private TextView textprac;
    private Button buttonprac;
    private String title;
    private String number;

    private exdialoglistener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        //title = prac[1].getnum;

        builder.setView(view)
                .setTitle("asdf")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        textprac = view.findViewById(R.id.textViewprac);
        buttonprac = view.findViewById(R.id.buttonprac);
        buttonprac.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "yoo";
                listener.applytext(number);
            }
        });




        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (exdialoglistener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement exdialog");
        }

    }

    public interface exdialoglistener{
        void applytext(String num);
    }
}
