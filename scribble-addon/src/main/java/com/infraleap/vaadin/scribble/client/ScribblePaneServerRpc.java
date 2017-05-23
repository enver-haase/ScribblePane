package com.infraleap.vaadin.scribble.client;

import com.vaadin.shared.communication.ServerRpc;

// ServerRpc is used to pass events from client to server
public interface ScribblePaneServerRpc extends ServerRpc {

    /**
     * Allows the client to upload the image data to the server.
     * @param width width of the image
     * @param height height of the image
     * @param rgba image data, red-green-blue-alpha bytes wrapped in ints for simplicity (because of signed bytes in Java)
     */
    void imageData(int width, int height, int[] rgba);

}
