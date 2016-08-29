package com.valevich.lingvoapp.eventbus.events;

public class ArrowClickedEvent {
    private boolean mIsLeftArrowClicked;

    public ArrowClickedEvent(boolean isLeftArrowClicked) {
        mIsLeftArrowClicked = isLeftArrowClicked;
    }

    public boolean isLeftArrowClicked() {
        return mIsLeftArrowClicked;
    }
}
