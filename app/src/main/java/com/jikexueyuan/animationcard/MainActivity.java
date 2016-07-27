package com.jikexueyuan.animationcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgA;
    private ImageView imgB;
    private ScaleAnimation sa1;
    private ScaleAnimation sa2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgA = (ImageView) findViewById(R.id.imgA);
        imgB = (ImageView) findViewById(R.id.imgB);
        //初始化界面，显示imgA,隐藏imgB
        imgA.setVisibility(View.VISIBLE);
        imgB.setVisibility(View.INVISIBLE);
        //创建两个ScaleAnimation，第一个将图片从两边缩到中间，第二个Animation将图片从中间开始展开至两边，这样连到一起就形成了翻转的效果。
        sa1 = new ScaleAnimation(1, 0, 1, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa2 = new ScaleAnimation(0, 1, 1, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画时间
        sa1.setDuration(1000);
        sa2.setDuration(1000);

        imgA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("imgA clicked");
                view.startAnimation(sa1);
                imgB.startAnimation(sa2);
                view.setVisibility(View.INVISIBLE);
                imgB.setVisibility(View.VISIBLE);

                //通过设置AnimationListener的方法，确保sa1已经执行完毕再执行sa2
                sa1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation){}

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation){}
                });
            }
        });
        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sa2.setStartOffset(1000);
                view.startAnimation(sa1);
                view.setVisibility(View.INVISIBLE);

                imgA.startAnimation(sa2);
                imgA.setVisibility(View.VISIBLE);
                sa2.setStartOffset(0);
                //通过设置AnimationListener的方法，确保sa1已经执行完毕再执行sa2
                sa1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
            }
        });
    }
}
