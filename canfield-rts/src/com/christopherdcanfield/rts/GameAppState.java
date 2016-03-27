/*
 * Copyright 2016 Christopher D. Canfield

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.christopherdcanfield.rts;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import java.util.logging.Logger;

/**
 *
 * @author Christopher D. Canfield
 */
public class GameAppState extends AbstractAppState
{
	private static final Logger logger = Logger.getLogger(GameAppState.class.getName());

	private SimpleApplication app;

	private ViewPort viewPort;
	private InputManager inputManager;
	private AssetManager assetManager;

	private Node gameRootNode;
	private Node gameGuiNode;

	public GameAppState()
	{
	}

	@Override
	public void initialize(AppStateManager stateManager, Application application)
	{
		this.app = (SimpleApplication)application;

		this.gameRootNode = new Node("gameRootNode");
		this.gameGuiNode = new Node("gameGuiRootNode");

		this.viewPort = this.app.getViewPort();
		this.inputManager = this.app.getInputManager();
		this.assetManager = this.app.getAssetManager();

		Camera cam = this.app.getCamera();
		cam.setLocation(new Vector3f(0, 0, 40));

		restart();

		setEnabled(true);
	}

	private void restart()
	{

	}

	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);

		// TODO (3/27/2016): Add/remove input listeners here.
		if (enabled) {
			app.getRootNode().attachChild(gameRootNode);
			app.getGuiNode().attachChild(gameGuiNode);
		} else {
			gameRootNode.removeFromParent();
			gameGuiNode.removeFromParent();
//			app.getStateManager().getState(MainMenuAppState.class).setEnabled(true);
		}
	}

	@Override
	public void cleanup()
	{
	}
}
