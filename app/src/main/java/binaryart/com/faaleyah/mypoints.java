package binaryart.com.faaleyah;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mypoints extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypoints);
    }

    public void showpup(View view) {
        dailogs cdd=new dailogs(mypoints.this);
        cdd.show();
    }
}
