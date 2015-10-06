package com.easygo.rbcdev.easygo.BusinessEnd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easygo.rbcdev.easygo.R;

public class LeaveRequest extends Activity {

    private Activity mActivity = this;
    private TextView dateSet;
    private Button btnSubmit;
    private EditText editReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);
        initialize();
    }

    public void initialize(){

        editReason = (EditText)findViewById(R.id.editReason);
        dateSet = (TextView) findViewById(R.id.pickerDate);
        btnSubmit = (Button) findViewById(R.id.btnSubmitApplication);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mActivity, "Your Application has been sent to your manager.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });



    }
}
