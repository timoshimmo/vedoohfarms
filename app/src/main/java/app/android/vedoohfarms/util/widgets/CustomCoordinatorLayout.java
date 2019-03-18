package app.android.vedoohfarms.util.widgets;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

/**
 * Created by freshfuturesmy on 21/08/17.
 */

public class CustomCoordinatorLayout extends CoordinatorLayout {
    private boolean scrollStatus = false;

    public CustomCoordinatorLayout(Context context) {
        super(context);
    }

    public CustomCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isPassScrolling() {

        return scrollStatus;
    }

    public void setPassScrolling(boolean allowForScroll) {
        this.scrollStatus = allowForScroll;
    }
}
