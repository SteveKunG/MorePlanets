package com.stevekung.moreplanets.utils.entity;

public interface ISpaceMob
{
    EnumMobType getMobType();

    public enum EnumMobType
    {
        NIBIRU,
        ROBOT;
    }
}