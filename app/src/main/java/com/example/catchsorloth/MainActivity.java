package com.example.catchsorloth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.example.catchsorloth.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int score;
    private Handler handler;
    private Runnable runnable;
    private ImageView[] imageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        score = 0;
        setArray();
        hideImage();
        imageViews = new ImageView[]{binding.image1,binding.image2,binding.image3,binding.image4,binding.image5,binding.image6,binding.image7,binding.image8,binding.image9,binding.image10,binding.image11,binding.image12,binding.image13,binding.image14,binding.image15,binding.image16};

    }
    public void increaseScore(View view ){

        score++;
        binding.score.setText("Skor: "+score );
    }
    public void hideImage(){
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                binding.time.setText("Kalan zaman: "+ l/1000);
            }

            @Override
            public void onFinish() {
                binding.time.setText("Kalan Zaman: Bitti!");

                for (ImageView image : imageViews){
                    image.setVisibility(View.INVISIBLE);
                }
                binding.restart.setVisibility(View.VISIBLE);
                handler.removeCallbacks(runnable);
                binding.restart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        score = 0;
                        binding.score.setText("Score: "+ score);
                        hideImage();

                    }
                });



            }
        }.start();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageViews){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(16);
                imageViews[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,350);
            }
        };
        handler.post(runnable);
    }
    public void setArray(){
        imageViews = new ImageView[]{binding.image1,binding.image2,binding.image3,
                binding.image4,binding.image5,binding.image6,binding.image7,
                binding.image8,binding.image9,binding.image10,binding.image11,binding.image12,
                binding.image13,binding.image14,binding.image15,binding.image16};
    }
}