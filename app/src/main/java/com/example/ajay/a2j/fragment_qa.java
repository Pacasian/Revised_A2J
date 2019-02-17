package com.example.ajay.a2j;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Metadata;
import ai.api.model.Result;
import ai.api.model.Status;

public class fragment_qa extends Fragment {
    public static final String TAG = fragment_lawyer.class.getName();
    ListView conversationList;
    ImageButton sendButton;
    EditText userInput;
    AIConfiguration aiConfiguration;
    AIDataService aiDataService;
    public static ArrayList<ChatData> chatlist;
    public static ChatAdapter chatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View  view=inflater.inflate(R.layout.fragment_qa,container,false);
        final AIConfiguration.SupportedLanguages lang =
                AIConfiguration.SupportedLanguages.fromLanguageTag("en");

        aiConfiguration = new AIConfiguration("a47ef2450f024fe5858e72c0af14a493",AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiDataService = new AIDataService(aiConfiguration);

        sendButton = (ImageButton) view.findViewById(R.id.imageButton);
        userInput = (EditText) view.findViewById(R.id.inputMessage);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = userInput.getText().toString();
                if (!text.trim().equals("")) {
                    addMessage(text, true);
                    userInput.setText("");
                    sendRequest(text);
                }
            }
        });
        conversationList = (ListView) view.findViewById(R.id.ConversationList);
        conversationList.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        conversationList.setStackFromBottom(true);

        chatlist = new ArrayList<ChatData>();
        chatAdapter = new ChatAdapter(getActivity(),chatlist);
        conversationList.setAdapter(chatAdapter);
        return view;
    }

    private void addMessage(String message, boolean isMine) {

        final ChatData chatMessage = new ChatData("", "","", isMine);
        chatMessage.body = message;
        chatAdapter.add(chatMessage);
        chatAdapter.notifyDataSetChanged();
    }
    private void sendRequest(String userText) {

        final String contextString = String.valueOf(userText);

        @SuppressLint("StaticFieldLeak") final AsyncTask<String, Void, AIResponse> task = new AsyncTask<String, Void, AIResponse>() {

            private AIError aiError;

            @Override
            protected AIResponse doInBackground(final String... params) {
                final AIRequest request = new AIRequest();
                String query = params[0];

                if (!TextUtils.isEmpty(query))
                    request.setQuery(query);

                try {
                    return aiDataService.request(request);
                } catch (final AIServiceException e) {
                    aiError = new AIError(e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(final AIResponse response) {
                if (response != null) {
                    onResult(response);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Error dabu", Toast.LENGTH_SHORT).show();
                }
            }
        };

        task.execute(contextString);
    }
    public void onResult(final AIResponse response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onResult");

                Log.i(TAG, "Received success response");

                // this is example how to get different parts of result object
                final Status status = response.getStatus();
                Log.i(TAG, "Status code: " + status.getCode());
                Log.i(TAG, "Status type: " + status.getErrorType());

                final Result result = response.getResult();
                Log.i(TAG, "Resolved query: " + result.getResolvedQuery());

                Log.i(TAG, "Action: " + result.getAction());

                final String speech = result.getFulfillment().getSpeech();
                Log.i(TAG, "Speech: " + speech);

                if (!TextUtils.isEmpty(speech))
                    addMessage(speech,false);

                final Metadata metadata = result.getMetadata();
                if (metadata != null) {
                    Log.i(TAG, "Intent id: " + metadata.getIntentId());
                    Log.i(TAG, "Intent name: " + metadata.getIntentName());
                }

                final HashMap<String, JsonElement> params = result.getParameters();
                if (params != null && !params.isEmpty()) {
                    Log.i(TAG, "Parameters: ");
                    for (final Map.Entry<String, JsonElement> entry : params.entrySet()) {
                        Log.i(TAG, String.format("%s: %s", entry.getKey(), entry.getValue().toString()));
                    }
                }
                switch (metadata.getIntentName()){
                    case "Weather":
                        Toast.makeText(getActivity().getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

        });
    }


}


