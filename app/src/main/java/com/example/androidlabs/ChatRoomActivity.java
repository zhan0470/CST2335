package com.example.androidlabs;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {
    private ListView listView;
    EditText messageBox;
    List<MessageModel> messageModelList = new ArrayList<>();
    ChatAdapter adapter;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        listView = (ListView)findViewById(R.id.listView);
        messageBox = (EditText)findViewById(R.id.editMessage);
    }

    @Override
    protected void onResume() {
        super.onResume();

        db = new DatabaseHelper(this);

        Cursor cursor = db.viewData();
        if (cursor.getCount() != 0){
            while (cursor.moveToNext()){
                MessageModel model = new MessageModel(cursor.getString(1), cursor.getInt(2)==0?true:false);
                messageModelList.add(model);
            }
        }

        adapter = new ChatAdapter(messageModelList, getApplicationContext());
        listView.setAdapter(adapter);
    }

    public void btnSendClick(View view) {
        btnClick(true);
    }

    public void btnReceiveClick(View view) {
        btnClick(false);
    }

    private void btnClick(boolean isSent) {
        String msg = messageBox.getText().toString();
        if (!msg.isEmpty()) {
            db.insertData(msg, isSent);
            messageModelList.add(new MessageModel(msg, isSent));
            adapter.notifyDataSetChanged();
            messageBox.setText("");
        }
    }

}
