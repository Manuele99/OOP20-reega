package reega.viewutils;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Stack;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import reega.util.ServiceProvider;

public class NavigatorImpl implements Navigator {

    private final Stack<Controller> navigationStack = new Stack<>();
    private final ServiceProvider serviceProvider;
    private final ObjectProperty<Controller> selectedControllerProperty = new SimpleObjectProperty<>();
    private final BooleanProperty navigationStackNotEmptyProperty = new SimpleBooleanProperty(false);

    public NavigatorImpl(final ServiceProvider provider) {
        this.serviceProvider = provider;
    }

    @Override
    public <T extends Controller> T buildController(Class<T> controllerClass) {
        final Optional<T> optionalController = this.serviceProvider.getService(controllerClass);
        if (optionalController.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optionalController.get();
    }

    @Override
    public void pushControllerToStack(Controller controller, boolean clearNavigationStack) {
        if (clearNavigationStack) {
            this.navigationStack.clear();
        }
        this.navigationStack.push(controller);
        this.navigationStackNotEmptyProperty.set(this.navigationStack.size() > 1);
        this.selectedControllerProperty.set(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void popController() {
        if (this.navigationStackNotEmptyProperty.getValue().equals(true)) {
            this.navigationStack.pop();
            this.navigationStackNotEmptyProperty.set(this.navigationStack.size() > 1);
            this.selectedControllerProperty.set(this.navigationStack.peek());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectProperty<Controller> selectedControllerProperty() {
        return this.selectedControllerProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BooleanProperty navigationStackNotEmptyProperty() {
        return this.navigationStackNotEmptyProperty;
    }
}
