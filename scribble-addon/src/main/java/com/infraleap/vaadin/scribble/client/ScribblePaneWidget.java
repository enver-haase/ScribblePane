package com.infraleap.vaadin.scribble.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

// Extend any GWT Widget
public class ScribblePaneWidget extends SimplePanel {

    public ScribblePaneWidget() {

       Canvas canvas = Canvas.createIfSupported();
       if (canvas != null){
    	   Context2d context = canvas.getContext2d();
    	   context.beginPath();
    	   context.moveTo(25,  0);
    	   context.lineTo(0, 20);
    	   context.lineTo(25, 40);
    	   context.lineTo(25, 0);
    	   context.fill();
    	   context.closePath();
       }
       add(canvas);
       
       //add(new Label("Hallo"));

        // State is set to widget in ScribblePaneConnector
    }

}