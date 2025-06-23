package com.stevekung.moreplanets.world;

public interface IMeteorType
{
    EnumMeteorType getMeteorType();
    double getMeteorSpawnFrequency();

    public enum EnumMeteorType
    {
        KOENTUS;
    }
}