package com.example.user.yeyoung_front;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class initialReviewActivity extends AppCompatActivity {
    ArrayList<String> prod_name = new ArrayList<>();
    ArrayList<String> prod_score = new ArrayList<>();
    static float density;
    static int dpHeight, dpWidth;
    RecyclerView recyclerView;
    CustAdapter custAdapter;
    Button nextbtn;
    Uri allUri;
    int cameraRequest=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_review);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        nextbtn = (Button) findViewById(R.id.next);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(initialReviewActivity.this, mainScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        density = getResources().getDisplayMetrics().density;
        dpHeight = (int) (outMetrics.heightPixels/*density*/);
        dpWidth = (int) (outMetrics.widthPixels/*density*/);
        String name[] = new String[]{"Dr.G MEDI UV ULTRA SUN", "YEBLANG Special SESITIVE SUN CREAM"};
        String score[] = new String[]{"2", "3"};
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_initial_review);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        custAdapter = new CustAdapter();
        recyclerView.setAdapter(custAdapter);

//        for (int i = 0; i < loginActivity.number; i++) {
//            Log.e("asdasd: ", String.valueOf(prod_name.size()));
//            custAdapter.add(new CustContainer(name[i], score[i]));
//        }
//        custAdapter.notifyDataSetChanged();
    }
    public void cameraView(View v){
        Uri uri = FileProvider.getUriForFile(this, "com.bignerdranch.android.test.fileprovider",new File(Environment.getExternalStorageDirectory(), "tmp_contact_" + System.currentTimeMillis() + ".jpg"));
        allUri = uri;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, cameraRequest);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                    if (requestCode == cameraRequest && resultCode == Activity.RESULT_OK){
                discharge num = new discharge();

                Intent intent = new Intent(this, cpicture_list.class);
                intent.putExtra("image", allUri);
                intent.putExtra("number", num.getGlobalString());
                startActivity(intent);

        }
    }
}

class CustHolder extends RecyclerView.ViewHolder {
    TextView tv, tvw;

    public CustHolder(final View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.product_name);
        tvw = itemView.findViewById(R.id.score);

        //옆으로 슬라이드
        LinearLayout content = itemView.findViewById(R.id.one_card_content);
        ViewGroup.LayoutParams param = content.getLayoutParams();
        param.width = initialReviewActivity.dpWidth - 10 * (int) initialReviewActivity.density;  //여백주기(마이너스 부분)
        content.setLayoutParams(param);

        itemView.setOnTouchListener(new View.OnTouchListener() {
            float x1 = 0f;
            float x2 = 0f;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        Log.d("Listener", "...");
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        float deltaX = x2 - x1;
                        Log.d("Event", "Happy" + deltaX);
                        TransitionManager.beginDelayedTransition((ViewGroup) v);
                        if (Math.abs(deltaX) > 25) {
                            //Left to Right swipe action
                            if (deltaX > 0) {
                                ConstraintSet constraintSet = new ConstraintSet();
                                constraintSet.clone((ConstraintLayout) itemView);
                                constraintSet.connect(R.id.one_card_layout, ConstraintSet.START, R.id.activity_cardview_test_layout, ConstraintSet.START, 0);
                                constraintSet.clear(R.id.one_card_layout, ConstraintSet.END);
                                constraintSet.applyTo((ConstraintLayout) itemView);
                                Log.d("dd", "left to right ");
                            }
                            //Right to left swipe action
                            else {
                                ConstraintSet constraintSet = new ConstraintSet();
                                constraintSet.clone((ConstraintLayout) itemView);
                                constraintSet.connect(R.id.one_card_layout, ConstraintSet.END, R.id.activity_cardview_test_layout, ConstraintSet.END, 0);
                                constraintSet.clear(R.id.one_card_layout, ConstraintSet.START);
                                constraintSet.applyTo((ConstraintLayout) itemView);
                                Log.d("ss", "right to left");
                            }
                        } else {
                            //consider as something else - a screen tap fo example
                        }
                        break;
                }
                return true;
            }
        });

    }
}

//카드뷰 안에 들어가 있는 내용들 넣기(Default value)
class CustContainer {
    String s1 = "나는 에러났다.";
    String s2 = "이것도 에러다.";

    public CustContainer(String i, String j) {
        s1 = i;
        s2 = j;
    }
}

//Adapter
class CustAdapter extends RecyclerView.Adapter<CustHolder> {
    ArrayList<CustContainer> arrayList = new ArrayList<>();

    @NonNull
    @Override
    //목록이 생겼을 때 도는 메소드
    public CustHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View testView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_test, parent, false);
        return new CustHolder(testView);
    }

    //변경사항이 생겼을 때 도는 메소드
    @Override
    public void onBindViewHolder(@NonNull CustHolder holder, int position) {
        CustContainer amuguna = arrayList.get(position);
        holder.tv.setText(amuguna.s1);
        holder.tvw.setText(amuguna.s2);
    }

    //이 클래스 안에 카드뷰 갯수 넣는 메소드
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void add(CustContainer content) {
        arrayList.add(content);
    }
}
