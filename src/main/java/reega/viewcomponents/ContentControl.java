/**
 *
 */
package reega.viewcomponents;

import java.util.NoSuchElementException;
import java.util.Optional;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import reega.viewutils.DataTemplate;
import reega.viewutils.DataTemplateManager;

/**
 * Control for handling a variable control in it based on its object property.
 */
public class ContentControl extends VBox {

    private ObjectProperty<Object> objProperty;

    /**
     * Get the current content that is bounded to a view.
     *
     * @return the content of the control
     */
    public Object getContent() {
        return this.objectProperty().get();
    }

    /**
     * Set a new content that is bounded to a view.
     *
     * @param value new content
     */
    public void setContent(final Object value) {
        this.objectProperty().set(value);
    }

    /**
     * Get the content property.
     *
     * @return the content property
     */
    public ObjectProperty<Object> objectProperty() {
        if (this.objProperty == null) {
            this.objProperty = new SimpleObjectProperty<>();
            this.objectProperty().addListener((observable, oldValue, newValue) -> {
                // Get the template for the new content
                final Optional<DataTemplate<?>> template = DataTemplateManager.getInstance()
                        .getTemplate(newValue.getClass());
                // Generate the element, or create a VBox if the template manager cannot find a
                // Control factory associated with the class of the new value
                final Parent newElement = template
                        .map(dTemplate -> ((DataTemplate<Object>) dTemplate).getControlFactory(newValue).get())
                        .map(control -> {
                            if (control instanceof Parent) {
                                return (Parent) control;
                            }
                            throw new NoSuchElementException("No JavaFX implementation for the object: " + newValue);
                        })
                        .orElse(new VBox());
                // Needed check to create or update the only child of this control
                if (this.getChildren().size() == 0) {
                    this.getChildren().add(newElement);
                } else {
                    this.getChildren().set(0, newElement);
                }
                VBox.setVgrow(this.getChildren().get(0), Priority.ALWAYS);
            });
        }
        return this.objProperty;
    }

}
