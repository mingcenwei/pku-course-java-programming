package com.example.java;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class RoundImageDrawable extends Drawable {

    private Paint paint;//画笔
    private Bitmap bitmap;//我们要操作的Bitmap
    private RectF rectF;//矩形f

    public RoundImageDrawable(Bitmap bitmap) {
        this.bitmap = bitmap;
        paint = new Paint();//初始化画笔
        paint.setAntiAlias(true);//抗锯齿
        //位图渲染器(参数1:我们要操作的Bitmap,参数2.3:X轴,Y轴的填充类型,
        // 类型一共有三种,REPEAT:重复类型,CLAMP:拉伸类型(注意这里的拉伸是指拉伸图片的而最后一个像素),MIRROM:镜像类型)
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(shader);
    }

    /**
     * 这个方法是指drawbale将被绘制在画布上的区域
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    //左上右下
    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        //绘制区域
        rectF = new RectF(left, top, right, bottom);

    }

    //获取bipmap的高度
    @Override
    public int getIntrinsicHeight() {
        return bitmap.getHeight();
    }

    //获取bitmap的宽
    @Override
    public int getIntrinsicWidth() {
        return bitmap.getWidth();
    }


    /**
     * 这是我们的核心方法,绘制我们想要的图片
     *
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        //参数1:绘制的区域,参数2:X轴圆角半径,参数3:Y轴圆角半径,参数4:画笔
        //canvas.drawRoundRect(rectF, 50, 50, paint);
        //画圆(参数1.2:确定圆心坐标,参数3:半径,参数4:画笔)
        canvas.drawCircle(getIntrinsicWidth() / 2, getIntrinsicHeight() / 2, getIntrinsicWidth() / 2, paint);
    }


    //设置透明度
    @Override
    public void setAlpha(int alpha) {

    }

    //设置滤镜渲染颜色
    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        final int i = 0;
        return i;
    }

}

