package com.christopherdcanfield.ui;

import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;

import de.lessvoid.nifty.Nifty;

public class UiPrototypeApp extends SimpleApplication
{
	public static void main(String[] args)
	{
		UiPrototypeApp application = new UiPrototypeApp();
		application.start();
	}

	@Override
	public void simpleInitApp()
	{
		MainMenuState state = new MainMenuState();
		stateManager.attach(state);
	}

	@Override
	public void simpleUpdate(float tpf)
	{
	}
}
