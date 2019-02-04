package com.brainstorm314.sougataghosh.retrievecount;

import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference firebaseDatabaseRef;
    ArrayList<HallStatus> statusList;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusList = new ArrayList<>();

        textView = findViewById(R.id.textView);

        firebaseDatabaseRef = FirebaseDatabase.getInstance().getReference("Movies");

        firebaseDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue().toString();
                    Log.e("DEBUG", name);
                    Integer Acount = 0, Rcount = 0;

                    DataSnapshot tempSnapshot = snapshot.child("hall").child("status");

                    for (int i = 0; i < 358; i++) {
                        if (tempSnapshot.child(String.valueOf(i)).getValue().toString().equals("A")) {
                            Acount++;
                        } else if (tempSnapshot.child(String.valueOf(i)).getValue().equals("R")) {
                            Rcount++;
                        }
                    }

                    Log.e("DEBUG", String.valueOf(Acount)+"  :  "+ String.valueOf(Rcount));

                    HallStatus obj = new HallStatus(name,
                            String.valueOf(Acount), String.valueOf(Rcount));

                    statusList.add(obj);
                    show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void show() {
        StringBuilder line = new StringBuilder("");

        Log.e("DEBUG", "Size: "+statusList.size());

        for (int i = 0; i<statusList.size(); i++) {
            HallStatus temp = statusList.get(i);
            line.append(temp.movieName+"  Available: "+temp.availableCount+
                    "  Reserved: "+temp.reservedCount+"\n");
        }

        textView.setText(line);
    }
}
