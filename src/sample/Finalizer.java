package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.nio.file.Path;
import java.util.Collections;


public class Finalizer<Void> extends Task {
    @Override
    protected Void call() throws Exception {

        Platform.runLater(new Runnable() {
            @Override public void run() {

                if (!Controller.result.isEmpty()) {
                    Collections.sort(Controller.result);
                }

                if (Controller.result.isEmpty()) {
                    Controller.answer = String.format("\nПо слову\n\"%s\"\nничего не нашлось", Controller.searchString);
                }
                else {
                    Controller.answer = String.format("\nПо слову\n\"%s\"\nнашлось %d результатов:\n", Controller.searchString, Controller.result.size());
                }

                Main.writelog(Main.filesArea, Controller.answer, false);

                if (!Controller.result.isEmpty()) {
                    for (Path x : Controller.result) {
                        Main.writelog(Main.filesArea, x.toAbsolutePath().toString() + "\n", false);
                    }
                }

            }
        });

        return null;
    }
}
