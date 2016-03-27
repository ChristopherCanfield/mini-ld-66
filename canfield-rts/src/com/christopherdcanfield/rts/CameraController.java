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
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

public class CameraController extends AbstractAppState
{
	private SimpleApplication app;
	private Camera camera;
	private InputManager inputManager;

	private ActionListener inputListener;

	private boolean moveCameraEast;
	private boolean moveCameraWest;
	private boolean moveCameraNorth;
	private boolean moveCameraSouth;

	@Override
	public void initialize(AppStateManager stateManager, Application application)
	{
		this.camera = app.getCamera();
		this.app = (SimpleApplication)application;
		this.inputManager = app.getInputManager();

		inputManager.addMapping("move-camera-east", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("move-camera-west", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("move-camera-north", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("move-camera-south", new KeyTrigger(KeyInput.KEY_DOWN));

        inputListener = (ActionListener)(String name, boolean isPressed, float tpf) -> {
        	if (name.equals("move-camera-east")) {
	        	moveCameraEast = isPressed;
        	} else if (name.equals("move-camera-west")) {
        		moveCameraWest = isPressed;
        	} else if (name.equals("move-camera-north")) {
        		moveCameraNorth = isPressed;
        	} else if (name.equals("move-camera-south")) {
        		moveCameraSouth = isPressed;
        	}
        };
	}

	@Override
	public void setEnabled(boolean active)
	{
		super.setEnabled(active);

		if (active) {
	        inputManager.addListener(inputListener,
	        		"move-camera-east", "move-camera-west", "move-camera-north", "move-camera-south")
		} else {
			inputManager.removeListener(inputListener);
		}
	}

	@Override
	public void cleanup()
	{
		super.cleanup();
	}

	@Override
	protected void update(float tpf)
	{
		if (moveCameraEast) {
			Vector3f newCameraPosition = camera.getLocation().add(0.01f, 0, 0);
			camera.setLocation(newCameraPosition);
		}
		else if (moveCameraWest) {
			Vector3f newCameraPosition = camera.getLocation().add(-0.01f, 0, 0);
			camera.setLocation(newCameraPosition);
		}
		else if (moveCameraNorth) {
			Vector3f newCameraPosition = camera.getLocation().add(0, 0, -0.01f);
			camera.setLocation(newCameraPosition);
		}
		else if (moveCameraSouth) {
			Vector3f newCameraPosition = camera.getLocation().add(0, 0, 0.01f);
			camera.setLocation(newCameraPosition);
		}
	}
}
