package android.example.voicerecognitionapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class StartVoiceRecognition extends Activity {

    private final int REQUEST_SPEECH_RECOGNIZER = 3000;
    private TextView textView;
    private String speech = "";
    private String searchQuestion="What do you want to search for?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView1);
        if (!SpeechRecognizer.isRecognitionAvailable(this)) {
            textView.setText("Voice recognition is not available on your device");
        } else {
            startSpeechRecognizer();
        }
    }

    private void startSpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, searchQuestion);
        startActivityForResult(intent, REQUEST_SPEECH_RECOGNIZER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SPEECH_RECOGNIZER) {
            if (resultCode == RESULT_OK) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                speech = results.get(0);
                textView.setText(speech);
            }
        }
    }

    /*
                if (mAnswer.toUpperCase().indexOf("AMAZON") > -1)
                    mTextView.setText("\n\nQuestion: " + mQuestion + "\n\nYour answer is '" + mAnswer + "' and it is correct!");
                else
                    mTextView.setText("\n\nQuestion: " + mQuestion + "\n\nYour answer is '" + mAnswer + "' and it is incorrect!");
                 */
}
