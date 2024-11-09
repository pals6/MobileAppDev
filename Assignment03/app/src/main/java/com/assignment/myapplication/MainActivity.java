package com.assignment.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textViewGameStatus;
    private final int[] imageViewFruitsId={R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,
            R.id.imageView8,R.id.imageView9,R.id.imageView10,R.id.imageView11,R.id.imageView12,R.id.imageView13,R.id.imageView14,R.id.imageView15,R.id.imageView16,R.id.imageView17,
            R.id.imageView18,R.id.imageView19,R.id.imageView20,R.id.imageView21,R.id.imageView22,R.id.imageView23,R.id.imageView24,R.id.imageView25};
    private final int[] selectableFruitIds={R.drawable.apple,R.drawable.lemon,R.drawable.peach,R.drawable.mango,R.drawable.strawberry,R.drawable.tomato};
    private  String[] imageNames = {"Apple", "Lemon", "Peach", "Mango", "Strawberry", "Tomato"};
    private int focusImageId;
    private String focusImageName;
    private int focusImageCount;
    private GridLayout gameGrid;
    //private ArrayList<Integer> shuffledSelectableFruitIds= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textViewGameStatus= findViewById(R.id.textViewGameStatus);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupNewGame();
            }
        });
        findViewById(R.id.imageView1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupNewGame();
            }
        });
        SetupNewGame();
    }



    @SuppressLint("SetTextI18n")
    private void SetupNewGame(){
        //gameGrid.removeAllViews();
        //shuffledSelectableFruitIds.clear();
        ArrayList<Integer> shuffledSelectableFruitIds= new ArrayList<>();
        Random random=new Random();
        int focusIndex=random.nextInt(selectableFruitIds.length);
        Log.d("size of focus index","size="+focusIndex);
        focusImageId=selectableFruitIds[focusIndex];
        focusImageName=imageNames[focusIndex];
        Log.d("Focus Image name","name="+focusImageName);

        // Select a random number N in the range [1, 8]
        focusImageCount = random.nextInt(8) + 1;

        for (int i=0; i<focusImageCount;i++){
            shuffledSelectableFruitIds.add(focusImageId);
            //Collections.shuffle(shuffledSelectableFruitIds);
        }

        for (int i = 0; i < 25-focusImageCount; i++) {
                int randomImage;
                do {
                    randomImage = selectableFruitIds[random.nextInt(selectableFruitIds.length)];
                } while (randomImage == focusImageId);  // Ensure non-focus image
                shuffledSelectableFruitIds.add(randomImage);
        }

        // Shuffle the images
        Collections.shuffle(shuffledSelectableFruitIds);
        Log.d("count of list is:","count="+shuffledSelectableFruitIds.size());
        // Update UI with the focus image info
        //textViewGameStatus.setText("Find All Apples ");

        for (int i = 0; i < shuffledSelectableFruitIds.size(); i++) {
            //Collections.shuffle(shuffledSelectableFruitIds);
            int imageViewId=imageViewFruitsId[i];
            int selectableId=shuffledSelectableFruitIds.get(i);
            FruitsInfo fruitsInfo=new FruitsInfo(imageViewId,selectableId);
            ImageView imageView=findViewById(imageViewId);
            imageView.setTag(fruitsInfo);
            imageView.setOnClickListener(this);
            //ImageButton imageButton = new ImageButton(this);
            imageView.setImageResource(shuffledSelectableFruitIds.get(i));
            //gameGrid.addView(imageView);

        }
        /*for (int selectableFruitId: selectableFruitIds) {
         //   shuffledSelectableFruitIds.add(selectableFruitId);
         //   Collections.shuffle(shuffledSelectableFruitIds);
            for (int i = 0; i < shuffledSelectableFruitIds.size(); i++) {
                int imageViewId=imageViewFruitsId[i];
                int selectableId=shuffledSelectableFruitIds.get(i);
                FruitsInfo fruitsInfo=new FruitsInfo(imageViewId,selectableId);
                ImageView imageView=findViewById(imageViewId);
                imageView.setTag(fruitsInfo);
                imageView.setOnClickListener(this);
            }

        }*/


    }
    FruitsInfo fruitsInfo1=null;
    int matchCount=0;
    @Override
    public void onClick(View v) {
        ImageView imageView=(ImageView) v;
        FruitsInfo fruitsInfo=(FruitsInfo) imageView.getTag();
        Log.d("demo","OnCreate" + fruitsInfo);

        /*if(!fruitsInfo.isMatched() && !fruitsInfo.isSelected()){
            imageView.setImageResource(fruitsInfo.getSelectableFruitIds());
            fruitsInfo.setSelected(true);
            if(fruitsInfo1==null){
                fruitsInfo1=fruitsInfo;
            }
            else{
                if(fruitsInfo.getSelectableFruitIds()==fruitsInfo1.getSelectableFruitIds()){
                     matchCount++ ;
                     fruitsInfo.setMatched(true);
                     fruitsInfo1.setMatched(true);

                }
            }
        }*/
    }
}