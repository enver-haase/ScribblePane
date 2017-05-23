package com.infraleap.vaadin.scribble.client;

import com.google.gwt.canvas.dom.client.CanvasPixelArray;
import com.google.gwt.canvas.dom.client.ImageData;
import com.infraleap.vaadin.scribble.ScribblePane;
//import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(ScribblePane.class)
public class ScribblePaneConnector extends AbstractComponentConnector {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// ServerRpc is used to send events to server. Communication implementation
    // is automatically created here
    //ScribblePaneServerRpc rpc = RpcProxy.create(ScribblePaneServerRpc.class, this);

    public ScribblePaneConnector() {
        
        // To receive RPC events from server, we register ClientRpc implementation 
        registerRpc(ScribblePaneClientRpc.class, new ScribblePaneClientRpc() {
            @Override
            public void sendImage() {
                ScribblePaneWidget pane = getWidget();
                ImageData imageData = pane.getImageData();
                CanvasPixelArray arr = imageData.getData();
                int[] data = new int[arr.getLength()];
                for (int i=0; i<data.length; i++){
                    data[i] = arr.get(i);
                }

                getRpcProxy(ScribblePaneServerRpc.class).imageData(imageData.getWidth(), imageData.getHeight(), data);
            }

            @Override
            public void clearImage() {
                getWidget().clearImage();
            }


        });

    }

    // We must implement getWidget() to cast to correct type 
    // (this will automatically create the correct widget type)
    @Override
    public ScribblePaneWidget getWidget() {
        return (ScribblePaneWidget) super.getWidget();
    }

    // We must implement getState() to cast to correct type
    @Override
    public ScribblePaneState getState() {
        return (ScribblePaneState) super.getState();
    }

    // Whenever the state changes in the server-side, this method is called
    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        // State is directly readable in the client after it is set in server
    }
}
