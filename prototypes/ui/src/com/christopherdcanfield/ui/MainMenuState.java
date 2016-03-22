package com.christopherdcanfield.ui;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

public class MainMenuState extends AbstractAppState
{
	@Override
	public void initialize(AppStateManager stateManager, Application app)
	{
		System.out.println("MainMenuState.initial");
	}

	@Override
	public void update(float tpf)
	{

	}

	@Override
	public void setEnabled(boolean active)
	{
		System.out.println("MainMenuState.setEnabled(" + active + ")");
	}

	@Override
	public void cleanup()
	{
		System.out.println("MainMenuState.cleanup");
	}
}
