package com.christopherdcanfield.viewport;

import javax.swing.JComboBox.KeySelectionManager;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class MultipleViewportsApp extends SimpleApplication
{
	public static void main(String[] args)
	{
		MultipleViewportsApp app = new MultipleViewportsApp();
		app.start();
	}

	private Camera camera2;

	private boolean moveCameraEast;
	private boolean moveCameraWest;
	private boolean moveCameraNorth;
	private boolean moveCameraSouth;

	@Override
	public void simpleInitApp()
	{
		flyCam.setEnabled(false);

		camera2 = cam.clone();
		camera2.setViewPort(0.85f, 0.98f, 0.85f, 0.98f);
		ViewPort viewport = renderManager.createPostView("camera2", camera2);
		viewport.setClearColor(true);
		viewport.attachScene(rootNode);
		viewport.setBackgroundColor(new ColorRGBA(1, 1, 1, 0.55f));

		Box b = new Box(1, 1, 1); // create cube shape
        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
        Material mat = new Material(assetManager,
          "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        geom.setMaterial(mat);                   // set the cube's material
        rootNode.attachChild(geom);              // make the cube appear in the scene

        inputManager.addMapping("move-camera-east", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("move-camera-west", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("move-camera-north", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("move-camera-south", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addListener((ActionListener)(String name, boolean isPressed, float tpf) -> {
        	if (name.equals("move-camera-east")) {
	        	moveCameraEast = isPressed;
        	} else if (name.equals("move-camera-west")) {
        		moveCameraWest = isPressed;
        	} else if (name.equals("move-camera-north")) {
        		moveCameraNorth = isPressed;
        	} else if (name.equals("move-camera-south")) {
        		moveCameraSouth = isPressed;
        	}
        }, "move-camera-east", "move-camera-west", "move-camera-north", "move-camera-south");
	}

	@Override
	public void simpleUpdate(float tpf)
	{
		if (moveCameraEast) {
			Vector3f newCameraPosition = cam.getLocation().add(0.01f, 0, 0);
        	cam.setLocation(newCameraPosition);
		}
		else if (moveCameraWest) {
			Vector3f newCameraPosition = cam.getLocation().add(-0.01f, 0, 0);
        	cam.setLocation(newCameraPosition);
		}
		else if (moveCameraNorth) {
			Vector3f newCameraPosition = cam.getLocation().add(0, 0.01f, 0);
        	cam.setLocation(newCameraPosition);
		}
		else if (moveCameraSouth) {
			Vector3f newCameraPosition = cam.getLocation().add(0, -0.01f, 0);
        	cam.setLocation(newCameraPosition);
		}
	}
}
