package com.infraleap.vaadin.scribble;

import com.infraleap.vaadin.scribble.client.ScribblePaneClientRpc;
import com.infraleap.vaadin.scribble.client.ScribblePaneServerRpc;
import com.infraleap.vaadin.scribble.client.ScribblePaneState;

// This is the server-side UI component that provides public API 
// for ScribblePane
public abstract class ScribblePane extends com.vaadin.ui.AbstractComponent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public ScribblePane() {

        // To receive events from the client, we register ServerRpc
        ScribblePaneServerRpc rpc = this::onImageDataFromClient;
        registerRpc(rpc);
    }

    // We must override getState() to cast the state to ScribblePaneState
    @Override
    protected ScribblePaneState getState() {
        return (ScribblePaneState) super.getState();
    }

    public void requestImageFromClient(){
        getRpcProxy(ScribblePaneClientRpc.class).sendImage();
    }

    public void clearImage() { getRpcProxy(ScribblePaneClientRpc.class).clearImage(); }

    public abstract void onImageDataFromClient(int width, int height, int[] rgba);
}
