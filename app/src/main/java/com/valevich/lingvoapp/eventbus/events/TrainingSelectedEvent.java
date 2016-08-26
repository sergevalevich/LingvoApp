package com.valevich.lingvoapp.eventbus.events;


import com.valevich.lingvoapp.stubmodel.Training;

public class TrainingSelectedEvent {
    private Training mTraining;

    public TrainingSelectedEvent(Training training) {
        mTraining = training;
    }

    public Training getTraining() {
        return mTraining;
    }
}
