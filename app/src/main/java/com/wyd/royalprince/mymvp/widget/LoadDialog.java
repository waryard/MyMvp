package com.wyd.royalprince.mymvp.widget;


import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.wyd.royalprince.mymvp.R;


public class LoadDialog {

    private Activity context;
    private TextView textView;
    private Dialog dialog;

    public LoadDialog(Activity context) {
        this.context = context;

        View view = View.inflate(context, R.layout.load_dialog, null);
        textView = (TextView) view.findViewById(R.id.tv_load);

        dialog = new Dialog(context, R.style.TransDialog);
        dialog.setContentView(view);
        dialog.setCancelable(false);
    }

    public void setCancelable(boolean flag) {
        dialog.setCancelable(flag);
    }

    public void setMessage(CharSequence message) {
        textView.setText(TextUtils.isEmpty(message) ? "" : message);
    }

    public void show() {
        if (!context.isFinishing())
            dialog.show();
    }

    public void dismiss() {
        if (!context.isFinishing())
            dialog.dismiss();
    }
}
