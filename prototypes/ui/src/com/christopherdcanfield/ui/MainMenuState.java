package com.christopherdcanfield.ui;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class MainMenuState extends AbstractAppState implements ScreenController
{
	@Override
	public void initialize(AppStateManager stateManager, Application app)
	{
		System.out.println("MainMenuState.initialize");

		AssetManager assetManager = app.getAssetManager();
		InputManager inputManager = app.getInputManager();
		AudioRenderer audioRenderer = app.getAudioRenderer();
		ViewPort guiViewPort = app.getGuiViewPort();

		NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(
				assetManager, inputManager, audioRenderer, guiViewPort);
		Nifty nifty = niftyDisplay.getNifty();
		guiViewPort.addProcessor(niftyDisplay);

		// nifty.validateXml is only needed when debugging.
		try {
			nifty.validateXml("Interface/MainMenu.xml");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		nifty.fromXml("Interface/MainMenu.xml", "start", this);
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

	@Override
	public void bind(Nifty nifty, Screen screen)
	{
		System.out.println("MainMenuState.bind");
	}

	@Override
	public void onStartScreen()
	{
		System.out.println("MainMenuState.onStartScreen");
	}

	@Override
	public void onEndScreen()
	{
		System.out.println("MainMenuState.onEndScreen");
	}

	public void onSinglePlayerButtonClicked()
	{
		System.out.println("onSinglePlayerButtonClicked");
	}

	public void onMultiplayerButtonClicked()
	{
		System.out.println("onMultiplayerButtonClicked");
	}

	public void onSettingsButtonClicked()
	{
		System.out.println("onSettingsButtonClicked");
	}

	public void onExitButtonClicked()
	{
		System.out.println("onExitButtonClicked");
	}
}
