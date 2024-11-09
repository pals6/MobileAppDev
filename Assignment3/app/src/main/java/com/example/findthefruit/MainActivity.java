package com.example.findthefruit;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView targetFruitName;
    private TextView remainingFruitsCount;
    private GridLayout gameBoard;
    private Button resetButton;

    private List<Integer> fruitImages;
    private int focusImage;
    private int focusCount;
    private int remainingCount;
    private Map<Integer, String> fruitNamesMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        targetFruitName = findViewById(R.id.target_fruit_name);
        remainingFruitsCount = findViewById(R.id.remaining_fruits_count);
        gameBoard = findViewById(R.id.gameBoard);
        resetButton = findViewById(R.id.resetButton);

        initializeFruitNamesMap();

        resetButton.setOnClickListener(v -> initializeGame());

        initializeGame();
    }

    private static final int[] FRUIT_IMAGES = {
            R.drawable.apple,
            R.drawable.lemon,
            R.drawable.peach,
            R.drawable.strawberry,
            R.drawable.tomato,
            R.drawable.mango
    };

    private void initializeFruitNamesMap() {
        fruitNamesMap = new HashMap<>();
        fruitNamesMap.put(R.drawable.apple, "Apple");
        fruitNamesMap.put(R.drawable.lemon, "Lemon");
        fruitNamesMap.put(R.drawable.peach, "Peach");
        fruitNamesMap.put(R.drawable.strawberry, "Strawberry");
        fruitNamesMap.put(R.drawable.tomato, "Tomato");
        fruitNamesMap.put(R.drawable.mango, "Mango");
    }

    private String getFruitName(int imageResId) {
        return fruitNamesMap.getOrDefault(imageResId, "Unknown");
    }

    private void initializeGame() {
        fruitImages = new ArrayList<>();
        Random random = new Random();

        focusImage = FRUIT_IMAGES[random.nextInt(FRUIT_IMAGES.length)];

        focusCount = random.nextInt(8) + 1;
        remainingCount = focusCount;

        targetFruitName.setText("Find All " + getFruitName(focusImage));
        remainingFruitsCount.setText(String.valueOf(focusCount));

        populateGrid();
    }

    private void populateGrid() {
        gameBoard.removeAllViews();
        List<Integer> allImages = new ArrayList<>();

        for (int i = 0; i < focusCount; i++) {
            allImages.add(focusImage);
        }

        for (int i = 0; i < 25 - focusCount; i++) {
            int randomImage;
            do {
                randomImage = FRUIT_IMAGES[new Random().nextInt(FRUIT_IMAGES.length)];
            } while (randomImage == focusImage);
            allImages.add(randomImage);
        }

        Collections.shuffle(allImages);

        for (int i = 0; i < 25; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(allImages.get(i));
            imageView.setTag(allImages.get(i));


            imageView.setAlpha(1.0f);
            imageView.setClickable(true);


            GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                    GridLayout.spec(i / 5),
                    GridLayout.spec(i % 5)
            );
            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.setMargins(8, 8, 8, 8);
            imageView.setLayoutParams(params);
            imageView.setOnClickListener(this::onImageClick);
            gameBoard.addView(imageView);
        }
    }

    private void onImageClick(View view) {
        ImageView clickedImage = (ImageView) view;
        int imageResourceId = (Integer) clickedImage.getTag();
        if (imageResourceId == focusImage) {


            clickedImage.setAlpha(0.5f);
            clickedImage.setClickable(false);


            remainingCount--;
            remainingFruitsCount.setText(String.valueOf(remainingCount));


            if (remainingCount == 0) {
                new AlertDialog.Builder(this)
                        .setTitle("Found All Shapes")
                        .setMessage("Congratulations!! You have found all " + getFruitName(focusImage) + "s.")
                        .setPositiveButton("OK", (dialog, which) -> initializeGame())
                        .show();
                initializeGame();

            }
            else {

                shuffleGrid();
            }

        }
    }


    private void shuffleGrid() {
        List<ImageView> unselectedImages = new ArrayList<>();
        Map<Integer, Integer> originalPositions = new HashMap<>();
        for (int i = 0; i < gameBoard.getChildCount(); i++) {
            View child = gameBoard.getChildAt(i);
            if (child instanceof ImageView) {
                ImageView imageView = (ImageView) child;
                int imageResourceId = (Integer) imageView.getTag();

                if (imageView.getAlpha() == 0.5f) {
                    originalPositions.put(i, imageResourceId);
                } else {
                    unselectedImages.add(imageView);
                }
            }
        }

        Collections.shuffle(unselectedImages);

        gameBoard.removeAllViews();

        int unselectedIndex = 0;

        for (int i = 0; i < 25; i++) {
            ImageView newImageView;

            if (originalPositions.containsKey(i)) {
                newImageView = new ImageView(this);
                int resourceId = originalPositions.get(i);
                newImageView.setImageResource(resourceId);
                newImageView.setTag(resourceId);
                newImageView.setAlpha(0.5f);
                newImageView.setClickable(false);
            } else if (unselectedIndex < unselectedImages.size()) {
                ImageView imageView = unselectedImages.get(unselectedIndex++);
                int resourceId = (Integer) imageView.getTag();

                newImageView = new ImageView(this);
                newImageView.setImageResource(resourceId);
                newImageView.setTag(resourceId);
                newImageView.setAlpha(1.0f);
                newImageView.setClickable(true);
                newImageView.setOnClickListener(this::onImageClick);
            } else {
                continue;
            }


            GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                    GridLayout.spec(i / 5),
                    GridLayout.spec(i % 5)
            );
            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.setMargins(8, 8, 8, 8);
            newImageView.setLayoutParams(params);

            gameBoard.addView(newImageView);
        }
    }



}
