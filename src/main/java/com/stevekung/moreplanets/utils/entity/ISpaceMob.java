package com.stevekung.moreplanets.utils.entity;

public interface ISpaceMob
{
    EnumMobType getMobType();

    enum EnumMobType
    {
        NIBIRU,
        ROBOT
    }
}