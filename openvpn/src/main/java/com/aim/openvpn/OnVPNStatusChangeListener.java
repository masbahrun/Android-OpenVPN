package com.aim.openvpn;

public interface OnVPNStatusChangeListener
{
    public void onProfileLoaded(boolean profileLoaded);
    public void onVPNStatusChanged(boolean vpnActivated);

}

