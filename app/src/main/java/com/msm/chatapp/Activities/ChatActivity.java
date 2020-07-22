package com.msm.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.msm.chatapp.Adapters.MessagesAdapter;
import com.msm.chatapp.DataModels.ChatModel;
import com.msm.chatapp.R;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView messageList;
    private EditText msgInput;
    private Button msgSend;

    private RecyclerView.LayoutManager layoutManager;
    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageList=findViewById(R.id.message_list);

        ChatModel chatModel=(ChatModel) getIntent().getSerializableExtra("chat");

        layoutManager=new LinearLayoutManager(this);
        messageList.setLayoutManager(layoutManager);

        messagesAdapter=new MessagesAdapter(this);
        messageList.setAdapter(messagesAdapter);
        Toast.makeText(this,String.valueOf(chatModel.getMessages().size()),Toast.LENGTH_LONG).show();
        messagesAdapter.setList(chatModel.getMessages());




    }
}
