package com.christopherdcanfield.viewport;

import java.util.ArrayList;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
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

	private Node units;

	private ArrayList<Geometry> selectedUnits = new ArrayList<>();

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

		units = new Node("units");
		rootNode.attachChild(units);

		Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("box", b);
        Material mat = new Material(assetManager,
        		"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        geom.setUserData("originalColor", ColorRGBA.Blue);
        units.attachChild(geom);

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

        inputManager.addMapping("click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener((ActionListener)(String name, boolean isPressed, float tpf) -> {
        	if (!isPressed) {
	        	CollisionResults results = new CollisionResults();
	        	Vector2f click2d = inputManager.getCursorPosition();
	        	Vector3f click3d = cam.getWorldCoordinates(
	        	    new Vector2f(click2d.x, click2d.y), 0f).clone();
	        	Vector3f dir = cam.getWorldCoordinates(
	        	    new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
	        	Ray ray = new Ray(click3d, dir);
    	        // DO NOT check collision with the root node, or else ALL collisions will hit the skybox! Always make a separate node for objects you want to collide with.
    	        units.collideWith(ray, results);
    	        System.out.println("Collisions: " + results.size());

    	        for (Geometry geometry : selectedUnits) {
	        		geometry.getMaterial().setColor("Color", geometry.getUserData("originalColor"));
	        	}

    	        if (results.size() > 0) {
    	        	System.out.println(results.getClosestCollision().getGeometry().getName());
    	        	Geometry closest = results.getClosestCollision().getGeometry();
    	        	closest.getMaterial().setColor("Color", ColorRGBA.Cyan);
    	        	selectedUnits.add(closest);
    	        } else {
    	        	selectedUnits.clear();
    	        }
    		}
        }, "click");
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
