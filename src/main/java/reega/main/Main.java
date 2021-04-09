/**
 *
 */
package reega.main;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import reega.util.ServiceProvider;

/**
 * @author Marco
 *
 */
public class Main extends Application {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        UIAppInitializer.getInstance().initialize();
        final ServiceProvider svcProvider = UIAppInitializer.getInstance().getServiceProvider();
        Screen primaryScreen = Screen.getPrimary();
        double aspectRatio = getScreenAspectRatio(primaryScreen);
        Rectangle2D bounds = primaryScreen.getVisualBounds();
        //Use the 75% of the width
        double minWidth = bounds.getWidth() * 0.4;
        //Keep the 16/9 proportion
        double minHeight = minWidth / aspectRatio;
        primaryStage.setMinHeight(minHeight);
        primaryStage.setMinWidth(minWidth);

        //Use the 75% of the width
        double prefWidth = bounds.getWidth() * 0.7;
        //Keep the 16/9 proportion
        double prefHeight = prefWidth / aspectRatio;
        primaryStage.setHeight(prefHeight);
        primaryStage.setWidth(prefWidth);

        primaryStage.setScene(new Scene(svcProvider.getRequiredService(BaseLayoutView.class)));
        primaryStage.show();
    }

    private double getScreenAspectRatio(Screen screen) {
        Rectangle2D bounds = screen.getBounds();
        return bounds.getWidth() / bounds.getHeight();
    }
}
