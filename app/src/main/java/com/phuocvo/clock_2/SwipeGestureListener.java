package com.phuocvo.clock_2;

import android.view.GestureDetector;
import android.view.MotionEvent;

class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    // Xác định khoảng cách tối thiểu và vận tốc tối thiểu để coi là vuốt
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float diffX = e2.getX() - e1.getX();
        float diffY = e2.getY() - e1.getY();
        try {
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX < 0) {
                        // Vuốt sang trái
                        onSwipeLeft();
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public void onSwipeLeft() {
        // Hành động khi vuốt sang trái
    }
}
