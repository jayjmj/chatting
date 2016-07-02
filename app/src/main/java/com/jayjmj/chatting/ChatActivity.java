package com.jayjmj.chatting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.facebook.login.LoginManager;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;

public class ChatActivity extends AppCompatActivity {

    public static final String FIREBASE_URL = "https://chatting-7986a.firebaseio.com/";

    private String userName;
    private EditText editText;
    private Button sendButton;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);

        Intent it = getIntent();
        userName = it.getStringExtra("userName");

        editText = (EditText)findViewById(R.id.editText);
        sendButton = (Button)findViewById(R.id.button);

        new Firebase(FIREBASE_URL).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                adapter.add(chatData.getUserName()+" : " +chatData.getMessage());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                new Firebase(FIREBASE_URL).push().setValue(new ChatData(userName,editText.getText().toString()));
                editText.setText("");
            }

        });
    }

    public void signOut(View v) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
