package abc.def.realm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class Update extends AppCompatActivity {
    private EditText name,duration,track,description;
    Button but,delete;
    private String names,durations,tracks,descriptions;
    private long id;
    private Realm realm;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        realm = Realm.getDefaultInstance();
        name = findViewById(R.id.n1);
        duration = findViewById(R.id.n2);
        track = findViewById(R.id.n3);
        description = findViewById(R.id.n4);
        but = findViewById(R.id.but);
        delete = findViewById(R.id.delete);

        names = getIntent().getStringExtra("coursename");
        durations = getIntent().getStringExtra("courseduration");
        tracks = getIntent().getStringExtra("coursetrack");
        descriptions = getIntent().getStringExtra("coursedescription");
        id = getIntent().getLongExtra("id",0);

        name.setText(names);
        duration.setText(durations);
        track.setText(tracks);
        description.setText(descriptions);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse(id);
                Intent intent = new Intent(Update.this,Display.class);
                startActivity(intent);
            }
        });



        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names = name.getText().toString();
                String durations = duration.getText().toString();
                String tracks = track.getText().toString();
                String descriptions = description.getText().toString();
                if(TextUtils.isEmpty(names)){
                    name.setError("Invalid text");
                }else if(TextUtils.isEmpty(durations)){
                    duration.setError("Invalid text");
                }else if (TextUtils.isEmpty(tracks)){
                    track.setError("Invalid text");
                }else if (TextUtils.isEmpty(descriptions)){
                    description.setError("Invalid text");
                }else{
                    final DataModal modal = realm.where(DataModal.class).equalTo("id",id).findFirst();
                    updateCourse(modal,names,durations,tracks,descriptions);
                }
                startActivity(new Intent(Update.this,Display.class));
                Toast.makeText(Update.this, "data updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteCourse(long id) {
        DataModal modal = realm.where(DataModal.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                modal.deleteFromRealm();
            }
        });
    }

    private void updateCourse(DataModal modal, String names, String durations, String tracks, String descriptions) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                modal.setCourseName(names);
                modal.setCourseDuration(durations);
                modal.setCourseTracks(tracks);
                modal.setCourseDescription(descriptions);

                realm.copyToRealmOrUpdate(modal);
            }
        });


    }
}