package me.ilucah.ahmaadsadventure.scenes;

import me.ilucah.ahmaadsadventure.handler.Handler;

import java.awt.*;

public abstract class Scene {
	
private static Scene currentState = null;
	
	public static void setScene(Scene state){
		currentState = state;
	}
	
	public static Scene getScene(){
		return currentState;
	}
	
	//CLASS
	
	protected Handler handler;
	
	public Scene(Handler handler){
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

}
