package com.pranay.myrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    ArrayList<Mylist> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


       databaseReference=FirebaseDatabase.getInstance().getReference();
       storageReference= FirebaseStorage.getInstance().getReference();

       list=new ArrayList<>();





       init();


    }


    private void init()
    {


        Query query=databaseReference.child("mylist");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                try {



                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())

                    {

                        Mylist mylist=new Mylist();
                        mylist.setImage(dataSnapshot1.child("imgurl").getValue().toString());
                        mylist.setTitle(dataSnapshot1.child("title").getValue().toString());
                        mylist.setDescription(dataSnapshot1.child("description").getValue().toString());

                        list.add(mylist);
                    }

                }catch (Exception e)
                {

                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }

                adapter=new Adapter(list,MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
