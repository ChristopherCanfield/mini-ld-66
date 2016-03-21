package com.christopherdcanfield.viewport;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class MultipleViewportsApp extends SimpleApplication
{
	public static void main(String[] args)
	{
		MultipleViewportsApp app = new MultipleViewportsApp();
		app.start();
	}

	private Camera camera2;

	@Override
	public void simpleInitApp()
	{
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
	}
}
