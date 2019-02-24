package com.example.androidlabs;

import android.content.Context;
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
    List<MessageModel> messageModelList = new ArrayList<MessageModel>();
    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        listView = (ListView)findViewById(R.id.listView);
        messageBox = (EditText)findViewById(R.id.editMessage);

        adapter = new ChatAdapter(messageModelList, getApplicationContext());
        listView.setAdapter(adapter);
    }

    public void btnSendClick(View view) {
        String msg = messageBox.getText().toString();
        messageModelList.add(new MessageModel(msg, true));
        adapter.notifyDataSetChanged();
        messageBox.setText("");
    }

    public void btnReceiveClick(View view) {
        String msg = messageBox.getText().toString();
        messageModelList.add(new MessageModel(msg, false));
        adapter.notifyDataSetChanged();
        messageBox.setText("");
    }

    class MessageModel {
        public String message;
        public boolean isSent;

        public MessageModel() {
            this(null, false);
        }
        public MessageModel(String message, boolean isSent) {
            this.message = message;
            this.isSent = isSent;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isSent() {
            return isSent;
        }

        public void setSent(boolean sent) {
            this.isSent = sent;
        }
    }

    class ChatAdapter extends BaseAdapter {
        private List<MessageModel> messageModels;
        private Context context;
        private LayoutInflater inflater;

        public ChatAdapter(List<MessageModel> messageModels, Context context) {
            this.messageModels = messageModels;
            this.context = context;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        @Override
        public int getCount() {
            return messageModels.size();
        }

        @Override
        public Object getItem(int position) {
            return messageModels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (messageModels.get(position).isSent()) {
                view = inflater.inflate(R.layout.activity_chat_send, null);

            } else {
                view = inflater.inflate(R.layout.activity_chat_receive, null);
            }
            TextView messageText = (TextView) view.findViewById(R.id.messageText);
            messageText.setText(messageModels.get(position).getMessage());

            return view;
        }
    }
}
