package com.shahrukhzarir.assignment1;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import android.content.Intent;
import android.view.View;


public class MainMenu extends AppCompatActivity {

    //limitation
    private final int MAX=5;
    private int counter = 0;

    //Constants
    private final int QUESTIONREQCODE =1;
    private final int SUMMARYREQCODE=2;

    //The questions will be found in an array resource
    private ArrayList<String> responses = new ArrayList<String>();
    private String[] questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Create an activity (MainMenu) that has a single button
        Button askQuestionButton = (Button)findViewById(R.id.answer_question);
        askQuestionButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        // Sets an intent for the next activity
                        Intent nextActivityIntent = new Intent();

                        //Checks to see if any questions left to ask
                        String question = requestQuestion();

                        //if there is then call AskQuestion activity
                        if (question != null){
                            nextActivityIntent.setClass(MainMenu.this,AskQuestion.class);
                            nextActivityIntent.putExtra("question",question);
                            //  Asking question in Question Activity
                            startActivityForResult(nextActivityIntent,QUESTIONREQCODE);
                        }

                        //if completed will call Summary Activity
                        else{
                            nextActivityIntent.setClass(MainMenu.this,Summary.class);
                            nextActivityIntent.putExtra("answer",responses);
                            startActivityForResult(nextActivityIntent,SUMMARYREQCODE);
                        }
                    }
                }
        );
    }


    public void onActivityResult(int reqCode, int resCode, Intent intent){
        //Gets result
        String answer;
        if(reqCode == 1){
            int selectQuestion = counter-1;
            //if 1 then answer is yes and adds it to a potential string builder
            if (resCode == 1){
                answer = questionsList[selectQuestion] + "\t :Selected Yes";
                responses.add(answer);
            }
            //if 0 then answer is no and adds it to a potential string builder
            else if(resCode == 0){
                answer = questionsList[selectQuestion] + "\t: Selected No";
                responses.add(answer);
            }
        }
    }

    private String requestQuestion() {

        //Retrieves question from resources array
        Resources res = getResources();
        questionsList = res.getStringArray(R.array.questions);
        if(counter < questionsList.length) {
            return questionsList[counter++];
        }
        return null;
    }
}
