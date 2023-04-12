package abc.def.realm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Display extends AppCompatActivity {

    List<DataModal> dataModalList;
    Realm realm;
    RecyclerView recyclerView;
    Recycler_view_adapter recycler_view_adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        realm = Realm.getDefaultInstance();
        recyclerView=findViewById(R.id.rlst);

        dataModalList = new ArrayList<>();

        dataModalList = realm.where(DataModal.class).findAll();

        recycler_view_adapter = new Recycler_view_adapter(dataModalList,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recycler_view_adapter);
    }
}