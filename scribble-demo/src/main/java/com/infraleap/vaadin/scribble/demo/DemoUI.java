package com.infraleap.vaadin.scribble.demo;

import com.infraleap.vaadin.scribble.ScribblePane;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.Date;

@Theme("demo")
@Title("ScribblePane Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();

        HorizontalLayout hl = new HorizontalLayout();

        // Initialize our new UI scribblePane
        final ScribblePane scribblePane = new ScribblePane() {
            @Override
            public void onImageDataFromClient(int width, int height, int[] rgba) {
                //layout.addComponent(new Label("Got an image ("+width+"x"+height+")!")); // XXX

                StreamResource.StreamSource imageSource = new RGBImageSource(width, height, rgba);
                StreamResource resource = new StreamResource(imageSource, "image-"+width+"x"+height+".png");
                Image image = new Image("("+width+"x"+height + ") - " + new Date(), resource);
                layout.addComponent(image);
                layout.setComponentAlignment(image, Alignment.TOP_CENTER);
            }
        };

        // Show it in the middle of the screen
        layout.setStyleName("demoContentLayout");
        //layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);

        layout.addComponent(scribblePane);

        Button snapButton = new Button("Snapshot!", clickEvent -> scribblePane.requestImageFromClient());
        Button clearButton = new Button ("Clear!", clickEvent -> scribblePane.clearImage());

        layout.addComponent(hl);
        hl.addComponent(snapButton);
        hl.addComponent(clearButton);
        hl.setComponentAlignment(snapButton, Alignment.TOP_CENTER);
        hl.setComponentAlignment(clearButton, Alignment.TOP_CENTER);

        layout.setComponentAlignment(scribblePane, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(hl, Alignment.TOP_CENTER);

        setContent(layout);
    }
}
