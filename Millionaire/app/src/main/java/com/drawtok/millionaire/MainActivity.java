package com.drawtok.millionaire;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvQuestion, tvNumQuestion, tvAnswer1,
             tvAnswer2, tvAnswer3, tvAnswer4, tvHelp50_50;
    ImageView imvPeople, imvNewQuestion;
    String urlData = "https://api.npoint.io/a0038fb03ffb35589617";
    String isAnswer = "";
    int titleNum = 0;
    final int NUM_QUESTION_MAX = 15;
    int numRandom;
    Random random;
    String question = "", correct = "";
    List<String> answers;
    List<Integer> blockQuestions;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blockQuestions = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            blockQuestions.add(i);
        }
        AnhXa();
        Thread help = new Thread(new Runnable() {
            @Override
            public void run() {
                useHelp();
            }
        });
        help.start();
        random = new Random();
        randomNumberQuestion();
    }

    private void AnhXa() {
        tvQuestion      = findViewById(R.id.tv_question);
        tvNumQuestion   = findViewById(R.id.tv_num_question);
        tvAnswer1       = findViewById(R.id.tv_answer_1);
        tvAnswer2       = findViewById(R.id.tv_answer_2);
        tvAnswer3       = findViewById(R.id.tv_answer_3);
        tvAnswer4       = findViewById(R.id.tv_answer_4);
        tvHelp50_50     = findViewById(R.id.tv_help_50);
        imvNewQuestion  = findViewById(R.id.imv_help_reload);
        imvPeople       = findViewById(R.id.imv_help_people);
    }

    //tạo câu hỏi ngẫu nhiên
    private void randomNumberQuestion()
    {
        Collections.shuffle(blockQuestions);
        numRandom = random.nextInt((NUM_QUESTION_MAX-1+1)+1);
        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.hint_answer);
        getJSONData();
    }
    //lấy dữ liệu từ json
    private void getJSONData()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlData,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonGames = response.getJSONArray("games");
                    JSONObject jsonGame = jsonGames.getJSONObject(blockQuestions.get(0));
                    JSONArray jsonQuestions = jsonGame.getJSONArray("questions");
                    /*String question = "", correct = "";
                    List<String> answers;*/
                    JSONObject jsonQuestion = jsonQuestions.getJSONObject(numRandom-1);
                    correct = jsonQuestion.getString("correct");
                    answers = new ArrayList<>();
                    JSONArray jsonArray = jsonQuestion.getJSONArray("content");
                    for(int j = 0; j < jsonArray.length(); j++)
                    {
                        answers.add(jsonArray.getString(j));
                    }
                    question = jsonQuestion.getString("question");

                    showQuestion();
                    //bắt sự kiện click và kiểm tra câu trả lời
                    handleClickEvent(correct);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    //lựa chọn câu trả lời
    private void handleClickEvent(String correct)
    {
        tvAnswer1.setOnClickListener(v->
        {
            tvAnswer1.setBackgroundResource(R.drawable.bg_orange);
            tvAnswer2.setClickable(false);
            tvAnswer3.setClickable(false);
            tvAnswer4.setClickable(false);
            isAnswer = "0";
            tvAnswer1.setAnimation(null);
            checkAnswer(correct);
        });

        tvAnswer2.setOnClickListener(v->
        {
            tvAnswer2.setBackgroundResource(R.drawable.bg_orange);
            tvAnswer1.setClickable(false);
            tvAnswer3.setClickable(false);
            tvAnswer4.setClickable(false);
            isAnswer = "1";
            tvAnswer2.setAnimation(null);
            checkAnswer(correct);
        });

        tvAnswer3.setOnClickListener(v->
        {
            tvAnswer3.setBackgroundResource(R.drawable.bg_orange);
            tvAnswer2.setClickable(false);
            tvAnswer1.setClickable(false);
            tvAnswer4.setClickable(false);
            isAnswer = "2";
            tvAnswer3.setAnimation(null);
            checkAnswer(correct);
        });

        tvAnswer4.setOnClickListener(v->
        {
            tvAnswer4.setBackgroundResource(R.drawable.bg_orange);
            tvAnswer2.setClickable(false);
            tvAnswer3.setClickable(false);
            tvAnswer1.setClickable(false);
            isAnswer = "3";
            tvAnswer4.setAnimation(null);
            checkAnswer(correct);
        });
    }

    private void checkAnswer(String correct) {
        if(correct.equals(isAnswer))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setBackgroundGreen(correct);
                    nextQuestion();
                }
            }, 1000);
        }else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setBackgroundRed(correct);
                    gameOver();
                }
            }, 500);
        }
    }

    private void gameOver()
    {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("Bạn đã thua :(((");
            }
        }, 1000);
    }
    //câu hỏi tiếp theo
    private void nextQuestion()
    {
        if(titleNum == 15)
        {
            showDialog("YOU WIN !!!");

        }else
        {
            Toast.makeText(this, "Chính xác!", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    randomNumberQuestion();
                    setAllDefaultAnswer();
                }
            }, 500);
        }
    }

    // hộp thoại thông báo
    private void showDialog(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                titleNum = 0;
                randomNumberQuestion();
                setAllDefaultAnswer();
                tvHelp50_50.setEnabled(true);
                tvHelp50_50.setText("50:50");
                tvHelp50_50.setTextColor(Color.WHITE);
                imvNewQuestion.setEnabled(true);
                imvNewQuestion.setImageResource(R.drawable.ic_baseline_cached_24);
                imvNewQuestion.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.white));
                imvPeople.setEnabled(true);
                imvPeople.setImageResource(R.drawable.ic_baseline_people_alt_24);
                imvPeople.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.white));
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setBackgroundGreen(String correct)
    {

        if(correct.equals("0"))
        {
            tvAnswer1.setBackgroundResource(R.drawable.bg_green);
        }
        if(correct.equals("1"))
        {
            tvAnswer2.setBackgroundResource(R.drawable.bg_green);
        }
        if(correct.equals("2"))
        {
            tvAnswer3.setBackgroundResource(R.drawable.bg_green);
        }
        if(correct.equals("3"))
        {
            tvAnswer4.setBackgroundResource(R.drawable.bg_green);
        }

    }
    private void setBackgroundRed(String correct)
    {
        if(isAnswer.equals("0"))
        {
            tvAnswer1.setBackgroundResource(R.drawable.bg_red);
        }
        if(isAnswer.equals("1"))
        {
            tvAnswer2.setBackgroundResource(R.drawable.bg_red);
        }
        if(isAnswer.equals("2"))
        {
            tvAnswer3.setBackgroundResource(R.drawable.bg_red);
        }
        if(isAnswer.equals("3"))
        {
            tvAnswer4.setBackgroundResource(R.drawable.bg_red);
        }
        if(!correct.isEmpty())
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setBackgroundGreen(correct);
                }
            }, 1000);
        }
    }
    //hiển thị câu hỏi
    private void showQuestion()
    {
        if(question.isEmpty()||answers.get(0).isEmpty())
        {
            randomNumberQuestion();
            getJSONData();
        }else
        {
            titleNum++;
            tvNumQuestion.setText("Câu "+(titleNum));
            tvQuestion.setText(question);
            question = "";
            tvAnswer1.setText(answers.get(0));
            tvAnswer2.setText(answers.get(1));
            tvAnswer3.setText(answers.get(2));
            tvAnswer4.setText(answers.get(3));
            answers.clear();
        }
    }
    //đặt background về mặc định
    private void setAllDefaultAnswer()
    {
        tvAnswer1.setBackgroundResource(R.drawable.bg_blue);
        tvAnswer2.setBackgroundResource(R.drawable.bg_blue);
        tvAnswer3.setBackgroundResource(R.drawable.bg_blue);
        tvAnswer4.setBackgroundResource(R.drawable.bg_blue);
        tvAnswer1.setClickable(true);
        tvAnswer2.setClickable(true);
        tvAnswer3.setClickable(true);
        tvAnswer4.setClickable(true);
    }
    //quyền trợ giúp
    private void useHelp()
    {
        tvHelp50_50.setOnClickListener(v->
        {
            if(correct.equals("0"))
            {
                tvAnswer3.setText("");
                tvAnswer4.setText("");
            }
            if(correct.equals("1"))
            {
                tvAnswer1.setText("");
                tvAnswer3.setText("");
            }
            if(correct.equals("2"))
            {
                tvAnswer2.setText("");
                tvAnswer4.setText("");
            }
            if(correct.equals("3"))
            {
                tvAnswer1.setText("");
                tvAnswer3.setText("");
            }
            tvHelp50_50.setText("X");
            tvHelp50_50.setTextColor(Color.RED);
            tvHelp50_50.setEnabled(false);
        });

        imvNewQuestion.setOnClickListener(v->
        {
            titleNum--;
            randomNumberQuestion();
            imvNewQuestion.setImageResource(R.drawable.ic_baseline_close_24);
            imvNewQuestion.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.red));
            imvNewQuestion.setEnabled(false);
        });

        imvPeople.setOnClickListener(v->
        {
            if(correct.equals("0"))
            {
                tvAnswer1.startAnimation(animation);
            }
            if(correct.equals("1"))
            {
                tvAnswer2.startAnimation(animation);
            }
            if(correct.equals("2"))
            {
                tvAnswer3.startAnimation(animation);
            }
            if(correct.equals("3"))
            {
                tvAnswer4.startAnimation(animation);
            }
            imvPeople.setImageResource(R.drawable.ic_baseline_close_24);
            imvPeople.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.red));
            imvPeople.setEnabled(false);
        });
    }
}