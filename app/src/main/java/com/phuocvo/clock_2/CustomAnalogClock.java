package com.phuocvo.clock_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.Calendar;

public class CustomAnalogClock extends View {

    private Paint paint;
    private int width, height, radius;
    private int centerX, centerY;
    private Calendar calendar;

    public CustomAnalogClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        calendar = Calendar.getInstance();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radius = Math.min(width, height) / 2 - 20;
        centerX = width / 2;
        centerY = height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calendar.setTimeInMillis(System.currentTimeMillis());

        // Vẽ mặt đồng hồ
        drawClockFace(canvas);

        // Vẽ các kim đồng hồ
        drawHands(canvas);

        // Làm cho đồng hồ cập nhật mỗi giây
        postInvalidateDelayed(1000);
    }

    private void drawClockFace(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radius, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        canvas.drawCircle(centerX, centerY, radius, paint);

        // Vẽ các con số
        paint.setTextSize(40);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 1; i <= 12; i++) {
            String number = String.valueOf(i);
            double angle = Math.toRadians((i - 3) * 30);
            int x = (int) (centerX + Math.cos(angle) * (radius - 50));
            int y = (int) (centerY + Math.sin(angle) * (radius - 50) + 15);  // Để con số canh chỉnh đúng
            canvas.drawText(number, x, y, paint);
        }
    }

    private void drawHands(Canvas canvas) {
        // Lấy giờ, phút, giây hiện tại
        int hours = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        // Tính toán góc cho các kim
        double secondAngle = Math.toRadians((seconds - 15) * 6);
        double minuteAngle = Math.toRadians((minutes - 15) * 6);
        double hourAngle = Math.toRadians((hours - 3) * 30 + (minutes / 2));

        // Vẽ kim giây (màu đỏ)
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        canvas.drawLine(centerX, centerY, (float) (centerX + Math.cos(secondAngle) * (radius - 40)),
                (float) (centerY + Math.sin(secondAngle) * (radius - 40)), paint);

        // Vẽ kim phút (màu đen)
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        canvas.drawLine(centerX, centerY, (float) (centerX + Math.cos(minuteAngle) * (radius - 60)),
                (float) (centerY + Math.sin(minuteAngle) * (radius - 60)), paint);

        // Vẽ kim giờ (màu đen, ngắn hơn)
        paint.setStrokeWidth(10);
        canvas.drawLine(centerX, centerY, (float) (centerX + Math.cos(hourAngle) * (radius - 100)),
                (float) (centerY + Math.sin(hourAngle) * (radius - 100)), paint);
    }
}