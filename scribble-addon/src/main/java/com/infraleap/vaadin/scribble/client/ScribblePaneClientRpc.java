package com.infraleap.vaadin.scribble.client;

import com.vaadin.shared.communication.ClientRpc;

// ClientRpc is used to pass events from server to client
// For sending information about the changes to component state, use State instead
public interface ScribblePaneClientRpc extends ClientRpc {

    /**
     * Tells the widget to upload its image data to the server.
     */
    void sendImage();

    /**
     * Tells the widget to clear its image.
     */
    void clearImage();
}