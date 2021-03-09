package com.team254.lib.drivers;

import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public interface ILazyMotorControllerEnhanced extends IMotorControllerEnhanced {
    double getLastSet();
}
