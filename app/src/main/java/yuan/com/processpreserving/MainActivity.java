package yuan.com.processpreserving;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import yuan.com.processpreserving.onepx.LiveService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_onepx_activity)
    Button btnOnepxActivity;
    @BindView(R.id.btn_forground_server)
    Button btnForgroundServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnOnepxActivity.setOnClickListener(this);
        btnForgroundServer.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_onepx_activity:
                LiveService.toLiveService(this);
                break;
            case R.id.btn_forground_server:
                startService(new Intent(MainActivity.this, KeepLiveService.class));
                break;
        }
    }
}
