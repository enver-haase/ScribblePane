package com.infraleap.vaadin.scribble.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.SimplePanel;

public class ScribblePaneWidget extends SimplePanel {

//    private static native void log(String text) /*-{
//        console.log(text);
//    }-*/;

    private class Point {
        private final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

//        @Override
//        public String toString() {
//            return "(x: " + x + ", y: " + y + ")";
//        }
    }

    private Point lastPoint = new Point(0, 0);
    private boolean mouseDown = false;

    public ScribblePaneWidget() {
        setStyleName("scribblepane");

        Canvas canvas = Canvas.createIfSupported();
        if (canvas != null) {
            canvas.addMouseDownHandler(e -> {
                mouseDown = true;
                lastPoint = new Point(e.getX(), e.getY());
//                log("MouseDown: Point is " + lastPoint);
            });
            canvas.addMouseOutHandler(e -> mouseDown = false);
            canvas.addMouseUpHandler(e -> mouseDown = false);
            canvas.addMouseMoveHandler(e -> {
                if (mouseDown) {
                    Context2d context = canvas.getContext2d();
                    context.beginPath();
                    context.moveTo(lastPoint.x, lastPoint.y);
                    context.lineTo(e.getX(), e.getY());
                    context.stroke();
                    context.closePath();
                    lastPoint = new Point(e.getX(), e.getY());
//                    log("MouseMove: Point is " + lastPoint);
                }
            });
        }
        add(canvas);

        // State is set to widget in ScribblePaneConnector
    }

}