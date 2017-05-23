package com.infraleap.vaadin.scribble;

import com.infraleap.vaadin.scribble.client.ScribblePaneClientRpc;
import com.infraleap.vaadin.scribble.client.ScribblePaneServerRpc;
import com.infraleap.vaadin.scribble.client.ScribblePaneState;

import com.vaadin.shared.MouseEventDetails;

// This is the server-side UI component that provides public API 
// for ScribblePane
public class ScribblePane extends com.vaadin.ui.AbstractComponent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int clickCount = 0;

    public ScribblePane() {

        // To receive events from the client, we register ServerRpc
        ScribblePaneServerRpc rpc = this::handleClick;
        registerRpc(rpc);
    }

    // We must override getState() to cast the state to ScribblePaneState
    @Override
    protected ScribblePaneState getState() {
        return (ScribblePaneState) super.getState();
    }
    
    private void handleClick(MouseEventDetails mouseDetails){
        // Send nag message every 5:th click with ClientRpc
        if (++clickCount % 5 == 0) {
            getRpcProxy(ScribblePaneClientRpc.class)
                    .alert("Ok, that's enough!");
        }
        
        // Update shared state. This state update is automatically 
        // sent to the client. 
        getState().text = "You have clicked " + clickCount + " times";
    }
}
