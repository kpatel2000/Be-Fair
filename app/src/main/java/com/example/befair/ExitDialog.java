package com.example.befair;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


public class ExitDialog extends Dialog implements
        android.view.View.OnClickListener{

    private static OnOkClickListener okClickListener;
    public TextView cancel, ok;

    public ExitDialog(Context context, OnOkClickListener onOkClickListener) {
        super(context);
        okClickListener = onOkClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_quit_dialog);
        cancel = (TextView) findViewById(R.id.quit_cancel);
        ok = (TextView) findViewById(R.id.quit_ok);
        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.quit_ok){
            okClickListener.onOkCLick();
        }else if (v.getId() == R.id.quit_cancel){
            dismiss();
        }
        dismiss();
    }
}
interface OnOkClickListener{
    void onOkCLick();
}


