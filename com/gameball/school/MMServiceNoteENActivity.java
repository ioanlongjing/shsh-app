package com.gameball.school;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMFavor;

public class MMServiceNoteENActivity extends Activity {
    @BindView(2131689655)
    TextView mTextViewServiceNote;
    @BindView(2131689613)
    TextView textview_title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0308R.layout.activity_service_note);
        ButterKnife.bind((Activity) this);
        this.mTextViewServiceNote.setText(MMFavor.getInstance().setting.getServiceNoteEN());
        this.textview_title.setText("Privacy Policy");
    }
}
